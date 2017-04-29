package com.zhuoxin.administrator.news;

import com.zhuoxin.administrator.news.utils.HttpUtil;
import com.zhuoxin.administrator.news.utils.JsonUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
//    @Test
//    public void testHttp(){
//       String jsonStr= HttpUtil.getNewsInfo("http://v.juhe.cn/toutiao/index?type=top&key=d728ab4e75e137c4f23aec12ed3ee6cd");
//        JsonUtil.parseJson(jsonStr);
//    }
}