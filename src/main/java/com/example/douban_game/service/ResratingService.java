package com.example.douban_game.service;

import com.example.douban_game.entity.Resrating;

import java.util.List;

public interface ResratingService {
    List<Resrating> selectResrating(int rr_id);
}
