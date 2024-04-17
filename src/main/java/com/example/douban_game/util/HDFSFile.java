package com.example.douban_game.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

//import org.thymeleaf.engine.IterationStatusVar;
import scala.annotation.meta.param;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;


public class HDFSFile {
    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        testListFiles();
    }
    public static void testListFiles() throws URISyntaxException, IOException, InterruptedException {

        //1、获取文件系统,获取的的是hadoop的配置信息文件：Configuration: core-default.xml, core-site.xml, hdfs-default.xml, hdfs-site.xml, mapred-default.xml, mapred-site.xml, yarn-default.xml, yarn-site.xml
        Configuration configuration=new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://10.7.122.53:8020"), configuration, "root");

        //2、获取文件详情.注意是获取的文件，而不是文件夹（目录）
        //final boolean recursive 是否递归查找
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/flume/"), true);
//        RemoteIterator<LocatedFileStatus> listFiles1 = fs.listFiles(new Path("/"), true);
//        System.out.println(listFiles1.next().getPath().getName());
        while(listFiles.next().getPath().getName().contains("tmp")){
//            Thread.sleep(5000);
            listFiles = fs.listFiles(new Path("/flume/"), true);
            System.out.println("正在计算。。。。");
            System.out.println(listFiles.next().getPath().getName());
            listFiles = fs.listFiles(new Path("/flume/"), true);
            Thread.sleep(5000);
        }
        System.out.println("sucsess");
//        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/csr/"), true);

//        System.out.println(listFiles.hasNext());
//        while (listFiles.hasNext()){
//            LocatedFileStatus fileStatus=listFiles.next();
//
//            System.out.println("++++++++++++++"+fileStatus.getPath()+"++++++++++++++");
//            System.out.println(fileStatus.getPermission());
//            System.out.println(fileStatus.getOwner());
//            System.out.println(fileStatus.getGroup());
//            System.out.println(fileStatus.getLen());
//            System.out.println(fileStatus.getModificationTime());
//            System.out.println(fileStatus.getReplication());
//            System.out.println(fileStatus.getBlockSize());
//            System.out.println(fileStatus.getPath().getName());
//
//            //获取块信息
//            BlockLocation[] blockLocations=fileStatus.getBlockLocations();
//            System.out.println(Arrays.toString(blockLocations));
//        }

        //3、关闭资源
        try {
            if (fs != null) {
                fs.close();
                System.out.println("关闭hadoop hdfs 文件远程连接成功...");
            }
        } catch (IOException e) {
            System.out.println("关闭hadoop hdfs 文件远程连接失败...\n原因如下：" + e);
        }

    }


}

