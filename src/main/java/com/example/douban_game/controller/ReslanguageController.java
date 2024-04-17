package com.example.douban_game.controller;

import com.example.douban_game.entity.Reslanguage;
import com.example.douban_game.service.ReslanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Reslanguage")
public class ReslanguageController {
    @Autowired
    private ReslanguageService reslanguageService;





    @RequestMapping("/select")
    @ResponseBody
    public List<Reslanguage> selectReslanguage(int resl_id) {
        return reslanguageService.selectReslanguage(resl_id);
    }
}
