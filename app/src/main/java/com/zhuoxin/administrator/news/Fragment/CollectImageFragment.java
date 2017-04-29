package com.zhuoxin.administrator.news.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.administrator.news.R;
import com.zhuoxin.administrator.news.adapter.ImageAdapter;
import com.zhuoxin.administrator.news.entity.ImageJsonBean;
import com.zhuoxin.administrator.news.utils.DBUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/14.
 */

public class CollectImageFragment extends Fragment {
    @BindView(R.id.rlv_collectImage)
    RecyclerView colletImage;
    List<ImageJsonBean.ResultsBean>  ImageUrlList;
    ImageAdapter imageAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.collete_image,container,false);
        ButterKnife.bind(this,view);
        ImageUrlList= DBUtil.queryAllImages(getContext());
        imageAdapter=new ImageAdapter(getContext(),ImageUrlList,getActivity(),false);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        colletImage.setLayoutManager(layoutManager);
        colletImage.setAdapter(imageAdapter);
        return view;
    }
}
