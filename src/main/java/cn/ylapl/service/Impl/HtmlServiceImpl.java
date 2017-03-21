package cn.ylapl.service.Impl;

import cn.ylapl.dao.YlResultDao;
import cn.ylapl.dto.AnalysisInfoDto;
import cn.ylapl.dto.ParamInfoDto;
import cn.ylapl.dto.ValueResultDto;
import cn.ylapl.entity.YlResult;
import cn.ylapl.operat.HttpOperat;
import cn.ylapl.service.HtmlService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * httpclent获得网页数据
 * Created by Angle on 2017/3/5.
 */

@Service
public class HtmlServiceImpl implements HtmlService {
    private static final Logger logger = LoggerFactory.getLogger(HtmlServiceImpl.class);

    private final HttpOperat httpOperat;

    private final YlResultDao ylResultDao;

    @Autowired
    public HtmlServiceImpl(HttpOperat httpOperat, YlResultDao ylResultDao) {
        this.httpOperat = httpOperat;
        this.ylResultDao = ylResultDao;
    }

    @Override
    public String getHtml(ParamInfoDto pageInfo) {

        logger.debug("请求参数:{}" , pageInfo);

        String html = httpOperat.getHtml(pageInfo);
        Date now = new Date();

        YlResult ylResult = new YlResult(now, -1, now, -1, 0, html);
        int num = ylResultDao.insert(ylResult);
        logger.debug("获得页面html:{},插入数据库条数:{}", html, num);

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
