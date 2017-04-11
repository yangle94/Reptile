package cn.ylapl.service.Impl;

import cn.ylapl.dto.ParamInfoDto;
import cn.ylapl.entity.YlCompany;
import cn.ylapl.entity.YlCompanyQcc;
import cn.ylapl.entity.YlCompanyRecruitment;
import cn.ylapl.mapper.YlCompanyMapper;
import cn.ylapl.mapper.YlCompanyQccMapper;
import cn.ylapl.mapper.YlCompanyRecruitmentMapper;
import cn.ylapl.operat.CompanyMatchCounter;
import cn.ylapl.operat.HttpOperat;
import cn.ylapl.operat.SeleniumMatchCounter;
import cn.ylapl.operat.SeleniumOperat;
import cn.ylapl.service.LagoHtmlService;
import cn.ylapl.util.GsonUtil;
import com.google.common.base.Function;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * Created by Angle on 2017/3/26.
 */
@Service
public class LagoHtmlServiceImpl implements LagoHtmlService {

    @Autowired
    private HttpOperat httpOperat;

    @Autowired
    private YlCompanyRecruitmentMapper companyRecruitmentMapper;

    @Autowired
    private YlCompanyMapper companyMapper;

    @Autowired
    private YlCompanyQccMapper companyQccMapper;

    @Override
    public String getCompanies(ParamInfoDto pageInfoDto) {
//        String json = "{\n" +
//                "  \"header\": {\n" +
//                "\"Accept\":\"application/json,text/javascript, */*;q=0.01\",\n" +
//                "\"Content-Type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\n" +
//                "\"User-Agent\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36\",\n" +
//                "\"X-Requested-With\":\"XMLHttpRequest\",\n" +
//                "\"Cookie\":\"JSESSIONID=77EFD5688C96210049C8D131F507C301; user_trace_token=20170326150553-beb0b26d92fd44aea08aa9708b367ada\"\n" +
//                "},\n" +
//                "  \"method\": \"post\",\n" +
//                "  \"param\": {\"first\":\"false\",\n" +
//                "\"pn\":1,\n" +
//                "\"sortField\":0,\n" +
//                "\"havemark\":0},\n" +
//                "  \"url\": \"https://www.lagou.com/gongsi/6-0-0.json\"\n" +
//                "}";
//
//        ParamInfoDto pageInfoDto = GsonUtil.gson.fromJson(json, ParamInfoDto.class);

        //创建线程池
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(7);
        //结果集hash
        ConcurrentHashMap<String, Future<YlCompanyRecruitment>> resMap = new ConcurrentHashMap<>();

        //获得公司列表
        String html = httpOperat.getHtml(pageInfoDto);
        JsonArray object = GsonUtil.toJsonObj(html).getAsJsonArray("result");

        for (JsonElement element : object) {

            YlCompany ylCompany = GsonUtil.gson.fromJson(element, YlCompany.class);

            companyMapper.insert(ylCompany);

            CompanyMatchCounter companyMatchCounter = new CompanyMatchCounter(resMap, pool, 1, ylCompany);
            Future<YlCompanyRecruitment> result = pool.submit(companyMatchCounter);
            resMap.put(1 + "_" + ylCompany.getCompanyId(), result);
        }

        while (true) {
            if(pool.getActiveCount() < 1) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Future<YlCompanyRecruitment> future : resMap.values()) {
            try {

                YlCompanyRecruitment companyRecruitment = future.get();

                if(companyRecruitment != null && companyRecruitment.getCompanyId() != null ) {

                    companyRecruitmentMapper.insert(future.get());
                }

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return html;
    }

    @Override
    public String getQCC(String qq, String pwd) {

        //创建线程池
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<YlCompany> list = companyMapper.selectAll();

        for (YlCompany company : list) {

            SeleniumMatchCounter seleniumMatchCounter = new SeleniumMatchCounter(company.getCompanyFullName(), companyQccMapper);
            pool.submit(seleniumMatchCounter);
        }

        return null;
    }

}
