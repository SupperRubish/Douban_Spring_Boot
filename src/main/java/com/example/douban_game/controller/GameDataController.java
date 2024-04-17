package com.example.douban_game.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.example.douban_game.entity.GameData;
import com.example.douban_game.service.GameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/gamedata")
public class GameDataController {
    @Autowired
    private GameDataService gameDataService;

    @RequestMapping("/selectAll")
    @ResponseBody
    @SaCheckLogin
    public List<GameData> selectGamedata(){
        return gameDataService.selectGameData();
    }

}
