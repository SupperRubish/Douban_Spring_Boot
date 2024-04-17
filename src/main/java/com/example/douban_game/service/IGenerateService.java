package com.example.douban_game.service;



import com.example.douban_game.entity.Area;
import com.example.douban_game.entity.GenerateData;
import com.example.douban_game.entity.Language;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IGenerateService {
    int countHistory();

    List<GenerateData> getGenerateHistory(int operator_id);

    void GenerateData(GenerateData gd);

    //新增area表数据
    void GenerateAreaData(Area area);

    //新增language表数据
    void GenerateLanguageData(Language language);

    List<GenerateData> selectHistoryByOperatorIp(int operator_id);

    //通过ID获取所有数据
    List<GenerateData> selectHistoryByID(int id);


    List<Integer>selectAllFakeId();


    //分页查询1
    List<GenerateData> findAllDataByPageF(int operator_id, int pageNum,int pageSize);
    //分页查询2
    PageInfo<GenerateData> findAllDataByPageS(int operator_id, int pageNum, int pageSize);


    //测试数据生成
    void generateTestData(int op_id,int f_id)throws Exception;
}
