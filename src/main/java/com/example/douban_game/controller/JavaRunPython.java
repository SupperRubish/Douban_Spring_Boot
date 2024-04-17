package com.example.douban_game.controller;

import org.python.core.*;
import scala.reflect.internal.pickling.UnPickler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.python.util.PythonInterpreter;
public class JavaRunPython {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Process proc;
        try {
            Scanner scanner =new Scanner(System.in);
            System.out.println("输入url：");
            String url = scanner.next();
            System.out.println("输入path：");
            String path = scanner.next();
            String[] ar = new String[] { "/Users/chengmouren/Anaconda3/anaconda3/bin/python","/Users/chengmouren/PycharmProjects/pythonProject/pachong/medio.py",url,path};
            proc = Runtime.getRuntime().exec(ar);// 执行py文件
            System.out.println(proc.waitFor());
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"utf-8"));
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
///Users/chengmouren/teacher.mp4
//        https://media1.99b15.com/remote_control.php?time=1679216352&cv=5005463ca482b1420c4ea4cc234296c7&lr=0&cv2=f2715b1da55a116f20f78c17540a9007&file=%2Fvideos%2F175000%2F175151%2F175151.mp4&cv3=98783c4f91916ebcfadda857ecb6e15d&cv4=b96351d3053b9383c7b31a414625424c
//        String[] arguments = new String[] {"/Users/chengmouren/Anaconda3/anaconda3/bin/python", "/Users/chengmouren/PycharmProjects/pythonProject/pachong/douban_top.py"};
//        try {
//            Process process = Runtime.getRuntime().exec(arguments);
//            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            int re = process.waitFor();
//            System.out.println(re);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}


