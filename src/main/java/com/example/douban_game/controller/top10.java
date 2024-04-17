package com.example.douban_game.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.example.douban_game.service.*;
import org.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/top")
public class top10 { ;




    @RequestMapping("/test")
    @ResponseBody
    public String sss(){

        LanguageAnalysis.languageAnalysis(44);
        return "nb";
    }




    @RequestMapping("/top3")
    @ResponseBody
    public ArrayList top(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            System.out.println(session.getAttribute("id"));
            System.out.println("start");
            //下面一行，前面的是调用环境中的python（注意不要用默认的，给个地址），后面的是要运行的python代码，我这里是1.py
            String[] args1=new String[]{"D:\\anaconda\\python","D:\\Competition\\pythonproject\\pythonProject\\数据清洗与采集\\豆瓣游戏\\douban_top.py"};
            Process pr=Runtime.getRuntime().exec(args1);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    pr.getInputStream(),"gb2312"));
            String line;
            int num = 1;
            ArrayList<String> Name = new ArrayList<String>();
            ArrayList<String> Url = new ArrayList<String>();
            ArrayList<String> Img = new ArrayList<String>();
            ArrayList<String> Content = new ArrayList<String>();
            ArrayList<List> sum = new ArrayList<List>();
            while ((line = in.readLine()) != null) {
                if (num==1||num==5||num==9||num==13||num==17){
                    Name.add(line);
                }
                if (num==2||num==6||num==10||num==14||num==18){
                    Url.add(line);
                }
                if (num==3||num==7||num==11||num==15||num==19){
                    Img.add(line);
                }
                if (num==4||num==8||num==12||num==16||num==20){
                    Content.add(line);
                }
                num++;
            }
            sum.add(Name);
            sum.add(Url);
            sum.add(Img);
            sum.add(Content);

            in.close();
            pr.waitFor();
            System.out.println("end");
            return sum;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/top5")
    @ResponseBody
    public ArrayList top1(){
        ArrayList<String> Name = new ArrayList<String>();
        ArrayList<String> Url = new ArrayList<String>();
        ArrayList<String> Img = new ArrayList<String>();
        ArrayList<String> Content = new ArrayList<String>();
        ArrayList<List> sum = new ArrayList<List>();
        Name.add("塞尔达传说：旷野之息 ゼルダの�徽h ブレス オブ ザ ワイルド");
        Name.add("塞尔达传说 旷野之息 - 英杰们的诗篇 ゼルダの�徽h ブレス オブ ザ ワイルド 英�埭郡沥卧�");
        Name.add("双人成行 It Takes Two");
        Name.add("侠盗猎车手5 Grand Theft Auto V");
        Name.add("巫师3：狂猎 血与酒 The Witcher 3: Wild Hunt - Blood and Wine");

        Url.add("https://www.douban.com/game/26817171/");
        Url.add("https://www.douban.com/game/27599942/");
        Url.add("https://www.douban.com/game/35110438/");
        Url.add("https://www.douban.com/game/35688209/");
        Url.add("https://www.douban.com/game/26682759/");

        Img.add("https://img3.doubanio.com/lpic/s29308930.jpg");
        Img.add("https://img1.doubanio.com/lpic/s29663617.jpg");
        Img.add("https://img2.doubanio.com/lpic/s33857713.jpg");
        Img.add("https://img2.doubanio.com/lpic/s34234833.jpg");
        Img.add("https://img1.doubanio.com/lpic/s29296639.jpg");

        Content.add("在二十多年乖乖女的循规蹈矩之后，这个游戏让我重新体会到走错路的快乐。所有的角落，我都想摸一摸，所有的人，我都想说说话，这个世界，好奇心带来的只有奖励，没有惩罚。");
        Content.add("这天夜里终于打穿了英杰之诗，见到久违的Mipha，骑上心爱的摩托，久久不能平静。再一次站在初始台地，远眺这片旷野，听了又听公主的呼唤。");
        Content.add("我死了我死了我死了你别死！！好我活了你可以死了");
        Content.add("想封这个游戏的，你一天会忘记成千上万件事，何不把这一件也忘了？");
        Content.add(	"请购买steam正版吧，你会发现cdp公司需要玩家的支持，而他们的定价之低，游戏耐玩度精致度之高，根本就太不对劲了。");
        sum.add(Name);
        sum.add(Url);
        sum.add(Img);
        sum.add(Content);

        return sum;
    }


    @RequestMapping("/pachong")
    @ResponseBody
    @SaCheckLogin //检测用户是否登录
    public void pachong(){
        Process proc;
        try {
            String[] ar = new String[] { "/usr/local/bin/python3.12","/home/centos/jiaoben/SelectData.py"};
            proc = Runtime.getRuntime().exec(ar);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gb2312"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }























}
