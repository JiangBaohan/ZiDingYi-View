package com.example.administrator.mooth9_exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TitleBarM tbTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        //初始化控件
        tbTitle = (TitleBarM) findViewById(R.id.tbm_title);
        //先来设置一个背景色，当然你也可以用默认的背景色
        tbTitle.setBackColor("#7067E2");
        //设置左侧文字显示文字，也可设置背景图
        tbTitle.setLeftText("返回");
        //设置左侧控件点击事件
        tbTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "你点击了返回", Toast.LENGTH_SHORT).show();
            }
        });
        //设置中间的标题
        tbTitle.setTitleText("landptf");
        //由于我们将右侧的控件默认为隐藏，在此显示出来
        tbTitle.setRightVisible(true);
        //设置右侧的控件的背景图，这里找了两张搜索的图片
        tbTitle.setRightBackImage(R.drawable.sousuo);
        tbTitle.setRightBackImageSeleted(R.drawable.sousuo);
        tbTitle.setOnClickLisenerR(new TitleBarM.OnClickListenerR() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你点击了搜索", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
