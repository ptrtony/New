package com.zhuoxin.administrator.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTitleStrip;

import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */

public class ScrollingAdapter extends FragmentPagerAdapter {
    //构造函数
    List<Fragment> fragmentList;
    String[] pagerTitle=new String[]{"头条","娱乐","国际","科技","社会","国内","财经","时尚","体育","小说"};
    public ScrollingAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }
    //获取总数
    @Override
    public int getCount() {
        return fragmentList.size();
    }
    //获取当前位置的内容
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTitle[position];
    }
}
