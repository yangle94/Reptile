package cn.ylapl.service.Impl;

import cn.ylapl.dto.ParamInfoDto;
import cn.ylapl.entity.YlCompany;
import cn.ylapl.entity.YlCompanyRecruitment;
import cn.ylapl.mapper.YlCompanyMapper;
import cn.ylapl.mapper.YlCompanyQccMapper;
import cn.ylapl.mapper.YlCompanyRecruitmentMapper;
import cn.ylapl.operat.CompanyMatchCounter;
import cn.ylapl.operat.HttpOperat;
import cn.ylapl.operat.SeleniumMatchCounter;
import cn.ylapl.service.LagoHtmlService;
import cn.ylapl.util.GsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        String json = "{\n" +
                "  \"header\": {\n" +
                "\"Accept\":\"application/json,text/javascript, */*;q=0.01\",\n" +
                "\"Content-Type\":\"application/x-www-form-urlencoded; charset=UTF-8\",\n" +
                "\"User-Agent\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36\",\n" +
                "\"X-Requested-With\":\"XMLHttpRequest\",\n" +
                "\"Cookie\":\"JSESSIONID=77EFD5688C96210049C8D131F507C301; user_trace_token=20170326150553-beb0b26d92fd44aea08aa9708b367ada\"\n" +
                "},\n" +
                "  \"method\": \"post\",\n" +
                "  \"param\": {\"first\":\"false\",\n" +
                "\"pn\":1,\n" +
                "\"sortField\":0,\n" +
                "\"havemark\":0},\n" +
                "  \"url\": \"https://www.lagou.com/gongsi/6-0-0.json\"\n" +
                "}";

        pageInfoDto = GsonUtil.gson.fromJson(json, ParamInfoDto.class);

        //创建线程池
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);


        //获得公司列表
        int i = 1;
        while (true) {
            //结果集hash
            ConcurrentHashMap<String, Future<YlCompanyRecruitment>> resMap = new ConcurrentHashMap<>();

            String html = httpOperat.getHtml(pageInfoDto);
            JsonArray object = GsonUtil.toJsonObj(html).getAsJsonArray("result");

            for (JsonElement element : object) {

                YlCompany ylCompany = GsonUtil.gson.fromJson(element, YlCompany.class);

                companyMapper.insert(ylCompany);

                CompanyMatchCounter companyMatchCounter = new CompanyMatchCounter(resMap, pool, 1, ylCompany);
                Future<YlCompanyRecruitment> result = pool.submit(companyMatchCounter);
                resMap.put(1 + "_" + ylCompany.getCompanyId(), result);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (object.size() == 0) {
                break;
            }

            pageInfoDto.getParam().put("pn",String.valueOf(++i));

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

                        companyRecruitment.setId(null);
                        companyRecruitmentMapper.insert(companyRecruitment);
                    }

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




        return null;
    }

    @Override
    public String getQCC(String qq, String pwd) {

        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
//        List<YlCompany> list = companyMapper.selectAll();

        YlCompany ylCompany = new YlCompany();
        ylCompany.setId(349);
        Example example = new Example(YlCompany.class);
        example.createCriteria()
                .andCondition("not exists(select id from yl_company_qcc where yl_company.id = yl_company_qcc.company_info)");
        List<YlCompany> list = companyMapper.selectByExample(example);

        for (YlCompany company : list) {

            SeleniumMatchCounter seleniumMatchCounter = new SeleniumMatchCounter(company.getCompanyFullName(), companyQccMapper, company.getId().toString());
            pool.submit(seleniumMatchCounter);
        }

        return null;
    }

}
