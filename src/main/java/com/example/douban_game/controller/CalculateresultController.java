package com.example.douban_game.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.douban_game.entity.Calculateresult;
import com.example.douban_game.entity.GenerateData;

import com.example.douban_game.service.CalculateresultService;
import com.example.douban_game.service.IGenerateService;

//import org.apache.tomcat.jni.Time;
import org.AgeAnalysis;
import org.LanguageAnalysis;
import org.RateAnalysis;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
//import org.sparkproject.guava.collect.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Calculate")
public class CalculateresultController {
    @Autowired
    private CalculateresultService calculateresultService;
    @Autowired
    private IGenerateService iGenerateService;

    @RequestMapping("/selectCalculateresult")
    @ResponseBody
    @SaCheckLogin
    public List<Calculateresult> selectCalculateresult(int operator_id){
        return calculateresultService.selectCalculateresult(operator_id);
    }


    @RequestMapping("/newCalculate")
    @ResponseBody
    @SaCheckLogin //检测该操作是否是在用户登录过后执行
    public void newCalculate(int fakeid, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
//        int id = (Integer) session.getAttribute("id");
        int id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));
        Calculateresult calculateresult = new Calculateresult();

        List<Integer> ii = iGenerateService.selectAllFakeId();
        int is = 0;
        for (int i = 0; i <ii.size() ; i++) {
            is=ii.get(i);
        }
        System.out.println(is);
        List<GenerateData> g1 = iGenerateService.getGenerateHistory(id);

        GenerateData gd = new GenerateData();
        for (int i = 0; i <g1.size() ; i++) {
            if(g1.get(i).getFake_id()==fakeid){
                gd = g1.get(i);
                break;
            }
        }
        Date date = new Date();
        calculateresult.setOperator_id(id);
        calculateresult.setCalc_date(date);
        calculateresult.setCalc_id(fakeid);
        calculateresult.setCalc_data_num(gd.getGenerate_number());
        SSHFileController sshFileController = new SSHFileController();
//        sshFileController.SSHFileUpload("D:\\专综2\\data\\input\\kind.csv");

        FileReader fr = new FileReader("/home/basedata/kind.csv");
        BufferedReader br = new BufferedReader(fr);

        FileWriter fw = new FileWriter("/home/data/kind.csv");
        PrintWriter pw = new PrintWriter(fw);

        String temp = "";
        while((temp = br.readLine())!=null){
            pw.println(temp);
            pw.flush();}
        Thread.sleep(5000);
        Configuration configuration=new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://10.7.122.53:8020"), configuration, "root");

        //2、获取文件详情.注意是获取的文件，而不是文件夹（目录）
        //final boolean recursive 是否递归查找
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/flume/"), true);
        System.out.println("检测");
        boolean bool = false;
        while(bool){
            boolean b1 = listFiles.next().getPath().getName().contains("tmp");
            if(listFiles.hasNext() && b1){
                while( listFiles.next().getPath().getName().contains("tmp")){
                    listFiles = fs.listFiles(new Path("/flume/"), true);
                    listFiles.next();
                    Thread.sleep(2000);
                }
                bool=true;
                listFiles = fs.listFiles(new Path("/flume/"), true);
            }
            else if (!listFiles.hasNext() && b1){
                bool=true;
            }
            listFiles = fs.listFiles(new Path("/flume/"), true);
        }


        RateAnalysis.rateAnalysis(fakeid);
        HadoopController.Delete("/flume");
        Thread.sleep(5000);

        FileReader fr1 = new FileReader("/home/basedata/language.csv");
        BufferedReader br1 = new BufferedReader(fr1);

        FileWriter fw1 = new FileWriter("/home/data/language.csv");
        PrintWriter pw1 = new PrintWriter(fw1);

        String temp1 = "";
        while((temp1 = br1.readLine())!=null){
            pw1.println(temp1);
            pw1.flush();}

        Thread.sleep(5000);
        FileSystem fs1 = FileSystem.get(new URI("hdfs://10.7.122.53:8020"), configuration, "root");

        //2、获取文件详情.注意是获取的文件，而不是文件夹（目录）
        //final boolean recursive 是否递归查找
        RemoteIterator<LocatedFileStatus> listFiles1 = fs1.listFiles(new Path("/flume/"), true);
        System.out.println("检测");
        while(listFiles1.next().getPath().getName().contains("tmp")){
//            Thread.sleep(5000);
            listFiles1 = fs.listFiles(new Path("/flume/"), true);
            System.out.println("正在计算。。。。");
            System.out.println(listFiles1.next().getPath().getName());
            listFiles1 = fs.listFiles(new Path("/flume/"), true);
            Thread.sleep(3000);
        }
        if(listFiles.hasNext()){
            System.out.println("数据量超过一千万，请稍等");
            Thread.sleep(10000);
        }




        LanguageAnalysis.languageAnalysis(fakeid);
        Thread.sleep(2000);
        HadoopController.Delete("/flume");
//        LinuxController.delete("10.7.122.53");
        Thread.sleep(5000);

        FileReader fr2 = new FileReader("/home/basedata/location.csv");
        BufferedReader br2 = new BufferedReader(fr2);

        FileWriter fw2 = new FileWriter("/home/data/location.csv");
        PrintWriter pw2 = new PrintWriter(fw2);

        String temp2 = "";
        while((temp2 = br2.readLine())!=null){
            pw2.println(temp2);
            pw2.flush();}


        Thread.sleep(5000);
        FileSystem fs2 = FileSystem.get(new URI("hdfs://10.7.122.53:8020"), configuration, "root");

        //2、获取文件详情.注意是获取的文件，而不是文件夹（目录）
        //final boolean recursive 是否递归查找
        RemoteIterator<LocatedFileStatus> listFiles2 = fs2.listFiles(new Path("/flume/"), true);
        System.out.println("检测");

        while(listFiles2.next().getPath().getName().contains("tmp")){
            listFiles2 = fs.listFiles(new Path("/flume/"), true);
            System.out.println("正在计算。。。。");
            System.out.println(listFiles2.next().getPath().getName());
            listFiles2 = fs.listFiles(new Path("/flume/"), true);
            Thread.sleep(5000);
        }

        if(listFiles.hasNext()){
            System.out.println("数据量超过一千万，请稍等");
            Thread.sleep(10000);
        }

        AgeAnalysis.ageAnalysis(fakeid);
        Thread.sleep(2000);
        HadoopController.Delete("/flume");
        File myObj_kind = new File("/home/data/kind.csv");
        File myObj_language = new File("/home/data/language.csv");
        File myObj_location = new File("/home/data/location.csv");
        myObj_kind.delete();
        myObj_language.delete();
        myObj_location.delete();
        calculateresultService.addnewCalculate(calculateresult);
    }

//    @RequestMapping("/newCalculate2")
//    @ResponseBody
//    public void newCalculate2(int fakeid, HttpServletRequest request) throws Exception {
//        SSHFileController sshFileController = new SSHFileController();
//        sshFileController.SSHFileUpload("D:\\专综2\\data\\input\\location.csv");
//        Thread.sleep(5000);
//        AgeAnalysis.ageAnalysis(fakeid);
//        Thread.sleep(2000);
//        HadoopController.Delete("/flume");
//        LinuxController.delete("10.7.122.53");
//        Thread.sleep(15000);
//    }



}
