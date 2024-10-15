package com.example.studentapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.Adapter.StudentAdapter;
import com.example.studentapp.Dao.StudentInfoDao;
import com.example.studentapp.enity.StudentInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchActivity extends AppCompatActivity {
    private EditText editTextSearch;
    private ListView listViewStudents;
    private List<StudentInfo> studentList;
    private StudentAdapter studentAdapter;
    private StudentInfoDao studentInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        editTextSearch = findViewById(R.id.editTextSearch);
        listViewStudents = findViewById(R.id.listViewStudents);

        // 获取学生表接口
        studentInfoDao = MyApplication.getDatabase().studentInfoDao();

        // 初始化适配器，初始数据为空
        studentAdapter = new StudentAdapter(this, new ArrayList<StudentInfo>(), null);
        listViewStudents.setAdapter(studentAdapter);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString();
                if (!query.isEmpty()) {
                    loadStudentList(query);
                } else {
                    // 当查询字符串为空时，清空ListView的数据
                    studentAdapter.updateData(new ArrayList<StudentInfo>());
                }
            }
        });
    }

    private void loadStudentList(String query) {
        // 在后台线程中查询数据库
        new Thread(() -> {
            // 根据查询过滤学生列表
            List<StudentInfo> filteredList = studentInfoDao.getStudentsByName(query);
            // 更新UI在主线程
            runOnUiThread(() -> {
                // 更新适配器数据
                studentAdapter.updateData(filteredList);
            });
        }).start();
    }

}