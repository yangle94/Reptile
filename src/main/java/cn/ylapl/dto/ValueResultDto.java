package cn.ylapl.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Angle on 2017/3/13.
 */
public class ValueResultDto implements Serializable{
    private Map<String,String> idRes;
    private Map<String,String> clsRes;

    public ValueResultDto() {
    }

    @Override
    public String toString() {
        return "ValueResultDto{" +
                "idRes=" + idRes +
                ", clsRes=" + clsRes +
                '}';
    }

    public Map<String, String> getIdRes() {
        return idRes;
    }

    public ValueResultDto setIdRes(Map<String, String> idRes) {
        this.idRes = idRes;
        return this;
    }

    public Map<String, String> getClsRes() {
        return clsRes;
    }

    public ValueResultDto setClsRes(Map<String, String> clsRes) {
        this.clsRes = clsRes;
        return this;
    }

    public ValueResultDto(Map<String, String> idRes, Map<String, String> clsRes) {

        this.idRes = idRes;
        this.clsRes = clsRes;
    }
}
