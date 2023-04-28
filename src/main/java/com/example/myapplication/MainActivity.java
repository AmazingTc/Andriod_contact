package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.active.HomeActivity;
import com.example.myapplication.dao.MyDataHelper;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // 添加登录按钮的点击事件
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // 调用登录方法，传入用户名和密码
                login(password, username);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usernameEditText.setText("");
        passwordEditText.setText("");
    }

    // 登录方法
    private void login(String username, String password) {
        // TODO: 实现登录逻辑
        MyDataHelper helper=new MyDataHelper(MainActivity.this);
        helper.openDatabase();
        Boolean result=helper.login(username,password);
        if(!result){
            Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        helper.closeDatabase();
    }

}