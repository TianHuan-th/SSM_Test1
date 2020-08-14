package com.huayu.ssm.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huayu.ssm.bo.Emp1;
import com.huayu.ssm.bo.PageuUtils;
import com.huayu.ssm.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {

    void saveEmpReturnId(Emp emp);

    List<Emp1> selectEmpByDid(PageuUtils pageuUtils);

    List<Emp1> selectEmpByDidLike(String str);

}