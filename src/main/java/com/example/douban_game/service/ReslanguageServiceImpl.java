package com.example.douban_game.service;

import com.example.douban_game.dao.ReslanguageMapper;
import com.example.douban_game.entity.Resage;
import com.example.douban_game.entity.Reslanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReslanguageServiceImpl implements ReslanguageService{

    @Autowired
    private ReslanguageMapper reslanguageMapper;


    @Override
    public List<Reslanguage> selectReslanguage(int resl_id) {
        return reslanguageMapper.selectReslanguage(resl_id);
    }
}
