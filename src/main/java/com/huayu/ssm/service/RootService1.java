package com.huayu.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huayu.ssm.bo.ParentData;
import com.huayu.ssm.pojo.Root;

import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/8/7 10:57
 */
public interface RootService1 extends IService<Root> {
    List<ParentData> getRootAllData();
}
