package com.example.douban_game.dao;

import com.example.douban_game.entity.Reslanguage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReslanguageMapper {
    List<Reslanguage> selectReslanguage(int resl_id);
}
