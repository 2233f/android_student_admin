package com.example.studentapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.Dao.StudentInfoDao;
import com.example.studentapp.Util.ToastUtil;
import com.example.studentapp.enity.StudentInfo;

import java.text.BreakIterator;

public class AddStudentActivity extends AppCompatActivity {

    private StudentInfoDao studentDao;

    private EditText Edit_stuName;
    private EditText Edit_stuAge;
    private EditText Edit_stuIdCard;
    private EditText Edit_stuClass;
    private EditText Edit_stuDate;
    private RadioGroup Edit_radioGroup;
    private Button verify;

    private String stuGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        // 学生数据库对象
        studentDao = MyApplication.getDatabase().studentInfoDao();

        // 获取控件
        Edit_stuName = findViewById(R.id.stuName);
        Edit_stuAge = findViewById(R.id.stuAge);
        Edit_stuIdCard = findViewById(R.id.stuIdCard);
        Edit_stuClass = findViewById(R.id.stuClass);
        Edit_stuDate = findViewById(R.id.stuDate);
        Edit_radioGroup = findViewById(R.id.re_Group);
        verify = findViewById(R.id.verify);

        Edit_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_male) {
                    stuGender = "Man";

                } else if (i == R.id.rb_female) {
                    stuGender = "Girl";
                }
            }
        });

        Edit_stuDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取数据
                String stuName = Edit_stuName.getText().toString().trim();
                String str_stuAge = Edit_stuAge.getText().toString().trim();
                String stuIdCard = Edit_stuIdCard.getText().toString().trim();
                String stuClass = Edit_stuClass.getText().toString().trim();
                String stuDate = Edit_stuDate.getText().toString().trim();
                // 检查空值
                if (stuName.isEmpty() || str_stuAge.isEmpty() || stuIdCard.isEmpty() || stuClass.isEmpty() || stuDate.isEmpty()) {
                    ToastUtil.showToast(AddStudentActivity.this, "请填写所有字段");
                    return;
                }
                // 检查年龄是否为正整数
                try {
                    final int stuAge = Integer.parseInt(str_stuAge);
                    if (stuAge <= 0) {
                        ToastUtil.showToast(AddStudentActivity.this, "请输入有效的年龄");
                        return;
                    }
                } catch (NumberFormatException e) {
                    ToastUtil.showToast(AddStudentActivity.this, "年龄必须是数字");
                    return;
                }

                // 构建插入字段
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setStuName(stuName);
                studentInfo.setStuAge(Integer.parseInt(str_stuAge));
                studentInfo.setStuGender(stuGender);
                studentInfo.setStuIdcard(stuIdCard);
                studentInfo.setStuClass(stuClass);
                studentInfo.setStuDate(stuDate);
                // 插入数据库中
                studentDao.insert(studentInfo);
                ToastUtil.showToast(AddStudentActivity.this, "添加成功！");
                finish(); // 返回前一个Activity
            }
        });

    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                        Edit_stuDate.setText(date);
                    }
                }, 2024, 9, 15);

        datePickerDialog.show();
    }
}