package com.zhuoxin.administrator.news.Fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhuoxin.administrator.news.R;
import com.zhuoxin.administrator.news.adapter.NewsAdapter;
import com.zhuoxin.administrator.news.entity.NewsJson;
import com.zhuoxin.administrator.news.utils.HttpUtil;
import com.zhuoxin.administrator.news.utils.JsonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment {
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    List<NewsJson.ResultBean.DataBean> newsList;
    String type;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    public NewsFragment() {

}

    public NewsFragment(String s) {
        this.type=s;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

           switch (msg.what){
               case 0:
                   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                   recyclerView.setLayoutManager(linearLayoutManager);
                   recyclerView.setAdapter(newsAdapter);
                   break;
               case 1:
                   Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                   break;

           }
            srl.setRefreshing(false);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_news_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMessage();

            }
        });
        loadMessage();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String jsonStr = HttpUtil.getNewsInfo("http://v.juhe.cn/toutiao/index?type=top&key=d728ab4e75e137c4f23aec12ed3ee6cd");
//                newsList= JsonUtil.parseJson(jsonStr);
//               newsAdapter=new NewsAdapter(getActivity(),newsList);
//                recyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
//                        recyclerView.setLayoutManager(linearLayoutManager);
//                        recyclerView.setAdapter(newsAdapter);
//                    }
//                });
//            }
//        }).start();
    }
    private void loadMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String urlStr = "http://v.juhe.cn/toutiao/index?type=" + type + "&key=d728ab4e75e137c4f23aec12ed3ee6cd";
                HttpUtil.HttpCallBack callBack = new HttpUtil.HttpCallBack() {
                    @Override
                    public void callBackSuccess(String jsonStr) {
                        newsList = JsonUtil.parseGsonWithJson(jsonStr);
                        newsAdapter = new NewsAdapter(getActivity(), newsList, getActivity(),true);
                        handler.sendEmptyMessage(0);

                    }

                    @Override
                    public void callBackFailed() {
                        handler.sendEmptyMessage(1);

                    }
                };

                HttpUtil.getNewsInfo(urlStr,callBack);

            }
        }).start();
    }
}
