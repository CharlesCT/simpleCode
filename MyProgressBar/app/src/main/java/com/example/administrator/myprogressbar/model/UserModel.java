package com.example.administrator.myprogressbar.model;


import com.example.administrator.myprogressbar.model.bean.AdBean;
import com.example.administrator.myprogressbar.model.bean.CheckAdBean;
import com.example.administrator.myprogressbar.service.RetrofitService;
import com.example.administrator.myprogressbar.service.RetrofitServiceInstance;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;

/**
 * Created by Charles on 2018/11/13.
 * com.example.administrator.myprogressbar.model
 */

public class UserModel {

    private RetrofitService mRetrofitService = RetrofitServiceInstance.getInstance();

    /**
     * 获取开屏广告APP的图片
     * @param url
     * @return
     */
    public Observable<ResponseBody> getAdPicture(String url){



        return mRetrofitService.downloadAdFile("ww");
    }


    public Observable<CheckAdBean>getServiceCheck(){
        return Observable.create(new ObservableOnSubscribe<CheckAdBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<CheckAdBean> emitter) throws Exception {
                //假装向服务器请求
                CheckAdBean bean = new CheckAdBean();
                bean.setDelay(2);
                bean.setPlay(true);
               emitter.onNext(bean);
                emitter.onComplete();
            }
        });

    }

    public Observable<AdBean>getServerUrl(){
        return Observable.create(new ObservableOnSubscribe<AdBean>() {
            @Override
            public void subscribe(ObservableEmitter<AdBean> emitter) throws Exception {
                AdBean adBean = new AdBean();
                adBean.setPlayTime(12);
                adBean.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542719104556&di=010bdb08ddd63691aed074f25a0bebe7&imgtype=0&src=http%3A%2F%2Fp98.pstatp.com%2Forigin%2Fpgc-image%2F15392697793998a8157cc1d");
                emitter.onNext(adBean);
            }
        });




    }











}
