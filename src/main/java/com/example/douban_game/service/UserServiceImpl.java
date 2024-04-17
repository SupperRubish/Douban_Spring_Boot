package com.example.douban_game.service;



import com.example.douban_game.dao.UserMapper;
import com.example.douban_game.entity.User;
import com.example.douban_game.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public List<User> selectUserAll() {
        return userMapper.selectUserAll();
    }

    @Override
    public User selectUserById(int user_id) {
        return userMapper.selectById(user_id);
    }

    @Override
    public int InsertUser(User user) {
        return userMapper.InsertUser(user);
    }

    @Override
    public int JudgePassword(int user_id,String password) {
        User user =  userMapper.selectById(user_id);

        if(user!=null){
            if(user.getPassword().equals(password)){
                return 1;
            }
            else{
                return -1;
            }

        }
        else{
            return 0;
        }

    }

    @Override
    public int JudgeInsurance(int user_id, String insurance) {
        User user = userMapper.selectById(user_id);
        if(user.getInsurance().equals(insurance)){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public int updatePassword(User user) {
        return userMapper.updatePassword(user);
    }

    @Override
    public User selectUserByTele(String phone_number) {
        return userMapper.selectUserByTele(phone_number);
    }


//    @Autowired
//    private UserMapper userMapper;

//    @Override
//    public JsonResult insertUser(User user) {
//        try {
//            String olds = user.getPassword();
//            String news = DigestUtils.md5DigestAsHex(olds.getBytes());
//            user.setPassword(news);
//            System.out.println(olds);
//            System.out.println(news);
//            userMapper.insertUser(user);
//            return new JsonResult(true,"添加成功");
//        }catch (Exception e) {
//            return new JsonResult(false,"添加失败");
//        }
//    }
//
//    @Override
//    public User selectUserByPhone(String pnum) {
//        return userMapper.selectUserByPhone(pnum);
//    }
//
//    @Override
//    public List<String> getAllPhone() {
//        return userMapper.getAllPhone();
//    }
//
//    @Override
//    public List<Colony> getAllColony(String pnum) {
//        System.out.println("======================="+userMapper.getAllColony(pnum).get(0).getMaster_ip());
//        return userMapper.getAllColony(pnum);
//    }
}
