package com.huayu.ssm.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.ssm.bo.ParentData;
import com.huayu.ssm.mappers.RootMapper;
import com.huayu.ssm.pojo.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/8/6 16:53
 */
@Service
@Transactional
public class RootService extends ServiceImpl<RootMapper, Root> implements RootService1{
    @Autowired
    private RootMapper rootMapper;
    @Override
    public List<ParentData> getRootAllData() {
        List<Root> rootList=rootMapper.selectList(null);
        List<ParentData> parentDataList=new ArrayList<>();
        for(Root root:rootList){
            if(root.getParentId()==-1){
                ParentData parentData=new ParentData();
                parentData.setId(root.getRootId());
                parentData.setTitle(root.getRootName());
                parentData.setChildren(setChildren1(rootList,root.getRootId()));
                parentDataList.add(parentData);
            }
        }
        return parentDataList;
    }


    public  List<ParentData> setChildren1(List<Root> list, Integer parentId){
        //当前父级中的子集
        List<ParentData> list1=new ArrayList<>();
        //循环所有 拿到所有parentID和传的父亲的ID比较
        for(Root root:list){
            if(root.getParentId().equals(parentId)){
                ParentData parentData=new ParentData();
                parentData.setTitle(root.getRootName());
                parentData.setId(root.getRootId());
                parentData.setChildren(setChildren1(list,root.getRootId()));
                list1.add(parentData);
            }
        }
        return list1;
    }
}
