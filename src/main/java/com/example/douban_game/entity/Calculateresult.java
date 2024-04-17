package com.example.douban_game.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Calculateresult {
    private int calc_id;
    private int operator_id;

    @JsonFormat(pattern="yyyy-MM-dd",timezone= "GMT+8")
    private Date calc_date;
    private int calc_data_num;



}
