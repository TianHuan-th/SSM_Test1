package com.huayu.ssm.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.ssm.bo.RoleWithRoot1;
import com.huayu.ssm.mappers.RoleMapper;
import com.huayu.ssm.mappers.RoleMapperService;
import com.huayu.ssm.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/8/6 10:09
 */

@Service
@Transactional
public class RoleService extends ServiceImpl<RoleMapper,Role> implements RoleMapperService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleWithRoot1> rootOfRole(Integer roleid){
        return roleMapper.rootOfRole(roleid);
    }
}
