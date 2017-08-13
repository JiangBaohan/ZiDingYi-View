package com.example.demo08_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*自定义控件有三种
自绘控件(完全自定义控件),继承view
组合式自定义控件,继承viewgroup
继承式自定义控件:如button
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
