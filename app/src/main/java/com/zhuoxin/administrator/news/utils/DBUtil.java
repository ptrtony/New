package com.zhuoxin.administrator.news.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import com.zhuoxin.administrator.news.db.NewsHelper;
import com.zhuoxin.administrator.news.entity.ImageJsonBean;
import com.zhuoxin.administrator.news.entity.NewsJson;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Administrator on 2017/1/12.
 */

public class DBUtil {
    public static void insertNews(Context context, NewsJson.ResultBean.DataBean news){
        String title=news.getTitle();
        String date=news.getDate();
        String category=news.getCategory();
        String imageURL=news.getThumbnail_pic_s();
        String url=news.getUrl();
        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("date",date);
        values.put("category",category);
        values.put("imageURL",imageURL);
        values.put("url",url);
        NewsHelper newsHelper=new NewsHelper(context,"News.db",null,1);
        SQLiteDatabase db=newsHelper.getReadableDatabase();
        if(!queryNews(context,news)){
            db.insert("news",null,values);
        }
    }

    public static boolean queryNews(Context context, NewsJson.ResultBean.DataBean news){
        NewsHelper newsHelper=new NewsHelper(context,"News.db",null,1);
        SQLiteDatabase db=newsHelper.getReadableDatabase();
        Cursor cursor=db.query("news",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String url=cursor.getString(cursor.getColumnIndex("url"));
            String newsURL=news.getUrl();
            if(url.equals(newsURL)){
//                cursor.close();
//                db.close();
                return true;
            }
        }
//        cursor.close();
//        db.close();
        return false;

    }

    public static List<NewsJson.ResultBean.DataBean> queryAllNews(Context context){
        List<NewsJson.ResultBean.DataBean> dataBeanList=new ArrayList<>();
        NewsHelper newsHelper=new NewsHelper(context,"News.db",null,1);
        SQLiteDatabase db=newsHelper.getReadableDatabase();
        Cursor cursor=db.query("news",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            String category=cursor.getString(cursor.getColumnIndex("category"));
            String imageURL=cursor.getString(cursor.getColumnIndex("imageURL"));
            String url=cursor.getString(cursor.getColumnIndex("url"));
            NewsJson.ResultBean.DataBean news=new NewsJson.ResultBean.DataBean();
            news.setTitle(title);
            news.setDate(date);
            news.setCategory(category);
            news.setThumbnail_pic_s(imageURL);
            news.setUrl(url);
            dataBeanList.add(news);
        }
//        cursor.close();
//        db.close();
        return dataBeanList;
    }
    public static void  deleteNews(Context context, NewsJson.ResultBean.DataBean news){
        NewsHelper newsHelper=new NewsHelper(context,"News.db",null,1);
        SQLiteDatabase db=newsHelper.getReadableDatabase();
         db.delete("news","url=?",new String[]{news.getUrl()});
//        db.close();
    }
    public static void insertImages(Context context, ImageJsonBean.ResultsBean images){
        String imageURL = images.getUrl();
        ContentValues values=new ContentValues();
        values.put("imageUrl",imageURL);
        NewsHelper newsHelper=new NewsHelper(context,"Images.db",null,1);
        SQLiteDatabase db=newsHelper.getReadableDatabase();
//        if(!queryImages(context,images)){
            db.insert("images",null,values);

//        }
    }
//    public static boolean queryImages(Context context,ImageJsonBean.ResultsBean images){
//        NewsHelper newsHelper=new NewsHelper(context,"Images.db",null,1);
//        SQLiteDatabase db=newsHelper.getReadableDatabase();
//        Cursor cursor=db.query("images",null,null,null,null,null,null);
//        while (cursor.moveToNext()){
//            String url=cursor.getString(cursor.getColumnIndex("url"));
//            String imageURL=images.getUrl();
//            if(url.equals(imageURL)){
//                return true;
//            }
//        }
//        return false;
//    }
    public static List<ImageJsonBean.ResultsBean> queryAllImages(Context context){
        List<ImageJsonBean.ResultsBean> imageList=new ArrayList<>();
        NewsHelper newsHelper=new NewsHelper(context,"Images.db",null,1);
        SQLiteDatabase db=newsHelper.getReadableDatabase();
        Cursor cursor=db.query("images",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String url=cursor.getString(cursor.getColumnIndex("imageUrl"));
            ImageJsonBean.ResultsBean images=new ImageJsonBean.ResultsBean();
            images.setUrl(url);
            imageList.add(images);
        }
    // private String _id;
//        private String createdAt;
//        private String desc;
//        private String publishedAt;
//        private String source;
//        private String type;
//        private String url;
//        private boolean used;
//        private String who;
//        String id=cursor.getString(cursor.getColumnIndex("id"));
//        String createdAt=cursor.getString(cursor.getColumnIndex("createdAt"));
//        String desc=cursor.getString(cursor.getColumnIndex("desc"));
//        String publishedAt=cursor.getString(cursor.getColumnIndex("publishedAt"));
//        String source=cursor.getString(cursor.getColumnIndex("source"));
//        String type=cursor.getString(cursor.getColumnIndex("type"));

    //        String who=cursor.getString(cursor.getColumnIndex("who"));

//        images.set_id(id);
//        images.setCreatedAt(createdAt);
//        images.setDesc(desc);
//        images.setPublishedAt(publishedAt);
//        images.setSource(source);
//        images.setType(type);

//        images.setWho(who);

        return imageList;
        }
public static void deleteImage(Context context,ImageJsonBean.ResultsBean images){
        NewsHelper newsHelper=new NewsHelper(context,"Images.db",null,1);
        SQLiteDatabase db=newsHelper.getReadableDatabase();
        db.delete("images","imageUrl=?",new String[]{images.getUrl()});

        }
        }
