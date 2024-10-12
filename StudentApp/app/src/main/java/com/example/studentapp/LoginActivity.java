package com.example.studentapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.Dao.UserInfoDao;
import com.example.studentapp.Util.ToastUtil;

public class LoginActivity extends AppCompatActivity {

    private UserInfoDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 获取页面中的组建
        EditText user_edit = findViewById(R.id.et_account);
        EditText password_edit = findViewById(R.id.et_password);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String user = bundle.getString("request_user");
            String password = bundle.getString("request_password");
            // 使用接收到的数据
            user_edit.setText(user);
            password_edit.setText(password);
        }

        // 用户数据库
        userDao = MyApplication.getDatabase().userInfoDao();

        // 登录
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取用户输入数据
                String user = user_edit.getText().toString();
                String password = password_edit.getText().toString();
                // 还需要校验用户名是否存在
                boolean user_is_empty = userDao.isUserNameExists(user);
                if (user_is_empty) {
                    // 通过用户名字查找密码配对
                    String UserPassword = userDao.getUserPasswordByName(user);
                    if (password.equals(UserPassword)) {
                        // 校验成功，转跳内部首页
//                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);

                    } else {
                        // 失败给出提示
                        password_edit.setText("");
                        password_edit.setHint("输入密码错误");
                        password_edit.setHintTextColor(Color.RED);
                        ToastUtil.showToast(LoginActivity.this, "请重新输入");
                    }
                } else {
                    // 清空栏目
                    user_edit.setText("");
                    password_edit.setText("");
                    // 给出提示
                    ToastUtil.showToast(LoginActivity.this, "账号不存在");
                }


            }
        });

    }


    // 注册界面
    public void toRegister(View view) {
        Intent intent = new Intent(this, RightActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // 忘记密码页面
    public void forgetPassword(View view) {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}