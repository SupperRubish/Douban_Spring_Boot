package com.example.douban_game.dao;

import com.example.douban_game.entity.Calculateresult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CalculateresultMapper {
    List<Calculateresult> selectCalculateresult(int operator_id);
    void addnewCalculate(Calculateresult calculateresult);
}
