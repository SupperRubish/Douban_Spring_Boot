package com.example.douban_game.dao;

import com.example.douban_game.entity.Resrating;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResratingMapper {
    List<Resrating> selectResrating(int rr_id);
}
