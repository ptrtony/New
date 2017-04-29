package com.zhuoxin.administrator.news.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/1/12.
 */

public class NewsHelper extends SQLiteOpenHelper {
    public NewsHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table news(title varchar," +
                "date varchar,category varchar," +
                "imageURL varchar,url varchar)");
        db.execSQL("create table images(imageUrl varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
