package com.example.administrator.myprogressbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.myprogressbar.base.BaseActivity;
import com.example.administrator.myprogressbar.contract.AdContract;
import com.example.administrator.myprogressbar.persenter.Adpresenter;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;


public class MainActivity extends BaseActivity implements AdContract.View {
    private ImageView adImageView;
    private Adpresenter adpresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adImageView = (ImageView) findViewById(R.id.ad_mian);
        adpresenter = new Adpresenter();
        //这里使用软引用
        WeakReference<AdContract.View > weakReference = new WeakReference<AdContract.View >(this);
        adpresenter.getStartAdPicture();
        adpresenter.attachView(weakReference.get());
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }

            @Override
            protected void onProgressUpdate(Object[] values) {
                super.onProgressUpdate(values);
            }



        };
        asyncTask.execute(0);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Picasso.get().cancelRequest(adImageView);
        adpresenter.disAttachView();
    }

    @Override
    public void showImage(String url) {
        if (adImageView!=null){
            Picasso.get().load(url).into(adImageView);
        }


    }





}
