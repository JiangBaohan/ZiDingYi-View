package com.example.demo08_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * data:2017/8/7
 * author:汉堡(Administrator)
 * function:自绘式自定义控件,牢记的三个方法
 * 1.onMeasure(int,int)测量:调用该方法来检查view组件及它包含的所有子组件的大小
 * 2.onlayout(boolean,int,int,int,int)位置:当该组件要分配其子组件的位置,大小时,调用(用的较少)
 * 3.onDraw(canves)绘制:当该组件将要绘制他的内容时,回调该方法(用的较多,不要在这个方法做耗时操作,new 对象)
 * <p>
 * 思路:创建一个可以拖动的小球
 * 1.类继承view(从一个普通的类,就变成控件了)
 * 2.覆写必要的方法
 * 3.onMeasure和OnDrawer方法,写对应的逻辑
 */

public class Balls extends View {
    private int height;
    private int widt;
    private int X;
    private int Y;
    private boolean onBall;

    //这三个方法是让你做初始化的业务逻辑

    /**
     * 代码中使用自定义控件(new 对象),自动回调此方法
     *
     * @param context
     */
    public Balls(Context context) {
        this(context, null);
    }

    /**
     * 在xml布局中用此自定义控件,自动回调此方法
     *
     * @param context
     * @param attrs
     */
    public Balls(Context context, AttributeSet attrs) {
        this(context, null, 0);

    }

    /**
     * xml布局中使用此自定义控件,且带有样式,自动回调
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public Balls(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiview();
    }

    //设置圆半径的距离
    int radius = 50;

    /**
     * 自定义控件初始化的
     */
    private void intiview() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取当前控件的宽和高
        height = this.getHeight();
        widt = this.getWidth();
        //获取屏幕的正心点
        X = height / 2;
        Y = widt / 2;

    }

    /*
    * 进行绘制
    * */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        //画圆,根据需要的参数,创建参数(ctrl+Q看参数)
        canvas.drawCircle(X, Y, radius, paint);

    }

//触摸事件,完成小球的移动

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*switch (event.getAction()){
            //进行判断,用户收是否再圆上
            case MotionEvent.ACTION_DOWN:
               //这个是以自定义 左上角为中心点 event.getRawX(),event.getRawY()
               //以屏幕左上角为中心点
                int downx= (int) event.getX();
                int downy= (int) event.getY();
                onBall = isOnBall(downx, downy);
                Toast.makeText(getContext(), "用户的手点击到圆了吗?"+ onBall, Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE:
                //进行判断,用户收是否再圆上,如是,让代码时时移动
                if (onBall) {
                    X = (int) event.getX();
                    Y = (int) event.getY();
                    //时时回调ondrawer方法
                    postInvalidate();
                }
                break;*/
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
             int   X1 = (int) event.getX();
             int   Y1 = (int) event.getY();
                if (X1  < (X + radius) && X1 > (X- radius) && Y1 < (Y + radius) && Y1 > (Y - radius)) {
                    X  = (int) event.getX();
                    Y = (int) event.getY();
                    postInvalidate();
                } else {
                    Log.d("0", "在外面");
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }

    private boolean isOnBall(int downx, int downy) {
        //勾股定理,得到按下的半径
        double sqrt = Math.sqrt((downx - X) * (downx - X) + (downy - Y) * (downy - Y));
        //对应圆的半径和按下的半径进行判断,看用户的收是否点再圆上
        if (sqrt <= radius) {
            return true;
        }
        return false;
    }
}
