package com.huayu.ssm.pojo;

import lombok.Data;

/**
 * @author 旗木卡卡西
 * @date 2020/8/5 14:22
 */
@Data
public class Root {
    private Integer rootId;
    private String rootName;
    private Integer parentId;
    private String sources;
}
