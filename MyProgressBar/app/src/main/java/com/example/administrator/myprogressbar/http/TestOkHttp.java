package com.example.administrator.myprogressbar.http;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestOkHttp {
    //自定义上传文件类型
    public static final MediaType MEDIA_TYPE_MARK = MediaType.parse("text/x=markdown;charset=UTF-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static void getFilePost(){
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        RequestBody body = new FormBody.Builder()
                .add("testNmae","CT")
                .build();
        File file = new File(path,"test.text");
        //组装参数
        Request request = new Request.Builder()
                .url("www.baidu.com")
                .post(RequestBody.create(MEDIA_TYPE_MARK,file))
                .build();

        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }



    //有时候传递的参数包含了 文件和 其他类型的参数这时候需要使用
    public static void getMultipartPost(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title","test")
                .addFormDataPart("imge","test.jpg",RequestBody.create(MEDIA_TYPE_PNG,new File("/addd/test.jpg")))
                .build();

        //设置请求头
        Request request = new Request.Builder()
                .header("Authorization","Client-ID" +"SSS")
                .url("https://www.baidu.com")
                .post(requestBody)
                .build();
        //设置超时时间
        File sdCache = Environment.getExternalStorageDirectory();
        int cacheSize = 10*1024*1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile(),cacheSize));

        OkHttpClient mOkhttpClient = builder.build();
        mOkhttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });






    }






}
