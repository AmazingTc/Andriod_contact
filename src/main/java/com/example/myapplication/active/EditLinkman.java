package com.example.myapplication.active;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.R.id;
import com.example.myapplication.dao.MyDataHelper;

import java.util.List;

public class EditLinkman extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linkman);
        ListView listView=(ListView) findViewById(R.id.list_view);
        EditText searchInput=(EditText) findViewById(R.id.searchInput);
        Button searchBtn=(Button) findViewById(R.id.searchBtn);

        MyDataHelper helper=new MyDataHelper(EditLinkman.this);
        helper.openDatabase();
        List list=helper.selectAll();
        helper.closeDatabase();

        //5、将适配器加载到控件中
        LinkManAdapter adapter=new LinkManAdapter(EditLinkman.this,R.layout.linkman_item,list);
        listView.setAdapter(adapter);
        ImageView addBtn=(ImageView) findViewById(R.id.addBtn);
        ImageView backHomeBtn=(ImageView) findViewById(id.backHome);
        //6、为列表中选中的项添加单击响应事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                TextView pName = view.findViewById(R.id.pName);
                String result = pName.getText().toString();
                Intent intent = new Intent(EditLinkman.this, ContactDetails.class);
                intent.putExtra("pName",result);
                startActivity(intent);
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditLinkman.this, AddLinkman.class);
                startActivity(intent);
            }
        });
        backHomeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditLinkman.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String str=searchInput.getText().toString();
                if(str==""){
                    return;
                }
                MyDataHelper helper=new MyDataHelper(EditLinkman.this);
                helper.openDatabase();
                List list=helper.getByName(str);
                helper.closeDatabase();
                // 更新适配器中的数据源
                adapter.setData(list);
                // 刷新ListView
                adapter.notifyDataSetChanged();
                // 滚动ListView到顶部
                listView.smoothScrollToPosition(0);
            }
        });




    }
}