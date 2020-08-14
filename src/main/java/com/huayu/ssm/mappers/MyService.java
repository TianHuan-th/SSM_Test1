package com.huayu.ssm.mappers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huayu.ssm.bo.Emp1;
import com.huayu.ssm.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/7/24 16:39
 */
@Mapper
public interface MyService extends IService<Emp> {
      Integer saveEmpReturnId(Emp emp);
      IPage queryAllEmpWithDept(IPage iPage,Emp emp);
      int addEmp(Emp emp, MultipartFile multipartFile, HttpServletRequest request)throws IOException;
      int putEmp(Emp emp, MultipartFile mufile, HttpServletRequest request) throws IOException;
     public List<Emp1> positionUtils(List<Emp1> list1);
}
