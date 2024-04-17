package com.example.douban_game.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.douban_game.entity.Area;
import com.example.douban_game.entity.GenerateData;
import com.example.douban_game.entity.Language;
import com.example.douban_game.service.IGenerateService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/data")
public class GenerateController {

    @Autowired
    private IGenerateService iGenerateService;

    // 利用mock模板生成模拟数据
    @RequestMapping("/test")
    @ResponseBody
    public void dataT(int fakeid,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        iGenerateService.generateTestData((Integer) session.getAttribute("id"),fakeid);
    }

    // 生成假数据，并增加历史生成数据信息
    @RequestMapping("/generate")
    @ResponseBody
    @SaCheckLogin
    public void generate(GenerateData gdata, Area area, Language language) throws Exception {
        GenerateController gc = new GenerateController();
        Date date = new Date();
        int id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));
        gdata.setOperator_id(id);
        gdata.setGenerate_date(date);
        iGenerateService.GenerateData(gdata);
        List<Integer> ii = iGenerateService.selectAllFakeId();
        int is = 0;
        for (int i = 0; i <ii.size() ; i++) {
            is=ii.get(i);
        }
        System.out.println(is);
        area.setArea_id(is);
        iGenerateService.GenerateAreaData(area);
        language.setLanguage_id(is);
        iGenerateService.GenerateLanguageData(language);
        iGenerateService.generateTestData(id,is);




    }


    // 根据操作人id查询历史生成数据记录信息
    @RequestMapping("/getHistoryDataInfo")
    @ResponseBody
    public List<GenerateData> selectHistoryByOperatorIp(int operator_id) {
        return iGenerateService.selectHistoryByOperatorIp(operator_id);
    }
    @RequestMapping("/getHistoryDataInfos")
    @ResponseBody
    public List<GenerateData> selectHistoryByOperatorIps(HttpServletRequest request) {

        HttpSession session = request.getSession();
        int operator_id=(Integer) session.getAttribute("id");
        return iGenerateService.selectHistoryByOperatorIp(operator_id);
    }




    //通过ID获取所有数据
    @RequestMapping("/selectHistoryByID")
    @ResponseBody
    public List<GenerateData> selectHistoryByID(int id) {
        return iGenerateService.selectHistoryByID(id);
    }

    // 获取所有历史生成数据记录信息
    @RequestMapping("/getGenerateHistory")
    @ResponseBody
    public List<GenerateData> getGenerateHistory(int operator_id) {
        return iGenerateService.getGenerateHistory(operator_id);
    }


    @RequestMapping("/getGenerateHistorys")
    @ResponseBody
    public List<GenerateData> getGenerateHistorys(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int operator_id=(Integer) session.getAttribute("id");
        return iGenerateService.getGenerateHistory(operator_id);
    }

    // 获取历史生成数据次数
    @RequestMapping("/countHistory")
    @ResponseBody
    public int countHistory() {
        return iGenerateService.countHistory();
    }



    @RequestMapping("/selectAllFakeId")
    @ResponseBody
    public List<Integer> selectAllFakeId() {
        return iGenerateService.selectAllFakeId();
    }



    //分页方式1
    @GetMapping("/PageHelper1")
    public PageInfo<GenerateData> PageHelper1(HttpServletRequest request, int page, int size) {
        HttpSession session = request.getSession();
        return iGenerateService.findAllDataByPageS((Integer) session.getAttribute("id"), page, size);
    }

    //分页方式2
    @GetMapping("/PageHelper2")
    public List<GenerateData> PageHelper2(HttpServletRequest request, int page, int size) {
        HttpSession session = request.getSession();
        return iGenerateService.findAllDataByPageF((Integer) session.getAttribute("id"), page, size);
    }

    //分页测试1
    @GetMapping("/testPageHelper1")
    @ResponseBody
    public PageInfo<GenerateData> testPageHelper1(int page, int size,HttpServletRequest request) {
        HttpSession session = request.getSession();
//        return iGenerateService.findAllDataByPageS((Integer) session.getAttribute("id"), page, size);
        return iGenerateService.findAllDataByPageS(1, page, size);
    }

    //分页测试2
    @GetMapping("/testPageHelper2")
    @ResponseBody
    public List<GenerateData> testPageHelper2(int page, int size,HttpServletRequest request) {
        HttpSession session = request.getSession();
        return iGenerateService.findAllDataByPageF((Integer) session.getAttribute("id"), page, size);
    }


}
