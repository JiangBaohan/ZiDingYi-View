package com.example.demo10_view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**扩展式自定义view,再scrollview的基础上添加新的功能
 * 1.类继承基础控件,时限内三个复写方法
 * 2.自定义一个srollview滑动监听的接口
 * 3.覆写scrollview自带的一个滑动监听
 * 4.提供方法,让外界可以设置scrollview的监听对象
 * 5.使用observablescroollview自定义控件
 *
 * 思路:
 * 1.看效果,判断是哪种类型的自定义控件
 * 2.如果是继承式自定义控件,那么我们就要判断这个效果是基于那种基础控件之上
 */
public class ObservableScrollView extends ScrollView{

    public ObservableScrollView(Context context) {
        this(context,null);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

   public  interface  ScrollViewListener{
       //ScrollView自带一个滑动监听,对其覆写
       //参数:1.监听view的对象,2.3 新的坐标  4.5 旧的坐标
       void OnScollChanged(ObservableScrollView scrollView,int l,int t,int oldl,int oldt);
   }
    //自己的监听对象
    private  ScrollViewListener mScrollViewListener;
    //提供方法,让外界可以设置ScrollViewListener的监听对象
    public void setScrollViewListener(ScrollViewListener scrollViewListener){
        mScrollViewListener=scrollViewListener;
    }
    //提供一个腹泻的滑动监听方法,让外界可以设置scrollview的监听对象
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mScrollViewListener!=null){
            mScrollViewListener.OnScollChanged(this,l,t,oldl,oldt);
        }
    }
}
