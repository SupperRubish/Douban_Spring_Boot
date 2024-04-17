package com.example.douban_game.entity;

import lombok.Data;

@Data
public class GameData {
    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_kind() {
        return game_kind;
    }

    public void setGame_kind(String game_kind) {
        this.game_kind = game_kind;
    }

    public int getGame_comment_number() {
        return game_comment_number;
    }

    public void setGame_comment_number(int game_comment_number) {
        this.game_comment_number = game_comment_number;
    }

    public String getGame_platform() {
        return game_platform;
    }

    public void setGame_platform(String game_platform) {
        this.game_platform = game_platform;
    }



    public double getGame_rating() {
        return game_rating;
    }

    public void setGame_rating(double game_rating) {
        this.game_rating = game_rating;
    }

    public String getGame_content() {
        return game_content;
    }

    public void setGame_content(String game_content) {
        this.game_content = game_content;
    }

    private String game_name;
    private String game_kind;
    private int game_comment_number;
    private String game_platform;

    public String getGame_image() {
        return game_image;
    }

    public void setGame_image(String game_image) {
        this.game_image = game_image;
    }

    private String game_image;
    private double game_rating;
    private String game_content;

}
