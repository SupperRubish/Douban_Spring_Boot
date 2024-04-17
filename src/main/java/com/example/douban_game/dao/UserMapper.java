package com.example.douban_game.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.douban_game.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List <User> selectUserAll();
    int InsertUser(User user);

//    User selectUserById(int user_id);

    int updatePassword(User user);


    User selectUserByTele(String phone_number);
//    void insertUser(User user);
//
//    User selectUserByPhone(String pnum);
//
//    List<String> getAllPhone();


}
