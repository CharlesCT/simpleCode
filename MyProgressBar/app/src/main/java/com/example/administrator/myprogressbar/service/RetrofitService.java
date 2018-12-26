package com.example.administrator.myprogressbar.service;


import com.example.administrator.myprogressbar.model.bean.UserBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Charles on 2018/11/13.
 * com.example.administrator.myprogressbar.service
 */

public interface RetrofitService {

    @GET
    io.reactivex.Observable<ResponseBody> downloadAdFile(@Url String url);

    @GET
    Call<ResponseBody>downloadADfile(@Url String url);
}
