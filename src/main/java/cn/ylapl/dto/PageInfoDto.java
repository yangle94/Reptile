package cn.ylapl.dto;

import java.io.Serializable;

/**
 * 关于要爬取得页面的信息
 * Created by Angle on 2017/3/5.
 */
public class PageInfoDto implements Serializable {

    private ParamInfoDto paramInfoDto;

    private AnalysisInfoDto analysisInfoDto;

    public ParamInfoDto getParamInfoDto() {
        return paramInfoDto;
    }

    public PageInfoDto setParamInfoDto(ParamInfoDto paramInfoDto) {
        this.paramInfoDto = paramInfoDto;
        return this;
    }

    public AnalysisInfoDto getAnalysisInfoDto() {
        return analysisInfoDto;
    }

    public PageInfoDto setAnalysisInfoDto(AnalysisInfoDto analysisInfoDto) {
        this.analysisInfoDto = analysisInfoDto;
        return this;
    }

    public PageInfoDto(ParamInfoDto paramInfoDto, AnalysisInfoDto analysisInfoDto) {

        this.paramInfoDto = paramInfoDto;
        this.analysisInfoDto = analysisInfoDto;
    }

    public PageInfoDto() {

    }

    @Override
    public String toString() {
        return "PageInfoDto{" +
                "paramInfoDto=" + paramInfoDto +
                ", analysisInfoDto=" + analysisInfoDto +
                '}';
    }
}
