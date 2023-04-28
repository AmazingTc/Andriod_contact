package com.example.myapplication.active;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.bean.LinkMan;
import com.example.myapplication.dao.MyDataHelper;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);

        Intent intent = getIntent();
        String pName = intent.getStringExtra("pName");

        MyDataHelper helper=new MyDataHelper(ContactDetails.this);
        helper.openDatabase();
        LinkMan linkMan=helper.getOneByName(pName);
        System.out.println(linkMan);
        Integer id=linkMan.getId();
        String photoId=linkMan.getPhotoId();


        ImageView img = findViewById(R.id.detailImg);
        System.out.println(img);
        TextView name=(TextView) findViewById(R.id.detailName);
        TextView number=(TextView) findViewById(R.id.detailNumber);
        img.setImageResource(Integer.parseInt(linkMan.getPhotoId()));
        name.setText(linkMan.getName());
        number.setText(linkMan.getNumber());


        Button save=(Button) findViewById(R.id.btnSave);
        Button delete=(Button) findViewById(R.id.btnDelete);

        EditText editName=(EditText)findViewById(R.id.editName);
        EditText editNumber=(EditText)findViewById(R.id.editNumber);
        EditText editAddress=(EditText)findViewById(R.id.editAddress);
        EditText editVchat=(EditText)findViewById(R.id.editVChat);


        editName.setHint(linkMan.getName());
        editNumber.setHint(linkMan.getNumber());
        editAddress.setHint(linkMan.getAddress());
        editVchat.setHint(linkMan.getVchat());


        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyDataHelper helper=new MyDataHelper(ContactDetails.this);
                helper.openDatabase();
                LinkMan newMsg=new LinkMan(id,editName.getText().toString(),editNumber.getText().toString(),editAddress.getText().toString(),editVchat.getText().toString(),photoId);
                String result=helper.updateOne(newMsg);
                if(result=="success"){
                    Toast.makeText(ContactDetails.this,"保存成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ContactDetails.this, EditLinkman.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ContactDetails.this,"保存失败",Toast.LENGTH_LONG).show();
                }
                helper.closeDatabase();
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyDataHelper helper=new MyDataHelper(ContactDetails.this);
                helper.openDatabase();
                LinkMan newMsg=new LinkMan(id,editName.getText().toString(),editNumber.getText().toString(),editAddress.getText().toString(),editVchat.getText().toString(),photoId);
                String result=helper.deleteOne(newMsg);
                if(result=="success"){
                    Toast.makeText(ContactDetails.this,"删除成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ContactDetails.this, EditLinkman.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ContactDetails.this,"删除失败",Toast.LENGTH_LONG).show();
                }
                helper.closeDatabase();

            }
        });

    }


}