package cn.ylapl.service;

import cn.ylapl.entity.PageInfo;

/**
 * 获取被爬取页面的信息
 * Created by Angle on 2017/3/5
 */
public interface HtmlService {
    String getHtml(PageInfo pageInfo);
}
