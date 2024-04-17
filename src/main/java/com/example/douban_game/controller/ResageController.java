package com.example.douban_game.controller;

import com.example.douban_game.entity.Resage;
import com.example.douban_game.service.ResageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Resage")
public class ResageController {
    @Autowired
    private ResageService resageService;

    @RequestMapping("/select")
    @ResponseBody
    public List<Resage> selectResage(int ra_id) {
        return resageService.selectResage(ra_id);
    }




}
