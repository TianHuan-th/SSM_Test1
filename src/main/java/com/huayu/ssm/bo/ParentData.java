package com.huayu.ssm.bo;

import lombok.Data;

import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/8/1 17:05
 */
@Data
public class ParentData {
    private Integer id;
    private String title;
    private List children;
}
