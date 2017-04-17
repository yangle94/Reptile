package cn.ylapl.operat;

import cn.ylapl.entity.YlCompany;
import cn.ylapl.entity.YlCompanyRecruitment;
import cn.ylapl.util.GsonUtil;
import cn.ylapl.util.HttpClientByYL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    public CompanyMatchCounter(ConcurrentHashMap<String, Future<YlCompanyRecruitment>> resultMap, ExecutorService poll, int num, YlCompany company) {
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
        head.put("Cookie", "JSESSIONID=DFC23690F3981E0C9F863DFEFA90A818; user_trace_token=20170412231657-ac6b61eb304e41a59ae8c66253b840eb; _gat=1; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; LGUID=20170412231657-11ee91e3-1f93-11e7-9f56-5254005c3644; index_location_city=%E6%9D%AD%E5%B7%9E; TG-TRACK-CODE=index_company; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1492010218; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1492010264; _ga=GA1.2.1795332620.1492010218; LGSID=20170412231657-11ee901d-1f93-11e7-9f56-5254005c3644; LGRID=20170412231744-2d77414e-1f93-11e7-956c-525400f775ce");
        head.put("X-Anit-Forge-Code", "18990186");
        head.put("X-Anit-Forge-Token", "0af7a8db-59e9-457f-8750-c8883e7f7e95");

        Map<String, String> param = new HashMap<>();
        param.put("companyId", companyId.toString());
        param.put("positionFirstType", "全部");
        param.put("pageNo", String.valueOf(num));
        param.put("pageSize", "10");

        String res = HttpClientByYL.httpPostRequest(URL, head, param);


//        JsonObject object = GsonUtil.toJsonObj(res).getAsJsonObject("content").getAsJsonObject("data");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode objectNode = mapper.readTree(res).path("content").path("data");
        JsonNode jsonNode = objectNode.path("page").path("result");
        int colun = 0;

        if (jsonNode.isArray()) {
            colun = jsonNode.size();
        }

        Thread.sleep(5000);
//        int colun = object.getAsJsonObject("page").getAsJsonArray("result").size();
        logger.info("当前公司：{}，当前工作列表第几页:{},当前页工作数：{},返回值：{}", company.getCompanyFullName(), num, colun, res);

        if(colun > 0) {

            YlCompanyRecruitment ylCompanyRecruitment = new YlCompanyRecruitment();
            ylCompanyRecruitment.setId(null);
            ylCompanyRecruitment.setCompanyId(companyId);
            ylCompanyRecruitment.setRecruitment(objectNode.toString());

            //加入新线程,向下翻页
            CompanyMatchCounter companyMatchCounter = new CompanyMatchCounter(resultMap, poll, num + 1, company);
            Future<YlCompanyRecruitment> result = poll.submit(companyMatchCounter);

            resultMap.put(num + "_" + company.getCompanyId(), result);

            return ylCompanyRecruitment;
        }

        return null;
    }
}
