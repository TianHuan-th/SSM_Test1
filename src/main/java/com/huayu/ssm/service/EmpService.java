package com.huayu.ssm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.ssm.bo.Emp1;
import com.huayu.ssm.bo.FileUtils;
import com.huayu.ssm.mappers.EmpMapper;
import com.huayu.ssm.mappers.MyService;
import com.huayu.ssm.mappers.PositionMapper;
import com.huayu.ssm.pojo.Emp;
import com.huayu.ssm.pojo.Position;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/7/22 8:57
 */
@Service
@Transactional
public class EmpService extends ServiceImpl<EmpMapper,Emp> implements MyService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private PositionMapper positionMapper;
    Logger logger= LogManager.getLogger(EmpService.class.getName());
    /*
     * 查询所有员工及其部门和职位信息
     * */
    @Override
    public IPage queryAllEmpWithDept(IPage ipage,Emp emp) {
            QueryWrapper<Emp> wrapper=new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(emp.getName())) {
                wrapper.like("name",emp.getName());
            }
            if (!ObjectUtils.isEmpty(emp.getDid()) && emp.getDid() != 0) {
                wrapper.eq("did",emp.getDid());
            }
            if (!ObjectUtils.isEmpty(emp.getPid()) && !emp.getPid().equals("0")) {
                wrapper.like("pid",emp.getPid());
            }
            IPage page=empMapper.selectPage(ipage,wrapper);
            if(null!=page&&page.getRecords().size()>0){
                List<Emp> list2=page.getRecords();
                /*判断模糊查询出来的有没有结果*/
                if (null != list2 && list2.size() > 0) {
                    String str = "";
                    for (Emp em : list2) {
                        str += em.getEmpid() + ",";
                    }
                    String strr = str.substring(0, str.lastIndexOf(","));
                    List<Emp1> lastList=positionUtils(empMapper.selectEmpByDidLike(strr));
                    if(null!=lastList && lastList.size()>0){
                        logger.info("内谁家内小谁进来模糊查询拉");
                    }
                    page.setRecords(lastList);
                    return page;
                }
            }
            return null;
        }


    @Override
    public int addEmp(Emp emp, MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        System.out.println("_+__________________++++++++++++++++++++++++++++++++++");
        if(multipartFile.getSize()>0){
            emp.setIdcard(FileUtils.getFileName(multipartFile, request));
            logger.info("内谁家内小谁添加了："+emp.getName());
            System.out.println("上传图片");
            return empMapper.insert(emp);
        }else{
            return 0;
        }
    }

    @Override
    public int putEmp(Emp emp, MultipartFile mufile, HttpServletRequest request) throws IOException {
        System.out.println("-------------------");
        if(mufile.getSize()>0) {
            emp.setIdcard(FileUtils.getFileName(mufile, request));
            logger.info("内谁家内小谁修改了："+emp.getName());
            System.out.println("修改");
            return empMapper.updateById(emp);
        }else{
            return 0;
        }
    }

    @Override
    public List<Emp1> positionUtils(List<Emp1> list1) {
        /*循环所有员工*/
        for (Emp1 emp1 : list1) {
            /*获取员工的职位ID*/
            String str = emp1.getPid();
            List<Position> listOfPosition=positionMapper.selectPosByEid(str);
            /*循环该员工所有职位并存入一个字符串*/
            String strr = "";
            for (Position pos : listOfPosition) {
                strr += pos.getPosname() + ",";
            }
            emp1.setPosname(strr.substring(0, strr.lastIndexOf(",")));
        }
        logger.info("内谁家内小谁查询了所有的员工");
        return list1;
    }
    @Override
    public Integer saveEmpReturnId(Emp emp){
        empMapper.saveEmpReturnId(emp);
        logger.info("用户注册成功："+emp.getName());
        return emp.getEmpid();
    }
}
