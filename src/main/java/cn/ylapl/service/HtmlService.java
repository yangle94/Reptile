package cn.ylapl.service;

import cn.ylapl.dto.AnalysisInfoDto;
import cn.ylapl.dto.ParamInfoDto;
import cn.ylapl.dto.ValueResultDto;
import cn.ylapl.entity.YlResult;

/**
 * 获取被爬取页面的信息
 * Created by Angle on 2017/3/5
 */
public interface HtmlService {

    String getHtml(ParamInfoDto pageInfoDto);

    ValueResultDto getValue(String html, AnalysisInfoDto analysisInfoDto);

    String getHtmlByDB(YlResult ylResult);

    int updateYlResult(YlResult ylResult);

    int deleteHtmlToDb(YlResult ylResult);
}
