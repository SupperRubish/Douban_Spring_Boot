package com.example.douban_game.service;

import com.example.douban_game.dao.CalculateresultMapper;
import com.example.douban_game.entity.Calculateresult;
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
public class ICalculateresultServiceImpl implements CalculateresultService{
    @Autowired
    private CalculateresultMapper calculateresultMapper;

    @Override
    public List<Calculateresult> selectCalculateresult(int operator_id) {
        return calculateresultMapper.selectCalculateresult(operator_id);
    }

    @Override
    public void addnewCalculate(Calculateresult calculateresult) {

        calculateresultMapper.addnewCalculate(calculateresult);
    }






}
