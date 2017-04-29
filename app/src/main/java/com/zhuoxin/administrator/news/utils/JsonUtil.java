package com.zhuoxin.administrator.news.utils;

import com.google.gson.Gson;
import com.zhuoxin.administrator.news.entity.News;
import com.zhuoxin.administrator.news.entity.NewsJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */

public class JsonUtil {
    public static List<News> parseJson(String jsonStr){
        List<News> newsList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(jsonStr);
            String reason=jsonObject.getString("reason");
            JSONObject result=jsonObject.getJSONObject("result");
            String stat=result.getString("stat");
            JSONArray data=result.getJSONArray("data");
            for(int i=0;i<data.length();i++){
                JSONObject newsInfo=data.getJSONObject(i);
                String uniquekey=newsInfo.getString("uniquekey");
                String title=newsInfo.getString("title");
                String date=newsInfo.getString("date");
                String category=newsInfo.getString("category");
                String url=newsInfo.getString("url");
                String thumbnail_pic_s=newsInfo.getString("thumbnail_pic_s");
//                System.out.println(title);
                News news=new News(uniquekey,title,date,category,url,thumbnail_pic_s);
                newsList.add(news);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
    public static List<NewsJson.ResultBean.DataBean>parseGsonWithJson(String jsonStr){
        Gson gson=new Gson();
        NewsJson newsJson=gson.fromJson(jsonStr,NewsJson.class);
        return newsJson.getResult().getData();
    }
}
