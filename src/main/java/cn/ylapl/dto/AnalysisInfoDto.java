package cn.ylapl.dto;

import cn.ylapl.util.GsonUtil;

import java.io.Serializable;
import java.util.Set;

/**
 *  分析网页所需对象
 * Created by Angle on 2017/3/13.
 */
public class AnalysisInfoDto implements Serializable {
    //id
    private Set<String> ids;
    private Set<String> xpath;
    //class
    private Set<String> classstr;

    public AnalysisInfoDto() {}

    public AnalysisInfoDto(Set<String> ids, Set<String> xpath, Set<String> classstr) {
        this.ids = ids;
        this.xpath = xpath;
        this.classstr = classstr;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }

    public Set<String> getIds() {
        return ids;
    }

    public AnalysisInfoDto setIds(Set<String> ids) {
        this.ids = ids;
        return this;
    }

    public Set<String> getXpath() {
        return xpath;
    }

    public AnalysisInfoDto setXpath(Set<String> xpath) {
        this.xpath = xpath;
        return this;
    }

    public Set<String> getClassstr() {
        return classstr;
    }

    public AnalysisInfoDto setClassstr(Set<String> classstr) {
        this.classstr = classstr;
        return this;
    }
}
