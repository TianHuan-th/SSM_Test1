package com.huayu.ssm.mappers;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huayu.ssm.bo.RoleWithRoot1;
import com.huayu.ssm.pojo.Role;

import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/7/24 16:39
 */

public interface RoleMapperService extends IService<Role>  {
    List<RoleWithRoot1> rootOfRole(Integer roleid);
}
