package com.example.administrator.myprogressbar.service;

import android.text.TextUtils;

import com.example.administrator.myprogressbar.myApplication.MyApplication;
import com.example.administrator.myprogressbar.utils.L;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Charles on 2018/11/13.
 * com.example.administrator.myprogressbar.service
 * 这个类是为了提供retrifit单例对象 和 token登录配置
 */

public class RetrofitServiceInstance {

    private static RetrofitService sInstance = null;

    public static RetrofitService getInstance(){

        okhttp3.Authenticator authenticator = new Authenticator() {
            @Override
            public Request authenticate(Route route,Response response) throws IOException {
                //当服务器返回的状态码为401时，会自动执行里面的代码，也就实现了自动刷新token
                return response.request().newBuilder()
                        .addHeader("token","")
                        .build();

            }
        };
        Interceptor interceptor = new Interceptor() {//全局拦截器，往请求头部添加token字段，就实现了全局token
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request tokenRequest =  null;
                if (TextUtils.isEmpty(MyApplication.getToken())){
                    return chain.proceed(originalRequest);
                }
                tokenRequest = originalRequest.newBuilder()
                        .header("token",MyApplication.getToken())
                        .build();
                return chain.proceed(tokenRequest);

            }
        };

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {//log拦截器，打印所有的log
            @Override
            public void log(String message) {
                L.d(message);
            }
        });



        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .authenticator(authenticator)
                .connectTimeout(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true).build();

        if (sInstance==null){
            synchronized (RetrofitService.class){
                if (sInstance == null){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://baidu.com")
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加rxjava回调转换
                            .build();
                        sInstance = retrofit.create(RetrofitService.class);
                }

            }
        }
        return sInstance;

    }











}






