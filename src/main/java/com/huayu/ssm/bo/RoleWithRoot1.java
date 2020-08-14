package com.huayu.ssm.bo;

import lombok.Data;

/**
 * @author 旗木卡卡西
 * @date 2020/8/6 16:37
 */
@Data
public class RoleWithRoot1 {
    private Integer roleid;
    private Integer rootid;
    private String rootname;
    private Integer parentid;
    private String sources;
}
