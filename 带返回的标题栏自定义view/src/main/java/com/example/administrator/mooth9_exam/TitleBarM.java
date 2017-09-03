package com.example.administrator.mooth9_exam;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * data:2017/9/1
 * author:汉堡(Administrator)
 * function:
 */

public class TitleBarM  extends RelativeLayout {
    private Context context;
    //定义三个控件，
    private Button btnLeft;
    private TextView tvTitle;
    private Button btnRight;
    //定义左侧控件的接口
    private OnClickListenerL onClickListenerL = null;
    //定义右侧控件的接口
    private OnClickListenerR onClickListenerR = null;

    public interface OnClickListenerL{
        //定义一个方法
        public void onClick(View v);
    }

    public interface OnClickListenerR{
        //定义一个方法
        public void onClick(View v);
    }

    /**
     * 为左侧控件绑定事件
     * @param onClickListenerL
     */
    public void setOnClickLisenerL(OnClickListenerL onClickListenerL) {
        this.onClickListenerL = onClickListenerL;
    }

    /**
     * 为右侧控件绑定事件
     * @param onClickListenerR
     */
    public void setOnClickLisenerR(OnClickListenerR onClickListenerR) {
        this.onClickListenerR = onClickListenerR;
    }

    public TitleBarM(Context context) {
        this(context, null);
    }

    public TitleBarM(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleBarM(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        //设置RelativeLayout的布局
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        //设置默认的背景色
        setBackgroundColor(Color.parseColor("#B674D2"));
        init();
    }

    private void init() {
        //初始化左侧ButtonM
        btnLeft = new Button(context);
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        //垂直居中
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        //设置距离左侧10dp
        lp.leftMargin = dp2px(context, 10);
        btnLeft.setLayoutParams(lp);
        btnLeft.setTextSize(16);//设置字体大小,默认为16
        btnLeft.setTextColor(Color.WHITE);//默认字体颜色为白色

       // btnLeft.setTextColorSelected("#909090");//按下后的字体颜色
        //定义其点击事件
        btnLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLeft.setTextColor(Color.BLACK);
                if (onClickListenerL != null) {
                    onClickListenerL.onClick(v);
                }
            }
        });
        //初始化中间标题控件
        tvTitle = new TextView(context);
        lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        //使其处于父控件的中间位置，也有一些APP的标题偏左，可根据项目需要自行调整，也可动态设置
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        tvTitle.setLayoutParams(lp);
        //设置标题文字颜色
        tvTitle.setTextColor(Color.WHITE);
        //设置标题文字大小
        tvTitle.setTextSize(18);//默认为18
        //初始化右侧ButtonM
        btnRight = new Button(context);
        lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.rightMargin = dp2px(context, 10);
        //垂直居中
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        //居于父控件的右侧
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        btnRight.setLayoutParams(lp);
        btnRight.setTextSize(16);//默认有16
        btnRight.setVisibility(View.GONE); //默认隐藏右侧控件
        btnRight.setTextColor(Color.WHITE);
        //btnRight.setTextColorSelected("#909090");
        btnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRight.setTextColor(Color.BLACK);
                if (onClickListenerR != null) {
                    onClickListenerR.onClick(v);
                }
            }
        });

        //分别将三个控件加入到容器中
        addView(btnLeft);
        addView(tvTitle);
        addView(btnRight);
    }

    /**
     * 设置标题栏的背景色 ，String
     * @param backColors
     */
    public void setBackColor(String backColors) {
        setBackgroundColor(Color.parseColor(backColors));
    }

    /**
     * 设置标题栏的背景色 ，int
     * @param backColori
     */
    public void setBackColor(int backColori) {
        setBackgroundColor(backColori);
    }

    /**
     * 设置左侧控件显示的文字
     * @param leftText
     */
    public void setLeftText(String leftText) {
        btnLeft.setText(leftText);
    }

    /**
     * 设置左侧控件的背景图
     * @param leftBackImage
     */
   /* public void setLeftBackImage(int leftBackImage) {
        if (leftBackImage != 0) {
            btnLeft.setBackGroundImage(leftBackImage);
        }
    }*/

    /**
     * 设置左侧控件选中的背景图
     * @param leftBackImageSeleted
     */
   /* public void setLeftBackImageSeleted(int leftBackImageSeleted) {
        if (leftBackImageSeleted != 0) {
            btnLeft.setBackGroundImageSeleted(leftBackImageSeleted);
        }
    }*/

    /**
     * 设置左侧控件显示属性，默认为显示
     * @param leftVisible
     */
    public void setLeftVisible(boolean leftVisible) {
        btnLeft.setVisibility(leftVisible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置左侧控件显示的字体大小
     * @param leftTextSize
     */
    public void setLeftTextSize(float leftTextSize) {
        btnLeft.setTextSize(leftTextSize);
    }

    /**
     * 设置中间控件显示属性，默认为显示
     *
     */
    public void setTitleVisible(boolean titleVisible) {
        tvTitle.setVisibility(titleVisible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置中间控件的文字
     * @param titleText
     */
    public void setTitleText(String titleText) {
        tvTitle.setText(titleText);
    }

    /**
     * 设置中间控件的文字的大小
     * @param titleTextSize
     */
    public void setTitleTextSize(float titleTextSize) {
        tvTitle.setTextSize(titleTextSize);
    }

    /**
     * 设置右侧控件显示的文字
     */
    public void setRightText(String rightText) {
        btnRight.setText(rightText);
    }

    /**
     * 设置右侧控件的背景图
     */
    public void setRightBackImage(int rightBackImage) {
        if (rightBackImage != 0) {
            btnRight.setBackgroundResource(rightBackImage);
        }
    }

    /**
     * 设置右侧控件选中的背景图
     */
    public void setRightBackImageSeleted(int rightBackImageSeleted) {
        if (rightBackImageSeleted != 0) {
            btnRight.setBackgroundResource(rightBackImageSeleted);
        }
    }

    /**
     * 设置右侧控件显示的字体大小
     */
    public void setRightTextSize(float rightTextSize) {
        btnRight.setTextSize(rightTextSize);
    }

    /**
     * 设置右侧控件显示属性，默认为隐藏
     */
    public void setRightVisible(boolean rightVisible) {
        btnRight.setVisibility(rightVisible ? View.VISIBLE : View.GONE);
    }

    //定义一个私有方法dp2px
    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
