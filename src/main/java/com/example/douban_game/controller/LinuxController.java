package com.example.douban_game.controller;

import com.example.douban_game.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;


import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Linux")
public class LinuxController {
    @RequestMapping("Test")
    @ResponseBody
    public JsonResult Tests(String host) throws IOException, JSchException {
        System.out.println("|||||||||||||||");
        System.out.println("+++++++++++++++++++"+host);
        System.out.println("|||||||||||||||");

        String j = Test(host, "123465");
        return new JsonResult(true, "success",j);
    }
    public String Test(String host, String password) throws JSchException, IOException {
        // TODO Auto-generated method stub
//        String host = "10.7.121.226";
        int port = 28991;
        String user = "root";
//        String password = "123456";
        String command = " sh /root/shell/test.sh";
        String res = exeCommand(host, port, user, password, command);
        System.out.println("=====================================================================");
        System.out.println(res);
        System.out.println("=====================================================================");
        return res;
    }















    @RequestMapping("WordCount")
    @ResponseBody
    public JsonResult WordCount(String host) throws IOException, JSchException {
        System.out.println("|||||||||||||||");
        System.out.println("+++++++++++++++++++"+host);
        System.out.println("|||||||||||||||");

        String j = Word(host, "admin");
        return new JsonResult(true, "success",j);
    }
    public String Word(String host, String password) throws JSchException, IOException {
        // TODO Auto-generated method stub
//        String host = "10.7.121.226";
        int port = 22;
        String user = "root";
//        String password = "123456";
        String command = " /root/lrx/spark/bin/spark-submit --master local[2] --class org.apache.spark.examples.SparkPi /root/lrx/spark/examples/jars/spark-examples_2.12-3.1.3.jar  10";
        String res = exeCommand(host, port, user, password, command);
        System.out.println("=====================================================================");
        System.out.println(res);
        System.out.println("=====================================================================");
        return res;
    }


    @RequestMapping("Uplate")
    @ResponseBody
    public JsonResult Uplate(String host) throws IOException, JSchException {
        System.out.println("|||||||||||||||");
        System.out.println("+++++++++++++++++++"+host);
        System.out.println("|||||||||||||||");

        String j = Uplates(host, "123456");
        return new JsonResult(true, "success",j);
    }
    public String Uplates(String host, String password) throws JSchException, IOException {
        // TODO Auto-generated method stub
//        String host = "10.7.121.226";
        int port = 16667;
        String user = "root";
//        String password = "123456";
        String command = " scp -P 16667 D:\\专综2\\data\\input\\kind.csv root@10.7.122.53:/home/ ";
        String res = exeCommand(host, port, user, password, command);
        System.out.println("=====================================================================");
        System.out.println(res);
        System.out.println("=====================================================================");
        return res;
    }











    @RequestMapping("/Delete")
    @ResponseBody
    public static JsonResult delete(String host) throws IOException, JSchException {
        System.out.println("|||||||||||||||");
        System.out.println("+++++++++++++++++++"+host);
        System.out.println("|||||||||||||||");

        String j = DELETE(host, "123456");
        return new JsonResult(true, "success",j);
    }
    public static String DELETE(String host, String password) throws JSchException, IOException {
        // TODO Auto-generated method stub
//        String host = "10.7.121.226";
        int port = 16667;
        String user = "root";
//        String password = "123456";
        String command = " /home/centos/jiaoben/rm_data.sh";
        String res = exeCommand(host, port, user, password, command);
        System.out.println("=====================================================================");
        System.out.println(res);
        System.out.println("=====================================================================");
        return res;
    }








    @RequestMapping("/JPS")
    @ResponseBody
    public JsonResult getinfo(String host) throws IOException, JSchException {
        System.out.println("|||||||||||||||");
        System.out.println("+++++++++++++++++++"+host);
        System.out.println("|||||||||||||||");

        String j = JPS(host, "admin");
        return new JsonResult(true, "success",j);
    }


    public String JPS(String host, String password) throws JSchException, IOException {
        // TODO Auto-generated method stub
//        String host = "10.7.121.226";
        int port = 22;
        String user = "root";
//        String password = "123456";
        String command = "/usr/local/jdk1.8.0_221/bin/jps ";
        String res = exeCommand(host, port, user, password, command);
        System.out.println("=====================================================================");
        System.out.println(res);
        System.out.println("=====================================================================");
        return res;
    }
    public static String exeCommand(String host, int port, String user, String password, String command) throws JSchException, IOException, JSchException, IOException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        //避免SSH 的公钥检查
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();

        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        String out = IOUtils.toString(in, "UTF-8");

        channelExec.disconnect();
        session.disconnect();

        return out;
    }
}
