package com.example.studentapp.Dao;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.studentapp.enity.StudentInfo;

import java.util.List;

@Dao
public interface StudentInfoDao {

    @Insert
    void insert(StudentInfo studentInfo);

    @Query("SELECT * FROM student_info")
    List<StudentInfo> getAllStudents();

    @Query("SELECT * FROM student_info WHERE _id = :id")
    StudentInfo getStudentById(int id);

    // 删除单个学生信息
    @Delete
    void delete(StudentInfo studentInfo);

    // 更新单个学生信息
    @Query("UPDATE student_info SET " +
            "stuName = :stuName, " +
            "stuGender = :stuGender, " +
            "stuAge = :stuAge, " +
            "stuClass = :stuClass, " +
            "stuIdcard = :stuIdcard, " +
            "stuDate = :stuDate " +
            "WHERE _id = :id")
    void updateStudent(@NonNull String stuName, @NonNull String stuGender, int stuAge,
                       @NonNull String stuClass, @NonNull String stuIdcard, @NonNull String stuDate, int id);

    @Query("SELECT * FROM student_info WHERE stuName LIKE :query || '%' OR stuName LIKE '%'|| :query || '%' OR stuName LIKE '%'|| :query")
    List<StudentInfo> getStudentsByName(String query);
}

