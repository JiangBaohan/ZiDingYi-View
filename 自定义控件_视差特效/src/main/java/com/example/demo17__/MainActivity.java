package com.example.demo17__;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * 视差特性思路:例如qq头像设置的时候下拉图片自动反弹
 * (listview如果第一个条目不同可以再上面添加一个headerview)
 * 1.解析onTouche,Action_Down,Action_move,Activity_up,业务逻辑过于复杂
 * 2.重写ListView的ouverScrollBy方法,继承式自定义控件listview,根据用户下拉的距离,动态修改headerView的高度
 * a.拷贝文本资源到项目中,自定义控件继承listview
 * b.使用自定义控件,并往头部添加布局,设置适配器
 * c.使用视图树,把Imageview传给我们的自定义控件
 */
public class MainActivity extends AppCompatActivity {

    private ParallaxListView plv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //A.view
        plv = (ParallaxListView) findViewById(R.id.tvlist);

        //B.listview添加一个头布局,Listview的上拉加载,下拉刷新
        View headerView = View.inflate(this, R.layout.layout_header, null);
        plv.addHeaderView(headerView);
        final ImageView iv_header= (ImageView)headerView.findViewById(R.id.iv_header);
       //等view界面全部绘制完毕的时候,去得到已经绘制完控件的宽和高,查个方法,记笔记
        iv_header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//宽和高已经测量完毕

                plv.setIv_header(iv_header);
                //释放资源
                iv_header.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        //B.使用listview的ArrayAdapter添加文本的item
        plv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Cheese.NAMES));
    }
}
