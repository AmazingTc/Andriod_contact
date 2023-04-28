package com.example.myapplication.active;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class HomeActivity extends AppCompatActivity {

    private Button managerBtn;
    private Button sysSettingBtn;
    private Button helpBtn;
    private Button loginOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        managerBtn = findViewById(R.id.manager);
        sysSettingBtn = findViewById(R.id.sysSetting);
        helpBtn = findViewById(R.id.help);
        loginOutBtn = findViewById(R.id.exitLogin);

        managerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EditLinkman.class);
                startActivity(intent);
            }
        });
        sysSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        loginOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(HomeActivity.this)
                        .setMessage("确定要退出应用吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 退出应用
                                finishAffinity(); // 关闭所有Activity
                                System.exit(0); // 结束进程
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }
}
