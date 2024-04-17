package com.example.douban_game.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.douban_game.entity.*;
import com.example.douban_game.service.ResageService;
import com.example.douban_game.service.ReshighratingService;
import com.example.douban_game.service.ReslanguageService;
import com.example.douban_game.service.ResratingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.Int;

import java.util.List;

@Controller
@RequestMapping("/DataSum")
public class DataSumController {
    @Autowired
    private ReslanguageService reslanguageService;
    @Autowired
    private ResageService resageService;
    @Autowired
    private ReshighratingService reshighratingService;
    @Autowired
    private ResratingService resratingService;


    @RequestMapping("/sum")
    @ResponseBody
    @SaCheckLogin //判断当前操作是否是用户登陆后执行
    public DataSum Select (){
        int id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));//获取操作用户的id
        //调用spark脚本对游戏数据进行分析
        List<Resrating> resratingList = resratingService.selectResrating(id);
        List<Reslanguage> reslanguageList = reslanguageService.selectReslanguage(id);
        List<Reshighrating>reshighratingLis =  reshighratingService.selectReshighrating(id);
        List<Resage> resages = resageService.selectResage(id);
        DataSum dataSum = new DataSum(); //new一个对象，该对象用于方便前端获取JSON格式数据
        dataSum.setResages(resages);
        dataSum.setReshighratings(reshighratingLis);
        dataSum.setReslanguages(reslanguageList);
        dataSum.setResratings(resratingList);
        return dataSum;
    }
}
