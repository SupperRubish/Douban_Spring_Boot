package com.example.douban_game.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class callPythonServiceImpl implements callPythonService{


    @Value("${carlos.python.script.path}")
    private String pythonScriptPath;

    @Value("${carlos.python.path}")
    private String pythonPath;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    @Override
    public Object compute() throws IOException {

        Map<String, Object> res = new HashMap<>();
//        解析数据库信息
        String cleanUrl = url.substring(5);
        URI uri = URI.create(cleanUrl);
        String host = uri.getHost();
        String database = uri.getPath().replace("/", "");

//        传递数据库参数，便于python脚本中操作数据库，并与项目参数保持一致，也可以传递其他参数
        String[] arg = new String[]{pythonPath, pythonScriptPath,
                host, database, username, password};
//        打印出来的命令行，也是在bash或者cmd上执行的命令，可预先测试下
        System.out.println("=======================command line=======================");
        for (String s : arg) {
            System.out.print(s + " ");
        }
        System.out.println("\n=======================command line=======================");
        Process proc;
        BufferedReader in = null;
        try {
            proc = Runtime.getRuntime().exec(arg); // 执行py文件
            in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;

            while ((line = in.readLine()) != null) {
//                打印python脚本输出
                System.out.println(line);
//                判断是否为返回结果
                if (line.startsWith("{") && line.endsWith("}") && line.contains("msg") && line.contains("status")){
                    return line;
                }
            }

            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (in != null){
                in.close();
            }
        }

        res.put("status", "error");
        res.put("msg", "python script run error");

        return res;

    }


}
