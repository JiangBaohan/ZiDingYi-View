package com.example.demo09_;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddSubView addSubView = (AddSubView) findViewById(R.id.addsubview);
        addSubView.setMaxNumber(10);
        addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void OnNumberChanger(int value) {
                Toast.makeText(MainActivity.this, "变化的数量值" + value, Toast.LENGTH_SHORT).show();
            }
        });
       /*//调用接口展开式写法
       demo d = new demo();
        addSubView.setOnNumberChangerListener(d);*/

    }

    /*class demo implements AddSubView.OnNumberChangerListener {
        @Override
        public void OnNumberChanger(int value) {
            Toast.makeText(MainActivity.this, "变化的数量值" + value, Toast.LENGTH_SHORT).show();
        } }*/

}
