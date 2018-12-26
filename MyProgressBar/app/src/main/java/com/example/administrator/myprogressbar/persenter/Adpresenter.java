package com.example.administrator.myprogressbar.persenter;

import com.example.administrator.myprogressbar.contract.AdContract;
import com.example.administrator.myprogressbar.model.UserModel;
import com.example.administrator.myprogressbar.model.bean.CheckAdBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Charles on 2018/11/14.
 * com.example.administrator.myprogressbar.persenter
 */

public class AdPresenter<V extends AdContract.View> extends PBase<V> {

    private UserModel mUserModel;

    public AdPresenter(){
        mUserModel = new UserModel();

    }

    public void getStartAdPicture(){
        mUserModel.getServiceCheck()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<CheckAdBean>() {
                    @Override
                    public void accept(CheckAdBean checkAdBean) throws Exception {

                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<CheckAdBean, Integer>() {
                    @Override
                    public Integer apply(CheckAdBean checkAdBean) throws Exception {
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());





    }

















}
