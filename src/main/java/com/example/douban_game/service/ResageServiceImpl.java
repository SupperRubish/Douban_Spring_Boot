package com.example.douban_game.service;

import com.example.douban_game.dao.ResageMapper;
import com.example.douban_game.entity.Resage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResageServiceImpl implements ResageService{
    @Autowired
    private ResageMapper resageMapper;


    @Override
    public List<Resage> selectResage(int ra_id) {
        return resageMapper.selectResage(ra_id);
    }
}
