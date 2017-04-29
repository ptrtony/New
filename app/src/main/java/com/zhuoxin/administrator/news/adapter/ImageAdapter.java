package com.zhuoxin.administrator.news.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhuoxin.administrator.news.R;
import com.zhuoxin.administrator.news.entity.ImageJsonBean;
import com.zhuoxin.administrator.news.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    Context context;
    List<ImageJsonBean.ResultsBean> ImageUrlList=new ArrayList<>();
    boolean isimageFragment;

    public ImageAdapter(Context context, List<ImageJsonBean.ResultsBean> imageUrlList, Activity activity,boolean isimageFragment) {
        this.context = context;
        this.ImageUrlList = imageUrlList;
        this.isimageFragment=isimageFragment;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_show_image;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_show_image= (ImageView) itemView.findViewById(R.id.iv_show_image);
        }
    }
    @Override
    public int getItemCount() {
        return ImageUrlList.size();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ImageJsonBean.ResultsBean images=ImageUrlList.get(position);
        Picasso.with(context)
                .load(ImageUrlList.get(position).getUrl())
                .placeholder(R.color.colorPrimary)
                .error(R.mipmap.ic_launcher)
                .into(holder.iv_show_image);
        holder.iv_show_image.setImageResource(R.mipmap.ic_launcher);
        View itemView = holder.itemView;
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (isimageFragment) {
                        AlertDialog dialog = new AlertDialog.Builder(context)
                                .setTitle("收藏提示")
                                .setMessage("你确定要收藏吗？")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("收藏", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DBUtil.insertImages(context, images);
                                    }
                                }).create();
                        dialog.show();


                    } else {
                        AlertDialog dialog = new AlertDialog.Builder(context)
                                .setTitle("删除提示")
                                .setMessage("你确定要删除吗？")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DBUtil.deleteImage(context, images);
                                        ImageUrlList.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position,ImageUrlList.size());
                                    }
                                }).create();
                        dialog.show();
                    }
                    return true;
                }

            });
//        Picasso.with(context)
//                .load(images.getUrl())
//                .error(R.mipmap.ic_launcher)
//                .placeholder(R.color.colorPrimary)
//                .into(holder.iv_show_image);
    }
//    public static Bitmap getBitmap(){
//        final LruCache<String,Bitmap> bitmapLruCache=new LruCache<String,Bitmap>(10*1024*1024){
//
//    }





}
