package cn.ylapl.dao.Impl;

import cn.ylapl.dao.YlResultDao;
import cn.ylapl.entity.YlResult;
import cn.ylapl.mapper.YlResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * YlResult 数据库操作
 * Created by Angle on 2017/3/14.
 */

@Service
@CacheConfig(cacheNames = "ylResults",keyGenerator = "wiselyKeyGenerator")
public class YlResultDaoImpl implements YlResultDao {

    @Autowired
    private YlResultMapper ylResultMapper;

    @Cacheable()
    @Override
    public YlResult getOneById(YlResult ylResult) {

        return ylResultMapper.selectByPrimaryKey(ylResult);
    }

    @Override
    public List<YlResult> getOneByIds(YlResult ylResults) {

        return ylResultMapper.selectByExample(ylResults);
    }

    @CachePut
    @Override
    public int insert(YlResult ylResult) {

        return ylResultMapper.insert(ylResult);
    }

    @CachePut
    @Override
    public int deleteById(YlResult ylResult) {
        return ylResultMapper.deleteByPrimaryKey(ylResult);
    }

    @CacheEvict
    @Override
    public int update(YlResult ylResult) {
        return ylResultMapper.updateByPrimaryKey(ylResult);
    }
}
