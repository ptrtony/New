package com.zhuoxin.administrator.news.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/1/10.
 */

public class BitmapAsyncTask extends AsyncTask<String,Void,Bitmap> {
    ImageView imageView;

    public BitmapAsyncTask(ImageView imageView) {
        this.imageView = imageView;
    }

//    AsyncTask<String,Void,Bitmap> asyncTask=new AsyncTask<String, Void, Bitmap>() {
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            return HttpUtil.getBitmap(params[0]);
//        }
//    };

    @Override
    protected Bitmap doInBackground(String... params) {
        return HttpUtil.getBitmap(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
