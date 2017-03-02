/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.zs5s.controller;

import com.zs5s.entity.UserIdCodeDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangle
 * @version $Id IndexController.java, v 0.1 2017-02-08 下午1:30 yangle Exp $$
 */
@RestController
@RequestMapping("jpush")
public class IndexController {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录ID", required = true, dataType = "String", paramType = "Query")
    })
    @RequestMapping("/")
    public String index(@RequestParam String id) {
        return "";
    }

    /**
     * 通过极光推送 发送通知
     * @param userIdCodeMessageDTO 用户ID与设备Code对应
     * @return 推送返回码
     */
    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public String sendMesscage(@RequestBody UserIdCodeDTO userIdCodeMessageDTO) {

//        userIdCodeMessageDTO.setMessage("测试通知");
//        userIdCodeMessageDTO.setIdentifyingCodeList(new ArrayList<String>()).getIdentifyingCodeList().add("18071adc033fc19a17e");

        return userIdCodeMessageDTO.toString();
    }

    private IndexController() {
    }

    public static IndexController getIndex() {
        return new IndexController();
    }
}