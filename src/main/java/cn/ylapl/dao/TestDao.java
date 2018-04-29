package cn.ylapl.dao;

import cn.ylapl.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * date: 2018/4/28
 * time: 下午4:59
 * author: Angle
 */
public interface TestDao extends JpaRepository<TestEntity, Long> {

    List<TestEntity> findByNameIgnoreCase(String name);
}
