package com.example.administrator.myprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ListView;

import com.example.administrator.myprogressbar.widget.HorizontalView;

public class TetstHorizontalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_diy_horizontal_view);
        HorizontalView view = (HorizontalView) findViewById(R.id.horiziontal);
        ListView view1 = (ListView) findViewById(R.id.list_1);
        ListView view2 = (ListView) findViewById(R.id.list_2);
    }












}
