package com.example.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;//创建数据库

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.bean.LinkMan;

import java.util.ArrayList;
import java.util.List;

public class MyDataHelper extends SQLiteOpenHelper {
    public static final String COLUMN_USERNAME = "username";

    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_NAME = "contract";
    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_VCHAT = "vChat";
    public static final String COLUMN_PHOTOID = "photoId";

    public SQLiteDatabase mDatabase;
    public MyDataHelper(@Nullable Context context) {
        super(context,  "contract.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //String sql= "CREATE TABLE contract" + " ("+COLUMN_ID+"INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_NUMBER + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_VCHAT + " TEXT," + COLUMN_PHOTOID + " TEXT);";
        String sql = "create table contract(id integer primary key,name text,number text,address text,vChat text,photoId text)";
        sqLiteDatabase.execSQL(sql);

        //创建 user 表的SQL语句
        String createTableSql = "CREATE TABLE user (id INTEGER PRIMARY KEY, username TEXT, password TEXT)";
        //执行 SQL 语句创建 user 表
        sqLiteDatabase.execSQL(createTableSql);
       //插入示例数据
        ContentValues values = new ContentValues();
        values.put("username", "admin");
        values.put("password", "admin");
        sqLiteDatabase.insert("user", null, values);
        values.clear();
        values.put("name","Alice");
        values.put("number","13297011629");
        values.put("address","翻斗花园");
        values.put("vChat","zhangsan");
        values.put("photoId",String.valueOf(R.drawable.p1));
        sqLiteDatabase.insert("contract", null, values);
        values.clear();
        values.put("name","Lisi");
        values.put("number","123456789");
        values.put("address","翻斗花园二号街");
        values.put("vChat","lisi");
        values.put("photoId",String.valueOf(R.drawable.p2));
        sqLiteDatabase.insert("contract", null, values);
        values.clear();
        values.put("name","Lucy");
        values.put("number","123456789");
        values.put("address","翻斗花园3号街");
        values.put("vChat","lisi");
        values.put("photoId",String.valueOf(R.drawable.p3));
        sqLiteDatabase.insert("contract", null, values);
        values.clear();
        values.put("name","Anna");
        values.put("number","123456789");
        values.put("address","翻斗花园4号街");
        values.put("vChat","lilis");
        values.put("photoId",String.valueOf(R.drawable.p7));
        sqLiteDatabase.insert("contract", null, values);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
    public SQLiteDatabase openDatabase(){
        mDatabase=getWritableDatabase();
        return mDatabase;
    }
    public void closeDatabase(){
        if(mDatabase!=null&&mDatabase.isOpen()){
            mDatabase.close();
        }
    }
    public String addOne(LinkMan linkman){
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_NAME,linkman.getName());
        cv.put(COLUMN_NUMBER,linkman.getNumber());
        cv.put(COLUMN_ADDRESS,linkman.getAddress());
        cv.put(COLUMN_VCHAT,linkman.getVchat());
        cv.put(COLUMN_PHOTOID,linkman.getPhotoId());

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long insert=sqLiteDatabase.insert(TABLE_NAME,TABLE_NAME,cv);
        if(insert==-1){
            return "fail";
        }
        sqLiteDatabase.close();
        return "success";
    }
    public String deleteOne(LinkMan linkman){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int result=sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{String.valueOf(linkman.getId())});
        if(result==0){
            return "fail";
        }
        sqLiteDatabase.close();
        return "success";
    }
    public String updateOne(LinkMan linkman){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_NAME,linkman.getName());
        cv.put(COLUMN_NUMBER,linkman.getNumber());
        cv.put(COLUMN_ADDRESS,linkman.getAddress());
        cv.put(COLUMN_VCHAT,linkman.getVchat());
        cv.put(COLUMN_PHOTOID,linkman.getPhotoId());

        int result=sqLiteDatabase.update(TABLE_NAME,cv,COLUMN_ID+"=?",new String[]{String.valueOf(linkman.getId())});
        if(result==0){
            return "fail";
        }
        sqLiteDatabase.close();
        return "success";
    }
    @SuppressLint("Range")
    public List<LinkMan> selectAll(){
        List<LinkMan> contactList = new ArrayList<>();
        // 查询所有的数据
        String selectQuery = "select * from contract";

        Cursor cursor = mDatabase.rawQuery(selectQuery, null);
        //System.out.println("数据："+cursor.getString(cursor.getColumnIndex("name")));
        // 循环遍历Cursor对象，将数据存储在List中
        if (cursor.moveToFirst()) {
            System.out.println("有数据");
            do {
                LinkMan linkMan = new LinkMan();
                linkMan.setId(cursor.getInt(0));
                linkMan.setName(cursor.getString(1));
                linkMan.setNumber(cursor.getString(2));
                linkMan.setAddress(cursor.getString(3));
                linkMan.setVchat(cursor.getString(4));;
                linkMan.setPhotoId(cursor.getString(5));
                contactList.add(linkMan);
            } while (cursor.moveToNext());
        }

        // 关闭Cursor和数据库连接
        cursor.close();
        // 返回List
        return contactList;
    }
    public List<LinkMan> getByName(String str){
        String sql="SELECT * FROM contract WHERE name LIKE '%" + str + "%'";
        Cursor cursor = mDatabase.rawQuery(sql, null);
        List<LinkMan> contacts = new ArrayList<>();
        if (cursor.moveToFirst()) {
            System.out.println("有数据");
            do {
                LinkMan linkMan = new LinkMan();
                linkMan.setId(cursor.getInt(0));
                linkMan.setName(cursor.getString(1));
                linkMan.setNumber(cursor.getString(2));
                linkMan.setAddress(cursor.getString(3));
                linkMan.setVchat(cursor.getString(4));;
                linkMan.setPhotoId(cursor.getString(5));
                contacts.add(linkMan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        System.out.println("返回了数据："+contacts);
        return contacts;
    }
    public LinkMan getOneByName(String name){

        String query = "SELECT * FROM contract WHERE name = ?";

        Cursor cursor = mDatabase.rawQuery(query, new String[]{name});
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("number"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            String vChat = cursor.getString(cursor.getColumnIndexOrThrow("vChat"));
            String photoId = cursor.getString(cursor.getColumnIndexOrThrow("photoId"));
            return new LinkMan(id, name, phoneNumber, address, vChat, photoId);
        } else {
            return null;
        }
    }

    public boolean login(String username, String password) {
        // 获取一个可读的 SQLiteDatabase 对象
        // 构造查询语句
        String query = "SELECT * FROM user WHERE username=? AND password=?";
        String[] selectionArgs = {username, password};
        // 执行查询，获取查询结果的游标
        Cursor cursor = mDatabase.rawQuery(query, selectionArgs);
        // 判断查询结果是否存在数据
        boolean result = cursor.moveToFirst();
        // 关闭游标和数据库连接
        cursor.close();
        // 返回查询结果
        return result;
    }
}
