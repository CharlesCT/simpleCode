package com.example.administrator.myprogressbar.base;

/**
 * Created by Administrator on 2018/11/13.
 */

public interface IPBase<V extends IBaseView> {
    void attachView(V view);
    void disAttachView();


}
