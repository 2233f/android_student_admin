package com.example.studentapp.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.studentapp.Dao.StudentInfoDao;
import com.example.studentapp.Dao.UserInfoDao;
import com.example.studentapp.enity.StudentInfo;
import com.example.studentapp.enity.UserInfo;

@Database(entities = {StudentInfo.class, UserInfo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentInfoDao studentInfoDao();
    public abstract UserInfoDao userInfoDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
