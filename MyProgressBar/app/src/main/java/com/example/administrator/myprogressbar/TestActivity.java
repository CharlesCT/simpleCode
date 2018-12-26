package com.example.administrator.myprogressbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.myprogressbar.widget.CustomView;
import java.util.Arrays;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private CustomView mCustomView;

    private android.widget.Button start;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_layout);

        mCustomView = (CustomView) findViewById(R.id.custcomview);

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* ObjectAnimator animator1 = ObjectAnimator.ofFloat(mCustomView,"translationX" ,0.0f,200.0f,0f);

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(mCustomView,"scaleX",1.0F,2.0F);

                ObjectAnimator animator3 = ObjectAnimator.ofFloat(mCustomView,"rotationX",0.0F,90.0f,0.0F);
                AnimatorSet set = new AnimatorSet();
                set.setDuration(1000);
                set.play(animator1).with(animator2).after(animator3);
                set.start();
                mCustomView.requestLayout();*/
                List<String> list = Arrays.asList("1", "2", "3");
                Intent intent = new Intent(TestActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });



        //====================使用动画移动View
        // mCustomView.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.test_move_view));

        //======================使用Scroller来移动View

      //  mCustomView.smoothScrollTo(-10,0);


    }
    private static void log(String log) {
        Log.d("FlatMap", log);
    }





}
