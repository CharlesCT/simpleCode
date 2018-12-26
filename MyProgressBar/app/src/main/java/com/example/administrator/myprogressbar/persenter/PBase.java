package com.example.administrator.myprogressbar.persenter;

import com.example.administrator.myprogressbar.base.IBaseView;
import com.example.administrator.myprogressbar.base.IPBase;

/**
 * Created by Administrator on 2018/11/13.
 */

public class PBase<V extends IBaseView> implements IPBase<V> {

    public V view;

    @Override
    public void attachView(V view) {

        this.view = view;

    }

    @Override
    public void disAttachView() {
        this.view = null;
    }
    public boolean isActive(){return view!=null;}

    public V getView() throws Exception {
        if (isActive()){
            return view;
        }
        throw new Exception("View is not Active");
    }


}
