package com.example.douban_game.service;

import com.example.douban_game.entity.Reshighrating;
import com.example.douban_game.entity.Resrating;

import java.util.List;

public interface ReshighratingService {
    List<Reshighrating> selectReshighrating(int resr_id);
}
