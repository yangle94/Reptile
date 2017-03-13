package com.zs5s.service;

import com.zs5s.entity.PageInfo;

/**
 * 获取被爬取页面的信息
 * Created by Angle on 2017/3/5
 */
public interface HtmlService {
    String getHtml(PageInfo pageInfo);
}
