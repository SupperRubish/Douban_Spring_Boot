package com.example.douban_game.service;

import com.example.douban_game.entity.Portrait;

import java.util.List;

public interface IPortraitService {
    String selectImg(int portrait_id);

    List<Portrait> selectAll();
}
