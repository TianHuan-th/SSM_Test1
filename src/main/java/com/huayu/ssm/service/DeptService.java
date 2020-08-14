package com.huayu.ssm.service;

import com.huayu.ssm.mappers.DeptMapper;
import com.huayu.ssm.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/7/22 11:44
 */
@Service
@Transactional
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;

    /*查询所有部门信息*/
    public List<Dept> queryDept(){
        return deptMapper.selectList(null);
    }
}
