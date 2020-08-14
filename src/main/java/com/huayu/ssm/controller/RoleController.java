package com.huayu.ssm.controller;

import com.huayu.ssm.bo.LayuiUtils;
import com.huayu.ssm.bo.ParentData;
import com.huayu.ssm.bo.RoleWithRoot1;
import com.huayu.ssm.mappers.RoleMapperService;
import com.huayu.ssm.pojo.Role;
import com.huayu.ssm.service.RootService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/8/6 10:23
 */
@Controller
public class RoleController {
    @Autowired
    private RoleMapperService roleService;
    @Autowired
    private RootService1 rootService;

    @GetMapping("/queryRole.do")
    @ResponseBody
    public LayuiUtils queryRole(){
        LayuiUtils utils=new LayuiUtils();
        utils.setCode(0);
        utils.setMsg("查询成功");
        utils.setCount(0);
        utils.setData(roleService.list(null));
        return utils;
    }
    @PostMapping("/addRole.do")
    public void addRole(Role role){
        roleService.save(role);
    }

    @DeleteMapping("/deleteRole/{id}.do")
    @ResponseBody
        public boolean deleteRole(@PathVariable Integer id){
        return roleService.removeById(id);
    }

    @PutMapping("updateRole.do")
    public void updateRole(Role role){
      roleService.updateById(role);
    }

    @GetMapping("/upRole/{id}.do")
    public ModelAndView getRole(@PathVariable Integer id){
        ModelAndView mv=new ModelAndView();
        mv.addObject("role",roleService.getById(id));
        mv.setViewName("/updateRole.html");
        return mv;
    }

    /*树形组件权限*/
    @RequestMapping("/rwithr.do")
    @ResponseBody
    public List<ParentData> getRoleRoot(Role role){
      /*  QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("parentid",-1);
        List<Root> allRoot=rootService.list(wrapper);
        List<RoleWithRoot1> list=roleService.rootOfRole(role.getRoleid());
        List<String> arr=new ArrayList<>();
        for(RoleWithRoot1 roleWithRoot1:list){
            System.out.println(roleWithRoot1.getRootname());
            arr.add(roleWithRoot1.getRootid().toString());
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("rrList",arr);
        System.out.println(arr);
        mv.addObject("rolename",role.getRolename());
        mv.addObject("roleid",role.getRoleid());
        mv.addObject("rootList",allRoot);
        System.out.println(allRoot);
        mv.setViewName("/RoleWithRoot.html");*/
        return rootService.getRootAllData();
    }


    @RequestMapping("/rwithrr.do")
    public ModelAndView getRoleRoot2(Role role){
        List<RoleWithRoot1> list=roleService.rootOfRole(role.getRoleid());
        String str="";
         for(RoleWithRoot1 roleWithRoot1:list){
             str+=roleWithRoot1.getRootid()+",";
        }
         String strr=str.substring(0,str.lastIndexOf(","));
        ModelAndView mv=new ModelAndView();
        mv.addObject("rrList",strr);
        mv.addObject("rolename",role.getRolename());
        mv.addObject("roleid",role.getRoleid());
        mv.setViewName("/RoleWithRoot.html");
        return  mv;
    }
}
