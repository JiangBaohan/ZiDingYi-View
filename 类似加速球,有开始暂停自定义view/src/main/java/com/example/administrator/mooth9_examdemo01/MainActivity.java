package com.example.administrator.mooth9_examdemo01;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button zanting;
    private MyView myView;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            myView.setData(true);
            myView.setRtate(false);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);myView = (MyView) findViewById(R.id.myview);
        button = (Button) findViewById(R.id.zidong);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.sendEmptyMessage(0);

            }
        });
        zanting = (Button) findViewById(R.id.zanting);
        zanting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setData(false);
            }
        });
    }
}
