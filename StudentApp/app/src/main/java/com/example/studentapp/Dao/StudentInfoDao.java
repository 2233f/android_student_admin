package com.example.studentapp.Dao;

import androidx.room.Dao;
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


}

