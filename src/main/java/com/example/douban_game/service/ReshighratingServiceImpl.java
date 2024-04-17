package com.example.douban_game.service;

import com.example.douban_game.dao.ReshighratingMapper;
import com.example.douban_game.entity.Reshighrating;
import com.example.douban_game.entity.Resrating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReshighratingServiceImpl implements ReshighratingService {
    @Autowired
    private ReshighratingMapper reshighratingMapper;


    @Override
    public List<Reshighrating> selectReshighrating(int resr_id) {
        return reshighratingMapper.selectReshighrating(resr_id);
    }
}
