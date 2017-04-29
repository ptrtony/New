package com.zhuoxin.administrator.news;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.zhuoxin.administrator.news.entity.News;
import com.zhuoxin.administrator.news.utils.HttpUtil;
import com.zhuoxin.administrator.news.utils.JsonUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.zhuoxin.administrator.news", appContext.getPackageName());
    }

//    @Test
//    public void testHttp() {
//        String jsonStr = HttpUtil.getNewsInfo("http://v.juhe.cn/toutiao/index?type=top&key=d728ab4e75e137c4f23aec12ed3ee6cd");
//        List<News> newsList=JsonUtil.parseJson(jsonStr);
//        for(int i=0;i<newsList.size();i++){
//            System.out.println(newsList.get(i).getTitle());
//        }
//    }
}