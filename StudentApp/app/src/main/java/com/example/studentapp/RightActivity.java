package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.studentapp.Dao.UserInfoDao;
import com.example.studentapp.Util.ToastUtil;
import com.example.studentapp.enity.UserInfo;

import java.util.Objects;

public class RightActivity extends AppCompatActivity {

    private String userInfo;
    private String password;
    private String verifyPassword;
    private UserInfoDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_right);

        // stop1：获取到页面中输入的内容
        // 用户名
        EditText userInfo_edit = findViewById(R.id.et_account);
        // 密码
        EditText password_edit = findViewById(R.id.et_password);
        // 确认密码
        EditText verifyPassword_edit = findViewById(R.id.et_password_Confirm);

        // 在android中如何调用数据库的呢？
        // stop2：获取数据库实列
        userDao = MyApplication.getDatabase().userInfoDao();

        // stop3：注册事件
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取数据一定要在填写完成之后
                userInfo = userInfo_edit.getText().toString();
                password = password_edit.getText().toString();
                verifyPassword = verifyPassword_edit.getText().toString();
                // 两次密码一致
                if (Objects.equals(password, verifyPassword)) {
                    // stop4：组织数据结构
                    UserInfo user = new UserInfo();
                    user.setUserName(userInfo);
                    user.setPassword(password);
                    // stop5：提交数据
                    userDao.insert(user);
                    ToastUtil.showToast(RightActivity.this, "注册成功！");
                    // stop6：注册成功后，返回登陆页面
                    // 从下级页面获取数据后返回上级应该怎么做？
                    Intent intent = new Intent(RightActivity.this, LoginActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("request_user", userInfo);
                    bundle.putString("request_password", password);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                // 两次密码不一致
                else {
                    ToastUtil.showToast(RightActivity.this, "两次密码输入不一致");
                }


            }
        });
    }
}
