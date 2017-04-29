package com.zhuoxin.administrator.news.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zhuoxin.administrator.news.R;
import com.zhuoxin.administrator.news.activity.NewsDetailActivity;
import com.zhuoxin.administrator.news.entity.NewsJson;
import com.zhuoxin.administrator.news.utils.BitmapAsyncTask;
import com.zhuoxin.administrator.news.utils.DBUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    //1创建Context，数据 并在构建方法中初始化
    Context context;
    List<NewsJson.ResultBean.DataBean> newsList;
    Activity activity;
    boolean isNewsFragment;
    public NewsAdapter(Context context, List<NewsJson.ResultBean.DataBean> newsList,Activity activity,boolean isNewsFragment) {
        this.context = context;
        this.newsList = newsList;
        this.activity=activity;
        this.isNewsFragment=isNewsFragment;
    }


    //2在ViewHolder类部类中的方法中把外部填充好的itemView拿进来，作为ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_image;
        TextView tv_title;
        TextView tv_date;
        TextView tv_category;
        public ViewHolder(View itemView) {
            super(itemView);
            //把ViewHolder的控件进行初始化
            iv_image= (ImageView) itemView.findViewById(R.id.iv_image);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title);
            tv_date= (TextView) itemView.findViewById(R.id.tv_date);
            tv_category= (TextView) itemView.findViewById(R.id.tv_category);

        }
    }
    //3.计数
    @Override
    public int getItemCount() {
        return newsList.size();
    }
    //4.填充布局，把布局传递到ViewHolder中
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //填充布局
        View itemView=LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
        //把填充好的布局传递到ViewHolder中，并返回viewHolder
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }
    //绑定视图，展示数据
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final NewsJson.ResultBean.DataBean news=newsList.get(position);
        holder.tv_title.setText(news.getTitle());
        holder.tv_date.setText(news.getDate());
        holder.tv_category.setText(news.getCategory());
        holder.iv_image.setImageResource(R.mipmap.ic_launcher);
        View itemView=holder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,news.getTitle(),Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                bundle.putString("url",news.getUrl());
                bundle.putString("imageURL",news.getThumbnail_pic_s());
                bundle.putString("title",news.getTitle());
                Intent intent=new Intent(activity, NewsDetailActivity.class);
                intent.putExtra("bundle",bundle);
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity,holder.iv_image,"newsTransition").toBundle());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(isNewsFragment){
                    AlertDialog alertDialog=new AlertDialog.Builder(context)
                            .setTitle("收藏提示")
                            .setMessage("确定要收藏吗？")
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定",     new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DBUtil.insertNews(context,news);
                                }
                            }).create();
                    alertDialog.show();
                }

                else{
                    AlertDialog alertDialog=new AlertDialog.Builder(context)
                            .setTitle("删除提示")
                            .setMessage("确定要删除吗？")
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                DBUtil.deleteNews(context,news);
                                    newsList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position,newsList.size());
                                }
                            }).create();
                    alertDialog.show();
                }

                return true;
            }
        });
//        final Handler handler=new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                Bitmap bitmap= (Bitmap) msg.obj;
//                holder.iv_image.setImageBitmap(bitmap);
//            }
//        };
//        holder.iv_image.setImageResource(R.mipmap.ic_launcher);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//               final Bitmap bitmap = HttpUtil.getBitmap(news.getThumbnail_pic_s());
////                holder.iv_image.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        holder.iv_image.setImageBitmap(bitmap);
////                    }
////                });
//                Message message=handler.obtainMessage();
//                message.obj=bitmap;
//                handler.sendMessage(message);
//            }
//        }).start();
//        BitmapAsyncTask bitmapAsyncTask=new BitmapAsyncTask(holder.iv_image);
//        bitmapAsyncTask.execute(news.getThumbnail_pic_s());
                Picasso.with(context)
                .load(news.getThumbnail_pic_s())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.color.colorPrimary)
                .into(holder.iv_image);
    }
}
