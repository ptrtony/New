package com.zhuoxin.administrator.news.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.administrator.news.R;
import com.zhuoxin.administrator.news.adapter.ImageAdapter;
import com.zhuoxin.administrator.news.entity.ImageJsonBean;
import com.zhuoxin.administrator.news.retrofit.ImageAPI;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageFragment extends Fragment {


    @BindView(R.id.rv_image)
    RecyclerView rv_image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_image_fragment, container, false);
        ButterKnife.bind(this, view);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ImageAPI imageAPI=retrofit.create(ImageAPI.class);
        Call<ImageJsonBean> getImageCall=imageAPI.getRespons();
        getImageCall.enqueue(new Callback<ImageJsonBean>() {
            @Override
            public void onResponse(Call<ImageJsonBean> call, Response<ImageJsonBean> response) {
                ImageJsonBean imageJsonBean=response.body();
                List<ImageJsonBean.ResultsBean> resultsBeanList=imageJsonBean.getResults();
                ImageAdapter imageAdapter=new ImageAdapter(getActivity(),resultsBeanList,getActivity(),true);
                StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                rv_image.setLayoutManager(layoutManager);
                rv_image.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<ImageJsonBean> call, Throwable t) {

            }
        });
        return view;
    }
}
