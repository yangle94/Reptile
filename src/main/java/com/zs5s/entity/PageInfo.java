package com.zs5s.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 关于要爬取得页面的信息
 * Created by Angle on 2017/3/5.
 */
public class PageInfo implements Serializable {

    private String url;
    private String method;
    private List<String> tags;

    public String getUrl() {
        return url;
    }

    public PageInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public PageInfo setMethod(String method) {
        this.method = method;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public PageInfo setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", tags=" + tags +
                '}';
    }
}
