package com.example.studentapp.enity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_info")
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    private int _id;

    @NonNull
    private String userName;

    @NonNull
    private String password;

    // Getters and Setters
    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
