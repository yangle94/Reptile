package cn.ylapl.operat;

import cn.ylapl.entity.YlCompany;
import cn.ylapl.entity.YlCompanyRecruitment;
import cn.ylapl.util.GsonUtil;
import cn.ylapl.util.HttpClientByYL;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 获取公司信息的多线程方法
 * Created by Angle on 2017/3/26.
 */
public class CompanyMatchCounter implements Callable<YlCompanyRecruitment> {

    private static final Logger logger = LoggerFactory.getLogger(CompanyMatchCounter.class);
    private static final String URL = "https://www.lagou.com/gongsi/searchPosition.json";

    //结果集
    private ConcurrentHashMap<String, Future<YlCompanyRecruitment>> resultMap;

    //线程池
    private ExecutorService poll;

    //当前页数
    private int num;

    //公司列表中的对象
    private YlCompany company;

    public CompanyMatchCounter(ConcurrentHashMap resultMap, ExecutorService poll, int num, YlCompany company) {
        this.resultMap = resultMap;
        this.poll = poll;
        this.num = num;
        this.company = company;
    }

    @Override
    public YlCompanyRecruitment call() throws Exception {
        Integer companyId = company.getCompanyId();

        Map<String, String> head = new HashMap<>();
        head.put("X-Requested-With", "XMLHttpRequest");
        head.put("Accept", "application/json, text/javascript, */*; q=0.01");
        head.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        head.put("Cookie", "JSESSIONID=77EFD5688C96210049C8D131F507C301; user_trace_token=20170326150553-beb0b26d92fd44aea08aa9708b367ada");

        Map<String, String> param = new HashMap<>();
        param.put("companyId", companyId.toString());
        param.put("positionFirstType", "全部");
        param.put("pageNo", String.valueOf(num));
        param.put("pageSize", "10");

        String res = HttpClientByYL.httpPostRequest(URL, head, param);
        JsonObject object = GsonUtil.toJsonObj(res).getAsJsonObject("content").getAsJsonObject("data");

        int colun = object.getAsJsonObject("page").getAsJsonArray("result").size();
        logger.info("当前公司：{}，当前工作列表第几页:{},当前页工作数：{},返回值：{}", company.getCompanyFullName(), num, colun, res);

        if(colun > 0) {

            YlCompanyRecruitment ylCompanyRecruitment = new YlCompanyRecruitment();
            ylCompanyRecruitment.setCompanyId(companyId);
            ylCompanyRecruitment.setRecruitment(object.toString());

            //加入新线程,向下翻页
            CompanyMatchCounter companyMatchCounter = new CompanyMatchCounter(resultMap, poll, num + 1, company);
            Future<YlCompanyRecruitment> result = poll.submit(companyMatchCounter);

            resultMap.put(num + "_" + company.getCompanyId(), result);

            return ylCompanyRecruitment;
        }

        return null;
    }
}
