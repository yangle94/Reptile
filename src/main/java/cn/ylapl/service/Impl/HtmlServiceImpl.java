package cn.ylapl.service.Impl;

import cn.ylapl.dao.YlResultDao;
import cn.ylapl.dto.AnalysisInfoDto;
import cn.ylapl.dto.ParamInfoDto;
import cn.ylapl.dto.ValueResultDto;
import cn.ylapl.entity.YlResult;
import cn.ylapl.operat.HttpOperat;
import cn.ylapl.service.HtmlService;
import cn.ylapl.util.logger.LogUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * httpclent获得网页数据
 * Created by Angle on 2017/3/5.
 */

@Service
public class HtmlServiceImpl implements HtmlService {

    @Autowired
    private HttpOperat httpOperat;

    @Autowired
    private YlResultDao ylResultDao;

    @Override
    public String getHtml(ParamInfoDto pageInfo) {

        LogUtil.debug(HtmlServiceImpl.class, "请求参数");
        String html = httpOperat.getHtml(pageInfo);
        Date now = new Date();

//        YlResult ylResultBuild = new YlResult(html, now, -1, now, -1, 0);
        YlResult ylResult = new YlResult();
        ylResultDao.insert(ylResult);

        LogUtil.debug(HtmlServiceImpl.class, "获得页面html：" + html);

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

    @Override
    public String getHtmlByDB(YlResult ylResult) {
        YlResult ylResult1 = ylResultDao.getOneById(ylResult);
        return ylResult1.getHtml();
    }

    @Override
    public int deleteHtmlToDb(YlResult ylResult) {

        return ylResultDao.deleteById(ylResult);
    }

    @Override
    public int updateYlResult(YlResult ylResult) {

        return ylResultDao.update(ylResult);
    }
}
