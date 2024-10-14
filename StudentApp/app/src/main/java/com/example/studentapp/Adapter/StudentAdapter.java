package com.example.studentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.studentapp.R;
import com.example.studentapp.enity.StudentInfo;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<StudentInfo> {
    private final OnItemClickListener listener;

    public void updateData(List<StudentInfo> students) {
        clear(); // 清除旧数据
        addAll(students); // 添加新数据
        notifyDataSetChanged(); // 通知数据变化
    }

    // 定义的两个接口
    public interface OnItemClickListener {
        void onEditClick(StudentInfo student);

        void onDeleteClick(StudentInfo student);
    }

    public StudentAdapter(@NonNull Context context, List<StudentInfo> students, OnItemClickListener listener) {
        super(context, 0, students);
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 获取当前项的数据
        StudentInfo student = getItem(position);

        // 检查convertView是否已经被实例化
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_student, parent, false);
        }

        // 通过ID找到布局中的TextView，并设置数据
        TextView textViewStuName = convertView.findViewById(R.id.textViewStuName);
        TextView textViewStuGender = convertView.findViewById(R.id.textViewStuGender);
        TextView textViewStuAge = convertView.findViewById(R.id.textViewStuAge);
        TextView textViewStuClass = convertView.findViewById(R.id.textViewStuClass);
        TextView textViewStuIdcard = convertView.findViewById(R.id.textViewStuIdcard);
        TextView textViewStuDate = convertView.findViewById(R.id.textViewStuDate);

        textViewStuName.setText(student.getStuName());
        textViewStuGender.setText("Man".equals(student.getStuGender()) ? "男" : "女");
        textViewStuAge.setText(String.valueOf(student.getStuAge()));
        textViewStuClass.setText(student.getStuClass());
        textViewStuIdcard.setText(student.getStuIdcard());
        textViewStuDate.setText(student.getStuDate());

        Button buttonEdit = convertView.findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(v -> listener.onEditClick(student));

        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(v -> listener.onDeleteClick(student));

        // 返回配置好的convertView
        return convertView;
    }
}
