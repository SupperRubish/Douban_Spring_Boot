package com.example.douban_game.controller;

import com.example.douban_game.entity.Resrating;
import com.example.douban_game.service.ResratingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Resrating")
public class ResratingController {
    @Autowired
    private ResratingService resratingService;


    @RequestMapping("/selectResrating")
    @ResponseBody
    public List<Resrating> selectResrating(int rr_id) {
        return resratingService.selectResrating(rr_id);
    }
}
