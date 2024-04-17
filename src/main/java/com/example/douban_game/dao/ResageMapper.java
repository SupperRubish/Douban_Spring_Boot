package com.example.douban_game.dao;

import com.example.douban_game.entity.Resage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResageMapper {
    List<Resage> selectResage(int ra_id);
}
