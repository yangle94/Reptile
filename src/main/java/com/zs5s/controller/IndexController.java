/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.zs5s.controller;

import com.zs5s.entity.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangle
 * @version $Id IndexController.java, v 0.1 2017-02-08 下午1:30 yangle Exp $$
 */
@RestController
@RequestMapping("htppclient")
public class IndexController {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录ID", required = true, dataType = "String", paramType = "Query")
    })
    @ApiOperation(value="使用httpClient爬取页面", notes="使用httpClient爬取页面")
    @RequestMapping("getHtml")
    public String getHtml(@RequestBody PageInfo pageInfo) {
        return "";
    }

}