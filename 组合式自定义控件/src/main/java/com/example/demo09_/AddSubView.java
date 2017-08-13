package com.example.demo09_;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * data:2017/8/8
 * author:汉堡(Administrator)
 * function:组合式自定义控件
 * 1.类继承viewgroup下的任意自定义控件
 * 2.覆写他的构造方法
 */
public class AddSubView extends LinearLayout implements View.OnClickListener {
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView tv_value;
    private int mValue;
    //再代码中New对象时使用
    public AddSubView(Context context) {
        this(context, null);
    }
    //2.在xml中使用是回调
    public AddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    //3.在XML中使用,切使用Style风格中
    public AddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context) {
        //把一个布局文件实例化,并且加载到AddSubView类中
        View view = View.inflate(context, R.layout.add_sub_view, this);
        //初始化控件
        iv_sub = (ImageView) view.findViewById(R.id.iv_sub);
        iv_add = (ImageView) view.findViewById(R.id.iv_add);
        tv_value = (TextView) view.findViewById(R.id.tv_value);
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        //获取value值
        getValue();
    }
    //imageview按钮的点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                addNumber();
                break;
            case R.id.iv_sub:
                subNumber();
                break;
            default:
                break;
        }
    }
    //当前数量值,默认为1,设置对此值获取
    private int value = 1;
    private int MinNumber = 1;
    private int MaxNumber = 5;
    public int getMinNumber() {
        return MinNumber;
    }
    public void setMinNumber(int minNumber) {
        MinNumber = minNumber;
    }
    public int getMaxNumber() {
        return MaxNumber;
    }
    public void setMaxNumber(int maxNumber) {
        MaxNumber = maxNumber;
    }
    //获取到的values是从ui拿到的
    public int getValue() {
        String trim = tv_value.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            //因为是字符串,所以要转化成int类型的
            value = Integer.valueOf(trim);
        }
        return value;
    }
    public void setValue(int value) {
        mValue = value;
        tv_value.setText(value + "");
    }
    private void subNumber() {
        if (value > MinNumber) {
            value--;
        }
        setValue(value);
        if(onNumberChangerListener !=null){
            onNumberChangerListener.OnNumberChanger(value);
        }
    }
    private void addNumber() {
        if (value < MaxNumber) {
            value++;
        }
        setValue(value);
        if(onNumberChangerListener !=null){
            onNumberChangerListener.OnNumberChanger(value);
        }
    }
    /*定义接口,及所要调用的接口方法,当商品数量发生变化时,回调给接口
    * */
    public interface OnNumberChangerListener{
        void OnNumberChanger(int value);
    }
    //定义接口
    private OnNumberChangerListener onNumberChangerListener;
//设置方法接收外界传来的接口对象方法
    public  void  setOnNumberChangerListener(OnNumberChangerListener onNumberChangerListener1){
        onNumberChangerListener=onNumberChangerListener1;
    }
}
