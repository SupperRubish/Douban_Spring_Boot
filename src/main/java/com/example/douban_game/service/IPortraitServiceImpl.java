package com.example.douban_game.service;

import com.example.douban_game.dao.PortraitMapper;
import com.example.douban_game.entity.Portrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPortraitServiceImpl implements IPortraitService{

    @Autowired
    private PortraitMapper portraitMapper;

    @Override
    public String selectImg(int portrait_id) {
        return portraitMapper.selectImg(portrait_id);
    }

    @Override
    public List<Portrait> selectAll() {
        return portraitMapper.selectAll();
    }
}
