package com.zhuoxin.administrator.news.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.administrator.news.R;
import com.zhuoxin.administrator.news.adapter.NewsAdapter;
import com.zhuoxin.administrator.news.entity.NewsJson;
import com.zhuoxin.administrator.news.utils.DBUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectFragment extends Fragment {


    @BindView(R.id.vr_collect)
    RecyclerView vr_collect;
    List<NewsJson.ResultBean.DataBean> dataBeanList;
    NewsAdapter newsAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_collect_fragment, container, false);
        ButterKnife.bind(this, view);
        dataBeanList=DBUtil.queryAllNews(getContext());
        newsAdapter=new NewsAdapter(getContext(),dataBeanList,getActivity(),false);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        vr_collect.setLayoutManager(layoutManager);
        vr_collect.setAdapter(newsAdapter);
        return view;
    }
}
