package com.example.demo16__.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * data:2017/8/14
 * author:汉堡(Administrator)
 * function:快速索引栏实现思路
 * 1.继承view,覆写构造方法,初始化画笔
 * 2.再onDrawer方法里绘制字符
 * 3.再onMeasure方法里测量高度
 * 4.再onTouchEvent事件知道用户具体按住了哪个字母
 * 5.定义抽象方法,实现监听回调.
 */

public class QuickIndexBar extends View {

    private Paint paint;
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };
    private float mWidth;
    private float cellHight;
    private float eventY;
    private int currentIndex;


    //三个构造方法
    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔
        initPaint();
    }

    private void initPaint() {
        //创建抗锯齿的画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 画笔加粗
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        //颜色
        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
    }

    //A.完成侧拉索引,字母的绘制从左下角开始,所以要规定字母绘制
//在哪里,就要定义好他在左下角的坐标
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //遍历26个字母,进行坐标计算,进行绘制
        for (int i = 0; i < LETTERS.length; i++) {
            //从数组,根据I取出字母
            String letter = LETTERS[i];
            //计算x坐标
            float X = mWidth * 0.5f - paint.measureText(letter) * 0.5f;
            //计算Y坐标
            float Y = cellHight * 0.5f + paint.measureText(letter) * 0.5f + i * cellHight;

            canvas.drawText(letter, X, Y, paint);
        }

    }

    //A.完成侧拉索引的测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取控件的宽高
        float mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        //获取单元格的高度,由自定义控件总高度,除以所有字母所占用的高度
        //*1.0,为了精确,避免四舍五入,我们把数转换为小数
        cellHight = mHeight * 1.0f / LETTERS.length;
    }

    /**
     * 重写onTouchEvent事件,返回true方起效.
     */
    //记录用户上一次按下的位置,以便进行判断这一次所按住的位置是否还是上一次的位置
    //如果是的话,不做任何处理
    private int lastIndex = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //计算用户按到哪个字母的范围是y轴
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //获取被点击到的字母索引
                eventY = event.getY();
                //按到第几个单元格
                currentIndex = (int) (eventY / cellHight);
                //为了防止一个字母按下后不停的重复调用,将进行判断
                //判断是否按住的还是上一个字母,如果是的话就不做任何处理,
                //提高程序的系能
                if (currentIndex != lastIndex) {
                    //为了防止角标越界,我们只在用户按住的Y轴值大于0,小于数组长度才执行;
                    if (currentIndex >= 0 && currentIndex < LETTERS.length) {
                       // String letter = LETTERS[currentIndex];
                        // if (mLetterChangeListener!=null){
                        mLetterChangeListener.OnLetterChange(LETTERS[currentIndex]);
                        // }
                        // toastUtil.showToast(getContext(), letter);
                    }
                    lastIndex = currentIndex;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 写接口的步骤:1.写个接口,里面写个抽象方法,
     * 2.定义接口对象
     * 3.暴露接口的方法
     * 4.在需要传的地方用接口对象调用接口里的抽象方法
     * 5.再mainActivity调用接口
     */
    //C.定义接口
    public interface OnLetterChangeListener {
        void OnLetterChange(String letter);
    }

    // (字母变化监听)C.定义接口对象
    private OnLetterChangeListener mLetterChangeListener;

    public OnLetterChangeListener getLetterChangeListener() {
        return mLetterChangeListener;
    }

    //C.暴露接口的方法
    public void setLetterChangeListener(OnLetterChangeListener mLetterChangeListener) {
        this.mLetterChangeListener = mLetterChangeListener;
    }
}
