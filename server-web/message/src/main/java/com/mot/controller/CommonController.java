package com.mot.controller;

import com.mot.common.utils.ChineseName;
import com.mot.common.utils.SessionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author tianfx
 * @date 2021/6/16 2:14 下午
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @GetMapping("randomName")
    public String randomName(){
        return ChineseName.randomName();
    }

    @GetMapping("onlineCount")
    public ArrayList<String> onlineCount(){
        ArrayList<String> list = new ArrayList<>();
        SessionUtils.getAllSession().forEach((k,v)->list.add(v.fromName));
        return list;
    }
}
