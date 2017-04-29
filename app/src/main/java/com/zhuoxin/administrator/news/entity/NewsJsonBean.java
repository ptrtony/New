package com.zhuoxin.administrator.news.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 * 手动写的数据
 */

public class NewsJsonBean {
    String reason;

    Result result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result{
        String stat;
        List<News> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<News> getData() {
            return data;
        }

        public void setData(List<News> data) {
            this.data = data;
        }
    }
}
