package com.example.douban_game.controller;

import com.example.douban_game.entity.Reshighrating;
import com.example.douban_game.entity.Resrating;
import com.example.douban_game.service.ReshighratingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Reshighrating")
public class ReshighratingController {
    @Autowired
    private ReshighratingService reshighratingService;


    @RequestMapping("/select")
    @ResponseBody
    public List<Reshighrating> selectReshighrating(int resr_id) {
        return reshighratingService.selectReshighrating(resr_id);
    }
}
