package com.example.studentapp.enity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_info")
public class StudentInfo {
    @PrimaryKey(autoGenerate = true)
    private int _id;

    @NonNull
    private String stuName;

    private String stuGender;
    private int stuAge;
    private String stuClass;
    private String stuIdcard;
    private String stuDate;

    // Getters and Setters
    public int getId() { return _id; }
    public void setId(int _id) { this._id = _id; }

    @NonNull
    public String getStuName() { return stuName; }
    public void setStuName(@NonNull String stuName) { this.stuName = stuName; }

    public String getStuGender() { return stuGender; }
    public void setStuGender(String stuGender) { this.stuGender = stuGender; }

    public int getStuAge() { return stuAge; }
    public void setStuAge(int stuAge) { this.stuAge = stuAge; }

    public String getStuClass() { return stuClass; }
    public void setStuClass(String stuClass) { this.stuClass = stuClass; }

    public String getStuIdcard() { return stuIdcard; }
    public void setStuIdcard(String stuIdcard) { this.stuIdcard = stuIdcard; }

    public String getStuDate() { return stuDate; }
    public void setStuDate(String stuDate) { this.stuDate = stuDate; }
}
