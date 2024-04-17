package com.example.douban_game.dao;


import com.example.douban_game.entity.Area;
import com.example.douban_game.entity.GenerateData;
import com.example.douban_game.entity.Language;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GenerateMapper {
    int countHistory();

    //获取所有历史生成数据信息
    List<GenerateData> getGenerateHistory(int operator_id);

    //新增fakedata表数据
    void GenerateData(GenerateData gd);

    //新增area表数据
    void GenerateAreaData(Area area);

    //新增language表数据
    void GenerateLanguageData(Language language);

    //根据操作人id寻找历史新增数据信息
    List<GenerateData> selectHistoryByOperatorIp(int operator_id);

    //通过ID获取所有数据
    List<GenerateData> selectHistoryByID(int id);

    List<Integer>selectAllFakeId();

    //获取生成数量(操作人id,模拟数据id)
    int getTotal(@Param("op_id")int op_id,@Param("f_id")int f_id);


    //测试数据生成
    void generateTestData(int op_id,int f_id)throws Exception;
}
