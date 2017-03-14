/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package cn.ylapl.controller;

import cn.ylapl.dto.PageInfoDto;
import cn.ylapl.dto.ParamInfoDto;
import cn.ylapl.dto.ValueResultDto;
import cn.ylapl.service.HtmlService;
import cn.ylapl.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yangle
 * @version $Id IndexController.java, v 0.1 2017-02-08 下午1:30 yangle Exp $$
 */
@RestController
@RequestMapping(value = "htppclient", method = RequestMethod.POST)
public class IndexController {

    @Autowired
    private HtmlService htmlService;

    @ApiOperation(value="使用httpClient爬取页面", notes="使用httpClient爬取页面", produces = "application/json")
    @ApiImplicitParam(name = "pageInfoDto", value = "用户详细实体user", required = true, dataType = "PageInfoDto", paramType = "body")
    @RequestMapping("getValue")
    public Result getValue(@RequestBody PageInfoDto pageInfoDto) {

        Result<ValueResultDto> result = new Result<>();

        String html = htmlService.getHtml(pageInfoDto.getParamInfoDto());
        ValueResultDto resMap = htmlService.getValue(html, pageInfoDto.getAnalysisInfoDto());
        result.setCode(1);

        return result.setObject(resMap);
    }

    @ApiOperation(value="使用httpClient爬取页面", notes="使用httpClient爬取页面", produces = "application/json")
    @ApiImplicitParam(name = "pageInfoDto", value = "抓取信息", required = true, dataType = "ParamInfoDto", paramType = "body")
    @RequestMapping("getHtml")
    public Result getHtml(@RequestBody ParamInfoDto pageInfoDto) {

        Result<String> result = new Result<>();
        result.setCode(1);
        result.setObject(htmlService.getHtml(pageInfoDto));

        return result;
    }

}