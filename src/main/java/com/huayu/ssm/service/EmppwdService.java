package com.huayu.ssm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huayu.ssm.mappers.EmpWithRoleMapper;
import com.huayu.ssm.mappers.EmppwdMapper;
import com.huayu.ssm.mappers.MyService;
import com.huayu.ssm.pojo.Emp;
import com.huayu.ssm.pojo.EmpWithRole;
import com.huayu.ssm.pojo.Emppwd;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 旗木卡卡西
 * @date 2020/8/4 9:40
 */
@Service
@Transactional
public class EmppwdService {
    @Autowired
    private EmppwdMapper emppwdMapper;
    @Autowired
    private EmpWithRoleMapper empWithRoleMapper;
    @Autowired
    private MyService empService;

    public Emppwd queryEmppwd(QueryWrapper queryWrapper){
        return emppwdMapper.selectOne(queryWrapper);
    }

    public Integer saveAllEmp(Emp emp,String eaccount,String epassword){
        /*盐值加密*/
        Object password=new SimpleHash("MD5",epassword,eaccount,1024);
        Integer empid=empService.saveEmpReturnId(emp);
        Emppwd emppwd=new Emppwd();
        emppwd.setEaccount(eaccount);
        System.out.println(password);
        System.out.println(password.toString());
        emppwd.setEpassword(password.toString());
        emppwd.setEid(empid);
        emppwdMapper.insert(emppwd);
        EmpWithRole empWithRole=new EmpWithRole();
        empWithRole.setEId(empid);
        empWithRole.setRId(1002);
        return empWithRoleMapper.insert(empWithRole);
    }
}
