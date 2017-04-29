package com.zhuoxin.administrator.news.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhuoxin.administrator.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.iv_news_image)
    ImageView ivNewsImage;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    String title;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        initSystemUI();
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String url = bundle.getString("url");
        String imageURL = bundle.getString("imageURL");
        title=bundle.getString("title");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        Picasso.with(this)
                .load(imageURL)
                .into(ivNewsImage);
        toolbarLayout.setTitle(title);
    }

    private void initSystemUI() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSup
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
