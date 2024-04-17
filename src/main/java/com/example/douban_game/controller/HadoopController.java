package com.example.douban_game.controller;




import com.example.douban_game.core.Result;
import com.example.douban_game.util.HadoopUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 类或方法的功能描述 :TODO
 * @date: 2018-11-28 13:51
 */
@RestController
@RequestMapping("/hadoop")
public class HadoopController {



    /**
     * 创建文件夹
     * @param path
     * @return
     * @throws Exception
     */
//    @PostMapping("/mkdir")
    @RequestMapping("/mkdir")
    @ResponseBody
    public Result mkdir(@RequestParam("path") String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            Result result=new Result();
            result.setCode("500");
            result.setMessage("请求参数为空");
            result.setData("请求参数为空");
            result.setSuccess(false);
            return result;
        }
        // 文件对象
        FileSystem fs = HadoopUtil.getFileSystem();
        // 目标路径
        Path newPath = new Path(path);
        // 创建空文件夹
        boolean isOk = fs.mkdirs(newPath);
        fs.close();
        if (isOk) {
            Result result=new Result();
            result.setMessage("dir create success");
            result.setCode("200");
            result.setSuccess(true);
            return result;
        } else {
            Result result=new Result();
            result.setMessage("dir create fail");
            result.setCode("500");
            result.setSuccess(false);
            return result;
        }
    }






    @RequestMapping("/delete")
    @ResponseBody
    public static Result Delete(@RequestParam("path") String path) throws Exception {
        if (StringUtils.isEmpty(path)) {
            Result result=new Result();
            result.setCode("500");
            result.setMessage("请求参数为空");
            result.setData("请求参数为空");
            result.setSuccess(false);
            return result;
        }
        // 文件对象
        FileSystem fs = HadoopUtil.getFileSystem();
        // 目标路径
        Path newPath = new Path(path);
        // 创建空文件夹
        boolean isOk = fs.delete(newPath,true);
        fs.close();
        if (isOk) {
            Result result=new Result();
            result.setMessage("dir delete success");
            result.setCode("200");
            result.setSuccess(true);
            return result;
        } else {
            Result result=new Result();
            result.setMessage("dir delete fail");
            result.setCode("500");
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * 读取HDFS文件内容
     * @param path
     * @return
     * @throws Exception
     */
//    @PostMapping("/readFile")
    @RequestMapping("/readFile")
    @ResponseBody
    public Result readFile(@RequestParam("path") String path) throws Exception {
        FileSystem fs = HadoopUtil.getFileSystem();
        Path newPath = new Path(path);
        InputStream in = null;
        try {
            in = fs.open(newPath);
            IOUtils.copyBytes(in, System.out, 4096);

        } finally {
            IOUtils.closeStream(in);
            fs.close();
        }

        Result result=new Result();
        result.setMessage("读取成功");
        result.setCode("200");
        result.setSuccess(true);
        result.setData(System.out.toString());
        return result;
    }

    /**
     * 创建文件
     * @param path
     * @return
     * @throws Exception
     */
    @PostMapping("/createFile")
    public Result createFile(@RequestParam("path") String path, @RequestParam("file") MultipartFile file) throws Exception {
        if (StringUtils.isEmpty(path) || null == file.getBytes()) {
            Result result=new Result();
            result.setMessage("请求参数为空");
            result.setCode("500");
            result.setSuccess(false);
            return result;
        }
        String fileName = file.getOriginalFilename();
        FileSystem fs = HadoopUtil.getFileSystem();
        // 上传时默认当前目录，后面自动拼接文件的目录
        Path newPath = new Path(path + "/" + fileName);
        // 打开一个输出流
        FSDataOutputStream outputStream = fs.create(newPath);
        outputStream.write(file.getBytes());
        outputStream.close();
        fs.close();
        Result result=new Result();
        result.setMessage("create file success");
        result.setCode("200");
        result.setSuccess(true);
        return result;
    }
}