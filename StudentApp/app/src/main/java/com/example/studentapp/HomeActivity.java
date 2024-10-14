package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.Adapter.StudentAdapter;
import com.example.studentapp.Dao.StudentInfoDao;
import com.example.studentapp.enity.StudentInfo;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements StudentAdapter.OnItemClickListener {
    private StudentInfoDao studentDao;
    private ListView listView;
    private List<StudentInfo> studentList;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // 学生数据库对象
        studentDao = MyApplication.getDatabase().studentInfoDao();
        // 获取ListView
        listView = findViewById(R.id.listView);

        // 查询所有学生信息
        studentList = studentDao.getAllStudents();

        // 创建适配器
        adapter = new StudentAdapter(this, studentList, this);

        // 设置适配器
        listView.setAdapter(adapter);

        // 获取添加按钮
        findViewById(R.id.te_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 转跳添加页面
                Intent intent = new Intent(HomeActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 重新加载数据
        studentList = studentDao.getAllStudents();
        // 刷新适配器数据
        adapter.updateData(studentList);
    }

    @Override
    public void onEditClick(StudentInfo student) {
        Intent intent = new Intent(this, EditStudentActivity.class);
        // 将 StudentInfo 对象转换为字符串，以便可以通过 Intent 传递
        intent.putExtra("student_info", student.getStuName() + "," + student.getStuGender() + "," + student.getStuAge() + "," + student.getStuClass() + "," + student.getStuIdcard() + "," + student.getStuDate());
        // 传递学生ID
        intent.putExtra("student_id", student.getId());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(StudentInfo student) {
        // 从数据库中删除学生信息
        studentDao.delete(student);
        // 重新加载数据
        studentList = studentDao.getAllStudents();
        // 更新列表
        adapter.updateData(studentList);
    }
}