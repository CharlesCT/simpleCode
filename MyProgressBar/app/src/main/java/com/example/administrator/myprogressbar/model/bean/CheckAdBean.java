package com.example.administrator.myprogressbar.model.bean;

/**
 * Created by Charles on 2018/11/14.
 * com.example.administrator.myprogressbar.model.bean
 * 检查服务器是否需要展示开屏广告
 */

public class CheckAdBean {
    private boolean isPlay = false;//默认不展示
    private int delay ;       //展示时间

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
