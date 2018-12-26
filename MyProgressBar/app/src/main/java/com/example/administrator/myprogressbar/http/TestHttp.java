package com.example.administrator.myprogressbar.http;
import android.text.TextUtils;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TestHttp {


    public static HttpURLConnection getHttpUrlConnection(String url){


        HttpURLConnection mHttpUrlConnection = null;
        try {
            URL mUrl = new URL(url);
            mHttpUrlConnection = (HttpURLConnection) mUrl.openConnection();
            //设置连接超时时间
            mHttpUrlConnection.setConnectTimeout(15000);
            //设置读取超时
            mHttpUrlConnection.setReadTimeout(15000);
            //
            mHttpUrlConnection.setRequestMethod("POST");//设置请求方式
            //
            mHttpUrlConnection.setRequestProperty("Connection","Keep-Alive");
            //接收数据流
            mHttpUrlConnection.setDoInput(true);
            //传递参数开启
            mHttpUrlConnection.setDoOutput(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mHttpUrlConnection;

    }

    public static void  postParams(OutputStream outputStream, List<NameVaulePairs> nameVaulePairs) throws IOException {

        StringBuilder builder = new StringBuilder();
        for (NameVaulePairs pair: nameVaulePairs){
            if (TextUtils.isEmpty(builder)){
                builder.append("&");

            }
            builder.append(URLEncoder.encode(pair.getKey(),"UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(pair.getValue(),"UTF-8"));
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    public static void useHttpUrlConnectionPost(String url){

        InputStream inputStream = null;
        HttpURLConnection mHttpUrlConnection = getHttpUrlConnection(url);

        List<NameVaulePairs> params = new ArrayList<>();
        params.add(new NameVaulePairs("www","test"));
        try {
            postParams(mHttpUrlConnection.getOutputStream(),params);
            mHttpUrlConnection.connect();
            inputStream = mHttpUrlConnection.getInputStream();
            int code = mHttpUrlConnection.getResponseCode();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }









}
