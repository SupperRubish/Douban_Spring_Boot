package com.example.douban_game.controller;


import com.example.douban_game.util.SSHRemoteCall;
import lombok.extern.slf4j.Slf4j;
//import org.apache.spark.internal.config.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import cn.wanda.modules.communication.utils.SSHRemoteCall;
//import lombok.extern.slf4j.Slf4j;


/**
 * @author : WangYuanYi
 * @description : 控制层
 * @create :2021-11-03 09:34:00
 */
@RestController()
@RequestMapping("/ssh/file")
@Slf4j
public class SSHFileController {
    // 默认端口号
    private static final int DEFAULT_PORT = 16667;
    private int port = 16667;// 端口号
    // ip地址
    private static String ipAddress = "10.7.122.53";
    // 账号
    private static String userName = "root";
    // 密码
    private static String password = "123456";

    /**
     *
     * @param path
     */
    @GetMapping("/fileUpload")
    public String SSHFileUpload(@RequestParam("path") String path) {
        // 连接到指定的服务器
        try {
            // 1、首先远程连接ssh
            SSHRemoteCall.getInstance().sshRemoteCallLogin(ipAddress, userName, password);
            // 打印信息
//            log.info("连接10.199.209.**,ip地址: " + ipAddress + ",账号: " + userName + ",连接成功.....");
            System.out.println("连接10.199.209.**,ip地址: " + ipAddress + ",账号: " + userName + ",连接成功.....");

            // 2、执行相关的命令
            // 查看目录信息
            // String command = "ls /home/hadoop/package ";
            // 查看文件信息
            // String command = "cat /home/hadoop/package/test ";
            // 查看磁盘空间大小
            // String command = "df -lh ";
            // 查看cpu的使用情况
            // String command = "top -bn 1 -i -c ";
            // 查看内存的使用情况
//            String command = "free ";
//            SSHRemoteCall.getInstance().execCommand(command);
            //赋予该路径下进行一系列的操作
            String command = "sudo chmode -R 777 /home";
            SSHRemoteCall.getInstance().execCommand(command);
            // 3、上传文件
            // 目标文件名
            String directory = "/home/data/w.csv";
            // 本地文件名
//            String uploadFile = "F:\\abc.docx";
            SSHRemoteCall.getInstance().uploadFile(directory, path);

            // 4、展示目录下的文件信息
            String lsDirectory = "/home/data/";
            SSHRemoteCall.getInstance().listFiles(lsDirectory);
            // 5、关闭连接
//            SSHRemoteCall.getInstance().closeSession();

        } catch (Exception e) {
            // 打印错误信息
//            log.info("远程连接失败......");
            System.out.println("远程连接失败......");
            e.printStackTrace();
            return "文件上传失败";
        }
        return "文件上传成功";
    }
    @GetMapping("/downUpload")
    public void SSHFileDownload() {
        // 连接到指定的服务器
        try {
            // 1、首先远程连接ssh
            SSHRemoteCall.getInstance().sshRemoteCallLogin(ipAddress, userName, password);
            // 打印信息
            System.out.println("连接10.199.209.**,ip地址: " + ipAddress + ",账号: " + userName + ",连接成功.....");

            // 查看内存的使用情况
            String command = "free ";
            SSHRemoteCall.getInstance().execCommand(command);

            // 2、下载文件
            // src 是linux服务器文件地址,dst 本地存放地址,采用默认的传输模式：OVERWRITE ;  test为文件名称哈
            String src = "/home/appuser/abc.docx";
            String dst = "E:\\";
            SSHRemoteCall.getInstance().fileDownload(src, dst);

//            // 3、刪除文件
//            String deleteDirectoryFile = "/home/hadoop/package/test";
//            SSHRemoteCall.getInstance().deleteFile(deleteDirectoryFile);
//
//            // 4、展示目录下的文件信息
//            String lsDirectory = "/home/hadoop/package";
//            SSHRemoteCall.getInstance().listFiles(lsDirectory);
            // 5、关闭连接
            SSHRemoteCall.getInstance().closeSession();
        } catch (Exception e) {
            // 打印错误信息
            System.err.println("远程连接失败......");
            e.printStackTrace();
        }
    }

}


