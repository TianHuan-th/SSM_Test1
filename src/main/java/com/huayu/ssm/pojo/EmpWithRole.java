package com.huayu.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 旗木卡卡西
 * @date 2020/8/5 14:24
 */
@Data
@TableName("empwithrole")
public class EmpWithRole {
    @TableId(value = "erid",type = IdType.AUTO)
    private Integer Erid;
    private Integer eId;
    private Integer rId;
}
