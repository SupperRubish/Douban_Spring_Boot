package com.example.douban_game.entity;

public class Portrait {
    public int getPortrait_id() {
        return portrait_id;
    }

    public void setPortrait_id(int portrait_id) {
        this.portrait_id = portrait_id;
    }

    public String getPortrait_name() {
        return portrait_name;
    }

    public void setPortrait_name(String portrait_name) {
        this.portrait_name = portrait_name;
    }

    public String getPortrait_link() {
        return portrait_link;
    }

    public void setPortrait_link(String portrait_link) {
        this.portrait_link = portrait_link;
    }

    private int portrait_id;
    private String portrait_name;
    private String portrait_link;
}
