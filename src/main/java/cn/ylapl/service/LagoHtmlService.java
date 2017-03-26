package cn.ylapl.service;

import cn.ylapl.dto.ParamInfoDto;

/**
 * 拉勾网
 * Created by Angle on 2017/3/26.
 */
public interface LagoHtmlService {

    /**
     * 获取拉勾网公司数据
     * @return
     */
    String getCompanies(ParamInfoDto pageInfoDto);
}
