package com.example.douban_game.controller;



import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.douban_game.ServiceEntity.UserServiceEntity;
import com.example.douban_game.entity.User;
import com.example.douban_game.service.IUserService;
import com.example.douban_game.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserServiceEntity userServiceEntity;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

//
    @RequestMapping("/all")
    @ResponseBody
    @SaCheckLogin //判断当前操作是否是用户登陆后执行
    public List<User> all(User user,HttpServletRequest request) {
        LambdaQueryWrapper<User> ew = new LambdaQueryWrapper<>();
        ew.select(User::getUserName).eq(User::getUserId,StpUtil.getLoginId());
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("id"));
        return iUserService.selectUserAll();
    }


    @RequestMapping("/info")
    @ResponseBody
    @SaCheckLogin //判断当前操作是否是用户登陆后执行
    public User UserInfo() {
        LambdaQueryWrapper<User> ew = new LambdaQueryWrapper<>();
        System.out.println(StpUtil.getLoginId());
        ew.eq(User::getUserId,StpUtil.getLoginId());
        return userServiceEntity.getOne(ew);
    }
    @RequestMapping("/addUser")
    @ResponseBody
    public JsonResult InsertUser(User user) {
        String p = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());//对用户设置的密码进行Md5加密
        user.setPassword(p);
        int a = iUserService.InsertUser(user);
        if(a==1){
            return new JsonResult(true,"添加成功",a);
        }
        else{
            return new JsonResult(false,"添加失败",0);
        }

    }
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult selectUserById(@RequestParam("telep")String telep, @RequestParam("password")String password, HttpServletRequest request
    ){
        LambdaQueryWrapper<User> ew = new LambdaQueryWrapper<>();
        String p1 = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userServiceEntity.getOne(ew.eq(User::getPhoneNumber,telep));
        int id = user.getUserId();
        int a = iUserService.JudgePassword(id,p1);
        if(a==1){
            StpUtil.login(id);//单点登录，后续用SaCheckLogin注解来验证
            ValueOperations ops = redisTemplate.opsForValue();
            String userToken= UUID.randomUUID().toString().replace("-","");
            ops.set(userToken, id, 3600, TimeUnit.SECONDS);
            return new JsonResult(true,"密码正确",a);
        }
        else if(a==-1){
            return new JsonResult(false,"密码错误",0);
        }

        else{
            return new JsonResult(false,"查无此人",0);
        }
    }
    @RequestMapping("/Insurance")
    @ResponseBody
    public JsonResult JudgeInsurance(String telep, String insurance){
        int user_id = iUserService.selectUserByTele(telep).getUserId();
        int a = iUserService.JudgeInsurance(user_id,insurance);
        if(a==1){
            return new JsonResult(true,"验证正确",a);
        }
        else {
            return new JsonResult(false,"验证错误",0);
        }
    }

    @RequestMapping("/Reset")
    @ResponseBody
    public JsonResult ResetPassword(String telep,String password){
        int id = iUserService.selectUserByTele(telep).getUserId();
        String news = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = new User();
        user.setUserId(id);
        user.setPassword(news);
        int a = iUserService.updatePassword(user);
        return new JsonResult(true,"重置成功",a);
    }


    @RequestMapping("/ChangePassword")
    @ResponseBody
    public JsonResult updatePassword(int user_id,String newp1,String newp2) {
        if (newp1.equals(newp2)){
            User user = new User();
            user.setUserId(user_id);
            String news = DigestUtils.md5DigestAsHex(newp1.getBytes());
            user.setPassword(news);
            int a = iUserService.updatePassword(user);
            if(a==1){
                return new JsonResult(true,"重置成功",a);
            }
            else {
                return new JsonResult(false,"重置失败",0);
            }
        }
        else{
            return new JsonResult(false,"密码不一致",0);
        }

    }



    @RequestMapping("/PasswordConstraint")
    @ResponseBody
    public JsonResult PasswordConstraint(int user_id,String newp) {
            User user = new User();
            user.setUserId(user_id);
            String news = DigestUtils.md5DigestAsHex(newp.getBytes());
            user.setPassword(news);
            int a = iUserService.updatePassword(user);
            return new JsonResult(true,"修改成功",a);



    }



    @RequestMapping("/selectUserById")
    @ResponseBody
    public User selectUserById(HttpServletRequest request) {
        HttpSession session = request.getSession();
//        int user_id = (Integer) session.getAttribute("id");
        int user_id = 1;
        return iUserService.selectUserById(user_id);
    }

}
