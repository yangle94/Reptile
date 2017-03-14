package cn.ylapl.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * 访问网页参数
 * Created by Angle on 2017/3/13.
 */
public class ParamInfoDto implements Serializable {

    private String url;
    private String method;
    private Map<String,String> param;
    private Map<String,String> header;

    public ParamInfoDto() {
    }

    public ParamInfoDto(String url, String method, Map<String, String> param, Map<String, String> header) {
        this.url = url;
        this.method = method;
        this.param = param;
        this.header = header;
    }

    @Override
    public String toString() {
        return "ParamInfoDto{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", param=" + param +
                ", header=" + header +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public ParamInfoDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public ParamInfoDto setMethod(String method) {
        this.method = method;
        return this;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public ParamInfoDto setParam(Map<String, String> param) {
        this.param = param;
        return this;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public ParamInfoDto setHeader(Map<String, String> header) {
        this.header = header;
        return this;
    }
}
