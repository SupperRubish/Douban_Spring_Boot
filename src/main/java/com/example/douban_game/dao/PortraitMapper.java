package com.example.douban_game.dao;

import com.example.douban_game.entity.Portrait;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PortraitMapper {
    String selectImg(int portrait_id);
    List<Portrait> selectAll();

}
