package com.example.demo10_view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/*发现:标题是随着顶部图片的高度关系而改变颜色(淡入淡出)
* */

public class MainActivity extends AppCompatActivity implements ObservableScrollView.ScrollViewListener {
    private int mImageHeight;
    private ImageView mIvDetail;
    private ObservableScrollView mScrollView;
    private TextView mTvTitlebar;
    private RelativeLayout mLayoutTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件,初始化控件
        mIvDetail = (ImageView) findViewById(R.id.iv_detail);
        mScrollView = (ObservableScrollView) findViewById(R.id.scrollView);
        mTvTitlebar = (TextView) findViewById(R.id.tv_titlebar);
        mLayoutTitle = (RelativeLayout) findViewById(R.id.layout_title);
        //获取顶部的图片高度
        initListener();
        //设置scrollview的滚动监听,以便滑动时,最上面的标题显示,参数是scrollviewListener
        mScrollView.setScrollViewListener(this);


    }

    /*获取顶部的图片高度,设置scrollview的滚动监听时,要使用这个参数
    * */
    private void initListener() {
        //获取控件的视图观察者,以便通过视图观察者得到控件的宽高参数
        ViewTreeObserver viewTreeObserver = mIvDetail.getViewTreeObserver();
        //使用视图观察者设置监听,以便获取所观察控件的高度
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //回调监听后,第一件事情就是移除该监听,减少内存的消耗,最低版本API16中
                mIvDetail.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //得到控件的高度
                mImageHeight = mIvDetail.getHeight();
            }
        });

    }


    @Override
    public void OnScollChanged(ObservableScrollView scrollView, int l, int t, int oldl, int oldt) {
        //对Y轴进行判断,就两种形态:1.消失没有,2.随着滑动,颜色越来越深
        if (t <= 0) {
            //设置标题隐藏
            mTvTitlebar.setVisibility(View.GONE);
            //设置标题所在背景为透明
            mLayoutTitle.setBackgroundColor(Color.argb(0, 0, 0, 0));
        } else if (t > 0 && t < mImageHeight) {
            //让标题显示出来
            mTvTitlebar.setVisibility(View.VISIBLE);
            //获取Scrollview向下滑动,图片消失部分的比例
            float scale = (float) t / mImageHeight;
            //根据这个比例,让标题的颜色慢慢的由浅入深
            float alpha = 255 * scale;//算法
            //设置标题的内容及颜色
            mTvTitlebar.setText("1506D颜值担当是66666");
            mTvTitlebar.setTextColor(Color.argb((int) alpha, 0, 0, 0));
            //设置标题 颜色
            mLayoutTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

        }
    }
}
