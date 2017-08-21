package com.example.demo19_photoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 1.复制jar包,添加奶瓶
 * 2.布局XML文件,添加photoview控件,src加载一张图片,就已经实现了双击放大缩小的功能
 * 3.photoview是指点击事件,实现单机退出Activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         PhotoView pv= (PhotoView) findViewById(R.id.pv);
        pv.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                finish();
            }
        });
    }
}
