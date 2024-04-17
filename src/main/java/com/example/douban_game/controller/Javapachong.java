package com.example.douban_game.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.fastjson.JSON;
import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/JavaPachong")
public class Javapachong {
    static String FileName="";
    @RequestMapping("/top5")
    @ResponseBody
    @SaCheckLogin
    private static List getCid() {
        String CidUrl="https://www.douban.com/j/ilmen/game/search?genres=&platforms=&q=&sort=rating&more=1";
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(CidUrl);
            URLConnection urlConnection = urlObject.openConnection();
            urlConnection.setRequestProperty("User-Agent","Mozilla/4.76");
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
//                System.out.println(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.toString();
        Map maps = (Map) JSON.parseObject(String.valueOf(json));
        System.out.println(maps);
        List lists = (List)maps.get("games");
        ArrayList<Map> listAll = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Map mapss = (Map) lists.get(i);
            System.out.println(mapss);
            listAll.add(mapss);

        }
        return listAll;

    }

    public static void main(String[] args) {
        getCid();
    }
}
