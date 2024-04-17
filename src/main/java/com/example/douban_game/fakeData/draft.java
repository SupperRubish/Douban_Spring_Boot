package com.example.douban_game.fakeData;

import org.apache.spark.sql.catalyst.expressions.Md5;
import org.springframework.util.DigestUtils;

public class draft {
    public static void main(String[] args) {
        String a = "123456";
        String b = DigestUtils.md5DigestAsHex(a.getBytes());
        System.out.println(b);
    }
}
