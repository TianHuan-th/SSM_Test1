package com.huayu.ssm.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huayu.ssm.bo.RoleWithRoot1;
import com.huayu.ssm.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/8/6 10:08
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<RoleWithRoot1> rootOfRole(Integer rid);
}
