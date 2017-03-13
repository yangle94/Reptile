package com.zs5s.service.Impl;

import com.zs5s.entity.PageInfo;
import com.zs5s.service.HtmlService;

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
