package com.example.douban_game.service;

import com.example.douban_game.dao.GameDataMapper;
import com.example.douban_game.entity.GameData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IGameDataServiceImpl implements GameDataService{
    @Autowired
    private GameDataMapper gameDataMapper;


    @Override
    public List<GameData> selectGameData() {
        return gameDataMapper.selectGameData();
    }



}
