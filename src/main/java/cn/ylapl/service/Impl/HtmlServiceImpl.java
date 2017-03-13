package cn.ylapl.service.Impl;

import cn.ylapl.entity.PageInfo;
import cn.ylapl.service.HtmlService;

/**
 * Created by Angle on 2017/3/5.
 */
public class HtmlServiceImpl implements HtmlService {

    @Override
    public String getHtml(PageInfo pageInfo) {
        if(pageInfo.getMethod().equalsIgnoreCase("post")) {

        }
        return null;
    }
}
