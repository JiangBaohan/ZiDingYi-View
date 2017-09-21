package com.example.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * date:2017/9/22
 * author:humberger
 * function:
 */

public class BigView extends View {

    private int imageWidth;
    private int imageHeight;
    private BitmapRegionDecoder decoder;
    private Rect currentRect;
    private int downX;
    private int downY;
    private int measuredWidth;
    private int measuredHeight;

    public BigView(Context context) {
        super(context);
    }

    public BigView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BigView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //创建设置图片参数对象


    private static BitmapFactory.Options ops = new BitmapFactory.Options();

    static {
//图片的颜色质量的参考网址:http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1023/1825.html
        //设置图片的颜色质量,使其不要太占内存
        ops.inPreferredConfig = Bitmap.Config.RGB_565;
    }


    /**
     * @param inputStream 代表着超大图片文件
     */
    public void setInput(InputStream inputStream) {
//通过图片参数对象设置不会把这张图片加载到内存中,避免OOM的问题
        ops.inJustDecodeBounds = true;
//只是单纯的将流和图片的参数设置对象进行关联 参数:1.流 2.为null 3.对图片的设置对象
        BitmapFactory.decodeStream(inputStream, null, ops);
//通过图片的参数设置对象获取到图片的宽和高
        imageWidth = ops.outWidth;
        imageHeight = ops.outHeight;
        try {
//BitmapRegionDecoder用于解码图像,把图片字节流其中一部分以矩形区域展示并换成Bitmap对象 参数:1.流资源 2.false会对加载的图片进行复制
            decoder = BitmapRegionDecoder.newInstance(inputStream, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//获取手机屏幕的宽和高
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
//是控件加载大图片一部分,显示的是图片中心位置,所以设置左右和上下底部的点
        int top = imageHeight / 2 - measuredHeight / 2;
        int bottom = imageHeight / 2 + measuredHeight / 2;
        int left = imageWidth / 2 - measuredWidth / 2;
        int right = imageWidth / 2 + measuredWidth / 2;
//创建一个矩形,设置其最左边的点,最上面的点,最右边的点,最下面的点
        currentRect = new Rect(left, top, right, bottom);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//创建一个指定区域的矩形Bitmap。 参数1.矩阵(就是手机显示图片一部分的大小,以屏幕的宽和高为基准) 2.图片参数对象
        Bitmap bitmap = decoder.decodeRegion(currentRect, ops);
//重新画一张图片图片 ,参数1:Bitmap 2,3设置为0 4画笔设置为null
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
//用户按下的回调
            case MotionEvent.ACTION_DOWN:
//获取当前的x轴和Y轴(也就是起点)
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
//用户移动的回调
            case MotionEvent.ACTION_MOVE:
//获取移动后的x轴和y轴(也就是终点)
                int moveX = (int) event.getX();
                int moveY = (int) event.getY();

//起点-终点,得到距离(之所以是起点减,是因为终点是移动值不固定)
                int diffX = downX - moveX;
                int diffY = downY - moveY;
                System.out.println("按下的x:" + downX + " 移动后的x:" + moveX);
//把终点变回起点
                downX = moveX;
                downY = moveY;
//加载区域根据距离进行移动
                refreshRect(diffX, diffY);
                break;
        }
//请求重绘View,也就是再次调用Ondraw方法.
        invalidate();
//返回值必须是true,代码才起效果.
        return true;
    }
    private void refreshRect(int diffX, int diffY) {
//矩形改变值的方法
        currentRect.offset(diffX, diffY);
//限制用户移动范围,不能让用户一直拉,直到超出图片的范围.
        //不让用户超出左边界,左边界是0
        if (currentRect.left <= 0) {
//当到了左边界,把最左边的参数设置为0
            currentRect.left = 0;
//最右边的参数设置为屏幕的宽即可
            currentRect.right = measuredWidth;
        }
//不让用户超出右边界,右边界图片的宽.
        else if (currentRect.right >= imageWidth) {
//当到了右边界,把最左边的参数设置图片宽-屏幕宽
            currentRect.left = imageWidth - measuredWidth;
//最右边的参数设置为图片的宽即可
            currentRect.right = imageWidth;
        }
//不让用户超出顶部,顶部边界是0.
        if (currentRect.top <= 0) {
//当到了顶部,把最上边的参数设置为0
            currentRect.top = 0;
//最下面的参数为屏幕的高
            currentRect.bottom = measuredHeight;
        }
//不让用户超出底部,顶部边界是图片的高.
        else if (currentRect.bottom >= imageHeight) {
//当到了顶部,用图片的高-屏幕的高
            currentRect.top = imageHeight - measuredHeight;
//最下面的参数为图片的高
            currentRect.bottom = imageHeight;
        }
    }
}
