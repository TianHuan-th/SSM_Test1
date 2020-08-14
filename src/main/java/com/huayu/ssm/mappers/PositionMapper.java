package com.huayu.ssm.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huayu.ssm.pojo.Position;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PositionMapper extends BaseMapper<Position> {

    List<Position> selectPosByEid(String pos);
}