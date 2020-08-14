package com.huayu.ssm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huayu.ssm.bo.FileUtils;
import com.huayu.ssm.bo.LayuiUtils;
import com.huayu.ssm.bo.ParentData;
import com.huayu.ssm.mappers.MyService;
import com.huayu.ssm.pojo.Dept;
import com.huayu.ssm.pojo.Emp;
import com.huayu.ssm.pojo.Emppwd;
import com.huayu.ssm.pojo.Position;
import com.huayu.ssm.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/7/22 8:58
 */
@Controller
public class EmpController {
    @Autowired
    private MyService empService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private EmppwdService emppwdService;
    Logger logger= LogManager.getLogger(EmpController.class.getName());
    @ResponseBody
    @GetMapping("/emp.do")
    public LayuiUtils queryAll(Integer page,Integer limit,Emp emp){
        if(null==page){
            page=1;
        }
        IPage iPage=new Page(page,limit);
        IPage iPage1=empService.queryAllEmpWithDept(iPage,emp);
        if(null!=iPage1){
            LayuiUtils utils=new LayuiUtils();
            utils.setCode(0);
            utils.setMsg("查询成功");
            utils.setCount((int) iPage1.getTotal());
            utils.setData(iPage1.getRecords());
            return utils;
        }else{
            LayuiUtils utils1=new LayuiUtils();
            utils1.setCode(0);
            utils1.setMsg("查询无结果");
            utils1.setCount(0);
            utils1.setData(null);
            return utils1;
        }
    }

    @PostMapping("/emp.do")
    public String addEmp(Emp emp/*, @RequestParam("idfile") MultipartFile mufile, HttpServletRequest request*/) throws IOException {
        if(empService.save(emp)){
            logger.info("添加成功！！！！");
            return "redirect:templeate/zhu1.html";
        }else{
         return "redirect:templeate/zhu1.html";
        }

    }

    /*查询*/
    @ResponseBody
    @GetMapping("/emp/{id}.do")
    public ModelAndView getEmp(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse responsed)throws  IOException, ServletException {
       ModelAndView mv=new ModelAndView();
        Emp emp=empService.getById(id);
       List<Dept> deptAll=deptService.queryDept();
       List<Position> posAll=positionService.selectAllPosition();
       String str[]=emp.getPid().split(",");
       List<String> arr=new ArrayList<>();
       for (int i=0;i<str.length;i++){
           arr.add(str[i]);
       }
       mv.addObject("arr",arr);
       mv.addObject("emp",emp);
       mv.addObject("deptAll",deptAll);
       mv.addObject("posAll",posAll);
       /*request.setAttribute("emp",emp);
       request.setAttribute("deptAll",deptAll);
       request.setAttribute("posAll",posAll);
      request.getRequestDispatcher("/templeate/updateEmp.html").forward(request,responsed);*/
       mv.setViewName("/updateEmp1.html");
       return mv;
    }
    /*修改用户*/
    @PutMapping("/emp.do")
    public void putEmp(Emp emp,/* @RequestParam("idfile") MultipartFile mufile, HttpServletRequest request,*/HttpServletResponse response) throws  IOException{
       /* int i=empService.putEmp(emp,mufile,request);*/
        empService.updateById(emp);
    }
    /*删除员工*/
    @DeleteMapping("/emp/{id}.do")
    @ResponseBody
    public boolean deleteEmp(@PathVariable Integer id){
        return  empService.removeById(id);
    }

    /*Layui的文件上传*/
    @PostMapping("/fileupload.do")
    @ResponseBody
    public LayuiUtils fileupload(@RequestParam("file") MultipartFile mufile,HttpServletRequest request) throws IOException{
        LayuiUtils layuiUtils=new LayuiUtils();
        layuiUtils.setMsg(FileUtils.getFileName(mufile,request));
        return layuiUtils;
    }

    /*批量删除*/
    @GetMapping("deleteMore.do")
    public void deleteMore(String arr,HttpServletResponse response) throws  IOException{
        if(!StringUtils.isEmpty(arr)){
           String[] str= arr.split(",");
           List<String> strList=new ArrayList<>();
           for(int i=0;i<str.length;i++){
               strList.add(str[i]);
           }
            if(empService.removeByIds(strList)){
                response.getWriter().write("OK");
            }else{
                response.getWriter().write("NO");
            }
        }
    }

    @GetMapping("/getParent.do")
    @ResponseBody
    public List<ParentData> getParent(){
    return parentService.getParentAll();
    }

    /*登录校验*/
    @RequestMapping("/login.do")
    public String empLogin(Emppwd emppwd){
        if(!StringUtils.isEmpty(emppwd.getEaccount()) && !StringUtils.isEmpty(emppwd.getEpassword())){
            UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(emppwd.getEaccount(), emppwd.getEpassword());
            Subject subject=SecurityUtils.getSubject();
            try {
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                /*未知账号异常*/
           return "redirect:templeate/login.html";
            }catch (IncorrectCredentialsException ice) {
                /*凭证有误异常*/
                return "redirect:templeate/login.html";
            }
            /*验证成功需要重定向  因为主页可以直接重新加载*/
            return "redirect:templeate/zhu1.html";
        }else{
            return "redirect:templeate/login.html";
        }
    }


    @PostMapping("/zhuce.do")
    public void zhuceEmp(Emp emp,@RequestParam("eaccount") String eaccount,@RequestParam("epassword") String epassword){
    emppwdService.saveAllEmp(emp,eaccount,epassword);
    }
}
