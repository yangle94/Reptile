/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package cn.ylapl.controller;

import cn.ylapl.dao.TestDao;
import cn.ylapl.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangle
 * @version $Id IndexController.java, v 0.1 2017-02-08 下午1:30 yangle Exp $$
 */
@RestController
@RequestMapping(value = "htppclient", method = RequestMethod.POST)
public class IndexController {

    @Autowired
    private TestDao testDao;

    @RequestMapping("test")
    public void test() {
        TestEntity testEntity = testDao.save(new TestEntity().setName("张三").setPhone("11111111").setCreateUser(0).setUpdateUser(0));
        TestEntity getEntity = testDao.getOne(testEntity.getId());
        getEntity.setName("李四");
        testEntity.setName("李四");

        List<TestEntity> testEntities = testDao.findByNameIgnoreCase("张三");
        Assert.isTrue(!CollectionUtils.isEmpty(testEntities));
    }
}