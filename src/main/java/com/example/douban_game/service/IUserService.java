package com.example.douban_game.service;




import com.example.douban_game.entity.User;

import java.util.List;

public interface IUserService {
    List <User> selectUserAll();
    User selectUserById(int user_id);


    int InsertUser(User user);

    int JudgePassword(int user_id,String password);

    int JudgeInsurance(int user_id,String insurance);

    int updatePassword(User user);
    User selectUserByTele(String phone_number);
//    JsonResult insertUser(User user);
//
//    User selectUserByPhone(String pnum);
//
//    List<String> getAllPhone();
//
//    List<Colony> getAllColony(String pnum);
}
