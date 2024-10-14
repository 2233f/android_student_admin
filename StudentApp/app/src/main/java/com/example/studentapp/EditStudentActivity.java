package com.example.studentapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.Dao.StudentInfoDao;
import com.example.studentapp.Util.ToastUtil;
import com.example.studentapp.enity.StudentInfo;

public class EditStudentActivity extends AppCompatActivity {

    private EditText stuDateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_student);
        // 从 Intent 中获取传递过来的 StudentInfo 数据
        String studentInfoString = getIntent().getStringExtra("student_info");
        // 解析数据
        String[] studentInfo = null;
        if (studentInfoString != null) {
            studentInfo = studentInfoString.split(",");
        }

        // 使用 student 对象的数据填充表单字段
        EditText stuNameEditText = findViewById(R.id.stuName);
        if (studentInfo != null) {
            stuNameEditText.setText(studentInfo[0]);
        }

        // 获取性别信息
        String stuGender = null;
        if (studentInfo != null) {
            stuGender = studentInfo[1];
            // 根据性别信息设置RadioButton的选中状态
            RadioButton rbMale = findViewById(R.id.rb_male);
            RadioButton rbFemale = findViewById(R.id.rb_female);

            if ("Man".equals(stuGender)) {
                rbMale.setChecked(true);
            } else if ("Girl".equals(stuGender)) {
                rbFemale.setChecked(true);
            }
        }

        EditText stuAgeEditText = findViewById(R.id.stuAge);
        if (studentInfo != null) {
            stuAgeEditText.setText(studentInfo[2]);
        }

        EditText stuClassEditText = findViewById(R.id.stuClass);
        if (studentInfo != null) {
            stuClassEditText.setText(studentInfo[3]);
        }

        EditText stuIdCardEditText = findViewById(R.id.stuIdCard);
        if (studentInfo != null) {
            stuIdCardEditText.setText(studentInfo[4]);
        }

        stuDateEditText = findViewById(R.id.stuDate);
        if (studentInfo != null) {
            stuDateEditText.setText(studentInfo[5]);
        }
        stuDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // 确定按钮
        Button verifyButton = findViewById(R.id.verify);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取表单数据
                EditText stuNameEditText = findViewById(R.id.stuName);
                String stuName = stuNameEditText.getText().toString();

                RadioButton rbMale = findViewById(R.id.rb_male);
                String stuGender = rbMale.isChecked() ? "Man" : "Girl";

                EditText stuAgeEditText = findViewById(R.id.stuAge);
                String stuAge = stuAgeEditText.getText().toString();

                EditText stuClassEditText = findViewById(R.id.stuClass);
                String stuClass = stuClassEditText.getText().toString();

                EditText stuIdCardEditText = findViewById(R.id.stuIdCard);
                String stuIdCard = stuIdCardEditText.getText().toString();

                EditText stuDateEditText = findViewById(R.id.stuDate);
                String stuDate = stuDateEditText.getText().toString();


                // 假设你已经有了一个方法来获取当前学生ID
                int studentId = getIntent().getIntExtra("student_id", -1);
                if (studentId != -1) {
                    // 更新数据库中的数据
                    StudentInfoDao studentInfoDao = MyApplication.getDatabase().studentInfoDao();
                    studentInfoDao.updateStudent(stuName, stuGender, Integer.parseInt(stuAge), stuClass, stuIdCard, stuDate, studentId);
                    finish(); // 返回前一个Activity
                } else {
                    ToastUtil.showToast(EditStudentActivity.this, "修改失败");
                }
            }
        });
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                        stuDateEditText.setText(date);
                    }
                }, 2024, 9, 15);

        datePickerDialog.show();
    }
}