package com.huayu.ssm.controller;

import com.huayu.ssm.pojo.Position;
import com.huayu.ssm.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 旗木卡卡西
 * @date 2020/7/22 8:58
 */
@Controller
public class PositionController {
    @Autowired
    private PositionService positionService;

    @ResponseBody
    @GetMapping("/position.do")
    public List<Position> queryAllPosition(){
       return positionService.selectAllPosition();
    }
}
