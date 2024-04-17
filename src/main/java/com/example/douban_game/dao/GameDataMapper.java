package com.example.douban_game.dao;

import com.example.douban_game.entity.GameData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface GameDataMapper {
    List<GameData> selectGameData();
}
