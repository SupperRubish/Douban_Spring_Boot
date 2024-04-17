package com.example.douban_game.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;
@Data
@TableName("user")
public class User {
    @TableId(value = "user_id")
    private int userId;
    @TableField(value = "user_name")
    private  String userName;
    @TableField(value = "phone_number")
    private String phoneNumber;
    private String password;
    private String insurance;
    @TableField(value = "nick_name")
    private String nickName;



    private int user_portrait;
}
