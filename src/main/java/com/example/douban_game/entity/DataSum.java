package com.example.douban_game.entity;

import lombok.Data;

import java.util.List;
@Data
public class DataSum {
    private List<Resage> resages;//储存通过年限分类的游戏数据
    private List<Resrating> resratings;//储存通过评分分类的游戏数据
    private List<Reshighrating> reshighratings;//储存高分游戏的游戏数据
    private List<Reslanguage> reslanguages;//储存通过游戏适配语言分类的游戏数据
}
