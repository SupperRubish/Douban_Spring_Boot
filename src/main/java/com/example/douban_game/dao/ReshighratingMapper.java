package com.example.douban_game.dao;

import com.example.douban_game.entity.Reshighrating;
import com.example.douban_game.entity.Resrating;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReshighratingMapper {
    List<Reshighrating> selectReshighrating(int resr_id);
}
