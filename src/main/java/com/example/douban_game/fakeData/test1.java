package com.example.douban_game.fakeData;

import com.forte.util.Mock;
import com.forte.util.mockbean.MockObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test1 {
    public static void main(String[] args) throws Exception {

        //long endTime = System.currentTimeMillis();    //获取结束时间

        // 打印展示程序运行时间
        //System.out.println("******" + listParallel.get(0).getKind() + "\n程序运行时间:" + (endTime - startTime)/1000.000 + "s");

    }

    //测试数据生成
    public static List AreaDataTempCreate(int num, String tb, String info) {

        Map<String, Object> template = new HashMap<>();

        template.put("rating|1-4.1", 1.2);
//        template.put("content_num|1-4.1", 1.2);

        //template.put("publish_date", "@date");随机日期
        //listParallel.get(i).getPublish_date().getYear() + 1900;随机日期格式调整
        template.put("publish_date|1", new String[]{"1998", "2001", "2004", "2007", "2010", "2013", "2016", "2019", "2022"});

        template.put("commentNum|1-8000", 1);

        template.put(tb + "|1", new String[]{info});

        //template.put("kind|1", new String[]{"action", "rolePlay", "adventure", "alpinia"});

        // 设置
        Mock.reset(dataMode.class, template);

        // 获取一个MockObject
        MockObject<dataMode> mockUser = Mock.get(dataMode.class);

        // 通过并行流(即多线程)的方式获取对象
        List<dataMode> listParallel = mockUser.getListParallel(num);

        return listParallel;

    }

    //语言测试数据生成
    public static List LanguageDataTempCreate(int num, String tb, String info) {

        Map<String, Object> template = new HashMap<>();

        template.put("rating|1-4.1", 1.2);

        //template.put("publish_date", "@date");随机日期
        //listParallel.get(i).getPublish_date().getYear() + 1900;随机日期格式调整
        template.put("publish_date|1", new String[]{"1998", "2001", "2004", "2007", "2010", "2013", "2016", "2019", "2022"});

        template.put("commentNum|1-8000", 1);

        template.put(tb + "|1", new String[]{info});

        template.put("l_kind|1", new String[]{"action", "rolePlay", "adventure", "alpinia"});

        // 设置
        Mock.reset(dataMode.class, template);

        // 获取一个MockObject
        MockObject<dataMode> mockUser = Mock.get(dataMode.class);

        // 通过并行流(即多线程)的方式获取对象
        List<dataMode> listParallel = mockUser.getListParallel(num);

        return listParallel;

    }

    //写入List数据到文件
    public void WriteConListtent(String tb, String path, List<dataMode> modes) throws IOException {

        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

        for (dataMode dmode : modes) {
            if (tb.equals("kind")) {
                bw.write(dmode.getRating() + "," + dmode.getPublish_date() + "," + dmode.getCommentNum() + "," + dmode.getKind() + "\r\n");
            } else if (tb.equals("location")) {
                bw.write(dmode.getRating() + "," + dmode.getPublish_date() + "," + dmode.getCommentNum() + "," + dmode.getLocation() + "\r\n");
            } else if (tb.equals("language")) {
                bw.write(dmode.getRating() + "," + dmode.getPublish_date() + "," + dmode.getCommentNum() + "," + dmode.getLanguage() +"," + dmode.getL_kind() + "\r\n");
            }
        }

        bw.flush();
        bw.close();

    }

    //写入单行数据到文本
//    public void WriteLineContent(String path, Object modes) throws IOException {
//
//        //String path = "data/input/words.txt";
//        File file = new File(path);
//        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
//        bw.write(modes + "\r\n");
//
//        bw.close();
//
//    }

}
