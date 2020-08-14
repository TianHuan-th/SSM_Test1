package com.huayu.ssm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 旗木卡卡西
 * @date 2020/8/5 14:21
 */
@Data
@TableName("role")
public class Role {
    @TableId(value = "roleid",type = IdType.AUTO)
    private Integer roleid;
    private String rolename;
}
