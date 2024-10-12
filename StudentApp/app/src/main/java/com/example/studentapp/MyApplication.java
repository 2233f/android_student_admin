package com.example.studentapp;

import android.app.Application;

import androidx.room.Room;

import com.example.studentapp.database.AppDatabase;

public class MyApplication extends Application {
    private static MyApplication mApp;
    private static AppDatabase db;

    // app启动时创建的内容
    @Override
    public void onCreate() {
        super.onCreate();
        // 创建一个MyApplication对象
        mApp = this;
        // 初始化数据库
        db = Room.databaseBuilder(this, AppDatabase.class, "app_database")
                // 允许迁移数据库，修改之后依旧保存数据
                .addMigrations()
                // 允许在主进程中进行操作
                .allowMainThreadQueries()
                .build();
    }

    // 获取MyApplication对象
    static MyApplication getInstance() {
        return mApp;
    }

    // 获取数据库对象
    public static AppDatabase getDatabase() {
        return db;
    }


}
