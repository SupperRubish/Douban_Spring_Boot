package com.example.douban_game.service;

import com.example.douban_game.entity.Calculateresult;

import java.io.IOException;
import java.util.List;

public interface CalculateresultService {
    List<Calculateresult> selectCalculateresult(int operator_id);
    void addnewCalculate(Calculateresult calculateresult);

}
