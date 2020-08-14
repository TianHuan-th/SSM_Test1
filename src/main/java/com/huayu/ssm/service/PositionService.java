package com.huayu.ssm.service;

import com.huayu.ssm.mappers.PositionMapper;
import com.huayu.ssm.pojo.Position;
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
public class   PositionService {
    @Autowired
    private PositionMapper positionMapper;

    /*查询所有职位信息*/
    public List<Position> selectAllPosition(){
        return positionMapper.selectList(null);
    }
}
