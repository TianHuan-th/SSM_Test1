package com.huayu.ssm.service;

import com.huayu.ssm.bo.ParentData;
import com.huayu.ssm.mappers.ParentMapper;
import com.huayu.ssm.pojo.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/7/22 11:44
 */
@Service
@Transactional
public class ParentService {
    @Autowired
    private ParentMapper parentMapper;

    /*查询父级菜单*/
    public List<ParentData> getParentAll(){
       List<Parent> list=parentMapper.selectList(null);
       List<ParentData> list1=new ArrayList<>();//一会直接返回所有父级包括子集
       for(Parent parent:list){
           if(parent.getParentid()==-1){
               ParentData parentData=new ParentData();
               parentData.setTitle(parent.getDeptname());
               parentData.setId(parent.getDeptid());
               parentData.setChildren(setChildren1(list,parent.getDeptid()));
               list1.add(parentData);
           }
       }
       return list1;
    }

    public  List<ParentData> setChildren1(List<Parent> list,Integer parentId){
        //当前父级中的子集
        List<ParentData> list1=new ArrayList<>();
        //循环所有 拿到所有parentID和传的父亲的ID比较
            for(Parent parent:list){
                if(parent.getParentid().equals(parentId)){
                    ParentData parentData=new ParentData();
                    parentData.setTitle(parent.getDeptname());
                    parentData.setId(parent.getDeptid());
                    parentData.setChildren(setChildren1(list,parent.getDeptid()));
                    list1.add(parentData);
                }
            }
            return list1;
    }
}
