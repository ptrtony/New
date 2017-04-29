package com.zhuoxin.administrator.news.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.administrator.news.R;
import com.zhuoxin.administrator.news.adapter.ScrollingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhuoxin.administrator.news.R.id.top;

/**
 * Created by Administrator on 2017/1/11.
 */

public class ScrollingFragment extends Fragment {
    //ButterKnife黄油刀，用来寻找View和设置各种单击事件
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    //创建数据  创建ViewPager适配器
    List<Fragment> fragmentList;
    ScrollingAdapter scrollingAdapter;
    @BindView(R.id.tablayout)
    TabLayout tablayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_scrolling, container, false);
        //初始化ButterKnife
        ButterKnife.bind(this, view);
        //初始化各种数据，设置到ViewPager上
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragment("top"));
        fragmentList.add(new NewsFragment("yule"));
        fragmentList.add(new NewsFragment("guoji"));
        fragmentList.add(new NewsFragment("keji"));
        fragmentList.add(new NewsFragment("shehui"));
        fragmentList.add(new NewsFragment("guonei"));
        fragmentList.add(new NewsFragment("caijing"));
        fragmentList.add(new NewsFragment("shishang"));
        fragmentList.add(new NewsFragment("tiyu"));
        fragmentList.add(new NewsFragment("xiaoshuo"));
        scrollingAdapter = new ScrollingAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(scrollingAdapter);
        tablayout.addTab(tablayout.newTab().setText("头条"));
        tablayout.addTab(tablayout.newTab().setText("娱乐"));
        tablayout.addTab(tablayout.newTab().setText("国际"));
        tablayout.addTab(tablayout.newTab().setText("科技"));
        tablayout.addTab(tablayout.newTab().setText("社会"));
        tablayout.addTab(tablayout.newTab().setText("国内"));
        tablayout.addTab(tablayout.newTab().setText("财经"));
        tablayout.addTab(tablayout.newTab().setText("时尚"));
        tablayout.addTab(tablayout.newTab().setText("体育"));
        tablayout.addTab(tablayout.newTab().setText("小说"));
        tablayout.setupWithViewPager(viewPager);
        return view;

    }
}
