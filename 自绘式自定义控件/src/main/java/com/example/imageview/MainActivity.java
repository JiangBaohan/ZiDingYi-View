package com.example.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * 1.从github下载WorldMap-master开源自定义控件
 * (网址:https://github.com/johnnylambada/WorldMap)

 2.找到library文件,从src代码中找到这三个类
 (ImageSurfaceView,InputStreamScene,Scene)拷贝到我们的项目中

 3.在XML布局文件中使用开源的自定义控件.
 <com.example.siyan.imagedemo.custom.ImageSurfaceView
 android:id="@+id/img"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content" />
 
 4.java代码中初始化控件,拿到图片资源,使用开源的自定义控件进行图片加载
 ImageSurfaceView img = (ImageSurfaceView) findViewById(R.id.img);
 InputStream inputStream = getAssets().open("world.jpg");
 img.setInputStream(inputStream);

 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//初始化控件
        BigView bigView = (BigView) findViewById(R.id.img);
//从资产目录把图片转换成字节流
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("world.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
//使用自定义控件,调用方法传入图片的字节流,进行图片的展示
        bigView.setInput(inputStream);
    }
}
