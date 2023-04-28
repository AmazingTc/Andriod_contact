package com.example.myapplication.active;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.bean.LinkMan;

import java.util.List;
public class LinkManAdapter extends ArrayAdapter<LinkMan> {
    public LinkManAdapter(@NonNull Context context, int resource, @NonNull List<LinkMan> objects) {
        super(context, resource, objects);
    }
    //每个子项被滚动到屏幕内的时候会被调用
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinkMan linkMan= (LinkMan) getItem(position);//得到当前项的linkman实例
        //为每一个子项加载设定的布局
        View view= LayoutInflater.from(getContext()).inflate(R.layout.linkman_item,parent,false);
        //分别获取 LinearLayout, image view 和 textview 的实例
        ImageView pImg =view.findViewById(R.id.pImg);
        TextView pName =view.findViewById(R.id.pName);
        TextView pNumber=view.findViewById(R.id.pNumber);
        // 设置要显示的图片和文字
        String img=linkMan.getPhotoId();
        //给图片赋值src
        pImg.setImageResource(Integer.parseInt(img));
        pName.setText(linkMan.getName());
        pNumber.setText(linkMan.getNumber());
        return view;
    }
    public void setData(List<LinkMan>  newData) {
        clear();
        addAll(newData);
        notifyDataSetChanged();
    }
}
