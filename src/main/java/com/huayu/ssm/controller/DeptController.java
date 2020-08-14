package com.huayu.ssm.controller;

import com.huayu.ssm.pojo.Dept;
import com.huayu.ssm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/7/22 8:58
 */
@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;

    @ResponseBody
    @GetMapping("/dept.do")
    public List<Dept> selectDept(){
        return deptService.queryDept();
    }
}
