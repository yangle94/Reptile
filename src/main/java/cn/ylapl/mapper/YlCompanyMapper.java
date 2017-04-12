package cn.ylapl.mapper;

import cn.ylapl.entity.YlCompany;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface YlCompanyMapper extends Mapper<YlCompany>, MySqlMapper<YlCompany> {
    List<YlCompany> selectByDYId(YlCompany company);
}