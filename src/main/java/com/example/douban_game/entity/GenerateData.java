package com.example.douban_game.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class GenerateData {
    public int fake_id;

    public int operator_id;

    public int generate_number;

    @JsonFormat(pattern="yyyy-MM-dd",timezone= "GMT+8")
    public Date generate_date;

    public int action;

    public int roleplay;

    public int adventure;

    public int alpinia;

    public Area area;

    public Language language;


}
