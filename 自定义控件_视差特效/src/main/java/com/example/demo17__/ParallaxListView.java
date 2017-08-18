package com.example.demo17__;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * data:2017/8/16
 * author:汉堡(Administrator)
 * function:继承式控件
 * 1.继承listview,覆写构造方法
 * 2.覆写overScrollBy方法,重点关注deltaY,isTouchEvent方法
 * 3.暴露一个方法,去得到外界的ImageView,并测量ImageView控件的高度
 * 4.覆写OnToucheEvent方法
 */

public class ParallaxListView extends ListView {
    private ImageView iv_header;
    private int drawableHeight;
    private int orignalHeight;

    public ParallaxListView(Context context) {
        this(context, null);
    }

    public ParallaxListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setIv_header(ImageView iv_header) {
        this.iv_header = iv_header;
        //B.获取图片的原始高度
        drawableHeight = iv_header.getDrawable().getIntrinsicHeight();
        //C.获取ImageView控件的原始高度,以便回弹时,回弹到原始高度
        orignalHeight = iv_header.getHeight();
    }

    /**
     * 重要方法********************************************
     * A.滑动到listview两端时,才会被调用
     *
     * @param deltaY:竖直方向滑动的瞬时变化量,顶部下拉为负,顶部上拉为正
     * @param isTouchEvent:是否是用户触摸拉动,true为用户手指拉动,false是惯性
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //A.通过LOG来验证参数的作用
        Log.d("tag", "deltaY" + deltaY);
        Log.d("tag", "isTouchEvent" + isTouchEvent);
//A.顶部下拉,用户触摸的操作才执行视差效果
        if (deltaY < 0 && isTouchEvent) {
            //A.deltaY是负值,所以要更改绝对值,累计给iv_header高度
            int newHeight = iv_header.getHeight() + Math.abs(deltaY);
            //B.避免图片的无限放大,使图片最大不能超过图片本身的高度
            if (newHeight <= drawableHeight) {
                //把新的高度值给控件,给摆弄控件的高度
                iv_header.getLayoutParams().height = newHeight;
                iv_header.requestLayout();
            }

        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    //C.覆写触摸事件,让滑动图片重新恢复原有的样子
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                //把当前的头布局的高度恢复初始高度
                int currentHeight = iv_header.getHeight();
                //属性动画,改变高度的值,把我们当前头布局的高度,改为原始时的高度
                final ValueAnimator animator = ValueAnimator.ofInt(currentHeight, orignalHeight);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float fraction = animator.getAnimatedFraction();
                //获取中间的值,并赋给控件的新高度,可以使控件平稳回弹的效果
                        Integer animatedValue = (Integer) animator.getAnimatedValue();
                        //让新高度值生效
                        iv_header.getLayoutParams().height = animatedValue;
                        iv_header.requestLayout();
                    }
                });
                //动画的回弹效果,值越大回弹越厉害
                animator.setInterpolator(new OvershootInterpolator(2));
                //设置动画的执行时间,单位毫秒
                animator.setDuration(1000);
                animator.start();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
