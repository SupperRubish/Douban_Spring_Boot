package com.example.douban_game.controller;

import com.example.douban_game.entity.Portrait;
import com.example.douban_game.entity.User;
import com.example.douban_game.service.IPortraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Img")
public class PortraitController {
    @Autowired
    private IPortraitService iPortraitService;


    @RequestMapping("/portrait")
    @ResponseBody
    public String img(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int id = (Integer)session.getAttribute("id");
        return iPortraitService.selectImg(id);
    }


    @RequestMapping("/Allimg")
    @ResponseBody
    public List<Portrait> Allimg() {

        return iPortraitService.selectAll();
    }

}
