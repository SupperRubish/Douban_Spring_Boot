package com.example.douban_game.entity;

public class Reslanguage {


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getJapanese() {
        return japanese;
    }

    public void setJapanese(int japanese) {
        this.japanese = japanese;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getOther_language() {
        return other_language;
    }

    public void setOther_language(int other_language) {
        this.other_language = other_language;
    }

    public int getResl_id() {
        return resl_id;
    }

    public void setResl_id(int resl_id) {
        this.resl_id = resl_id;
    }

    private int resl_id;
    private String type;
    private int chinese;
    private int japanese;
    private int english;
    private int other_language;
}
