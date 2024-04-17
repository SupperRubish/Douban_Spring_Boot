package com.example.douban_game.service;

import com.example.douban_game.entity.GameData;

import java.io.IOException;
import java.util.List;

public interface GameDataService {
    List<GameData> selectGameData();

//    Object compute() throws IOException;
}
