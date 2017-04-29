package com.zhuoxin.administrator.news.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/1/6.
 */

public class HttpUtil {


    public interface HttpCallBack{
        public void callBackSuccess(String jsonStr);
        public void callBackFailed();
    }

    public static void getNewsInfo(String url,HttpCallBack callBack){
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;

        try {
            URL targetURL = new URL(url);
            HttpURLConnection httpURLConnection= (HttpURLConnection) targetURL.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.connect();
            int requestCode=httpURLConnection.getResponseCode();
            if(requestCode==200){
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                bufferedReader=new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer=new StringBuffer();
                String msg;
                while((msg=bufferedReader.readLine())!=null){
                    stringBuffer.append(msg);
//                    System.out.println("getNewsInfo:获取数据"+stringBuffer.toString());

                }
                callBack.callBackSuccess(stringBuffer.toString());
            }
            else{
//                System.out.println("getNewssInfo:获取数据失败");
                callBack.callBackFailed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                    inputStreamReader.close();
                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    private static LruCache<String,Bitmap> bitmapLruCache=new LruCache<String,Bitmap>(1024*1024*100){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getHeight()*value.getRowBytes();
        }
    };
    public static Bitmap getBitmap(String imgURL) {
        HttpURLConnection httpURLConnection = null;
        Bitmap bitmap = bitmapLruCache.get(imgURL);
        InputStream is = null;
        if (bitmap!= null) {
            return bitmap;
        }
        else {
            try {
                URL url = new URL(imgURL);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    is = httpURLConnection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    bitmapLruCache.put(imgURL,bitmap);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        }
    }

}
