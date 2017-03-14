package cn.ylapl.service.Impl;

import cn.ylapl.dto.AnalysisInfoDto;
import cn.ylapl.dto.ParamInfoDto;
import cn.ylapl.dto.ValueResultDto;
import cn.ylapl.operat.HttpOperat;
import cn.ylapl.service.HtmlService;
import cn.ylapl.util.GsonUtil;
import cn.ylapl.util.logger.LogUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * httpclent获得网页数据
 * Created by Angle on 2017/3/5.
 */

@Service
public class HtmlServiceImpl implements HtmlService {

    @Autowired
    private HttpOperat httpOperat;

    @Override
    public String getHtml(ParamInfoDto pageInfo) {

        LogUtil.info(HtmlServiceImpl.class, "请求参数");
        String html = httpOperat.getHtml(pageInfo);
        LogUtil.info(HtmlServiceImpl.class, "获得页面html：" + html);

        return html;
    }

    @Override
    public ValueResultDto getValue(String html, AnalysisInfoDto analysisInfoDto) {

        ValueResultDto valueResultDto = new ValueResultDto();

        Document document = Jsoup.parse(html);

        valueResultDto.setClsRes(httpOperat.getValueByClass(document, analysisInfoDto.getClassstr()))
                .setIdRes(httpOperat.getValueByClass(document, analysisInfoDto.getClassstr()));
        return valueResultDto;
    }
}
