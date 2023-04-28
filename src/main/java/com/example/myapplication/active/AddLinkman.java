package com.example.myapplication.active;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.bean.LinkMan;
import com.example.myapplication.dao.MyDataHelper;

public class AddLinkman extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_linkman);
        EditText addName=(EditText) findViewById(R.id.addName);
        EditText addNumber=(EditText) findViewById(R.id.addNumber);
        EditText addAddress=(EditText) findViewById(R.id.addAddress);
        EditText addVChat=(EditText) findViewById(R.id.addVChat);
        Button submitAdd=(Button) findViewById(R.id.submitAdd);
        submitAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addName.getText().toString()=="" | addNumber.getText().toString()==""| addAddress.getText().toString()==""| addVChat.getText().toString()==""){return;}
                LinkMan linkman=new LinkMan(addName.getText().toString(),addNumber.getText().toString(),addAddress.getText().toString(),addVChat.getText().toString(),String.valueOf(R.drawable.p5));
                MyDataHelper helper=new MyDataHelper(AddLinkman.this);
                helper.openDatabase();
                String result=helper.addOne(linkman);
                if(result=="success"){
                    Toast.makeText(AddLinkman.this,"添加成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddLinkman.this, EditLinkman.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddLinkman.this,"添加失败",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddLinkman.this, EditLinkman.class);
                    startActivity(intent);
                }
                helper.closeDatabase();
            }
        });
    }
}
