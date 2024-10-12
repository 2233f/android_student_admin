package com.example.studentapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.studentapp.enity.UserInfo;

import java.util.List;

@Dao
public interface UserInfoDao {
    @Insert
    void insert(UserInfo userInfo);

    @Query("SELECT * FROM user_info")
    List<UserInfo> getAllUsers();

    @Query("SELECT * FROM user_info WHERE _id = :id")
    UserInfo getUserById(int id);

    // 根据名字查找密码
    @Query("SELECT password FROM user_info WHERE userName = :userName")
    String getUserPasswordByName(String userName);

    // 用户名是否存在
    @Query("SELECT EXISTS(SELECT 1 FROM user_info WHERE userName = :userName LIMIT 1)")
    boolean isUserNameExists(String userName);
}

