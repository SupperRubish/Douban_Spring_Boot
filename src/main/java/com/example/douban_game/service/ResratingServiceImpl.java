package com.example.douban_game.service;

import com.example.douban_game.dao.ResratingMapper;
import com.example.douban_game.entity.Resrating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResratingServiceImpl implements ResratingService{
    @Autowired
    private ResratingMapper resratingMapper;

    @Override
    public List<Resrating> selectResrating(int rr_id) {
        return resratingMapper.selectResrating(rr_id);
    }
}
