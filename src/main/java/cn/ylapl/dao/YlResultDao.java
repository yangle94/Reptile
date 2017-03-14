package cn.ylapl.dao;

import cn.ylapl.entity.YlResult;

import java.util.List;

/**
 * YlResult 数据库操作
 * Created by Angle on 2017/3/14.
 */
public interface YlResultDao {

    YlResult getOneById(YlResult ylResult);

    List<YlResult> getOneByIds(YlResult ylResults);

    int insert(YlResult ylResult);

    int deleteById(YlResult ylResult);

    int update(YlResult ylResult);
}
