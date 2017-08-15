package com.example.demo16__;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demo16__.Bean.Person;

import java.util.ArrayList;

/**
 * data:2017/8/15
 * author:汉堡(Administrator)
 * function:判断当前首字母和上一个条目的首字母是否一致,
 * 不一致时就显示全部界面;
 * 一致时就隐藏第一个界面;
 * 用多条的思路实现
 */
public class MyAdapter extends BaseAdapter {
    private ArrayList<Person> persons=new ArrayList<>();
    private final  Context context;

    public MyAdapter(ArrayList<Person> persons, Context context) {
        this.persons = persons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup viewGroup) {
        View view;
        if (converView == null) {
            view = View.inflate(context, R.layout.item_person, null);
        } else {
            view = converView;
        }
        TextView tv_index= (TextView) view.findViewById(R.id.tv_index);
        TextView tv_name= (TextView) view.findViewById(R.id.tv_name);
        Person person = persons.get(position);
        //当前首字母
        String currentStr = person.getPinyin().charAt(0) + "";
        String indexStr = null;
        //如果是第一个名字,就直接显示
        if (position == 0) {
            indexStr = currentStr;
        } else {
            //判断当前首字母和上一个条目的首字母是否一致,不一致时候显示完整的item界面
            String lastStr = persons.get(position - 1).getPinyin().charAt(0) + "";
            //判断两个参数是否一直,不一致就之行赋值
            if (!TextUtils.equals(lastStr, currentStr)) {
                //不一致的时候赋值indexStr
                indexStr = currentStr;
            }
        }
        tv_index.setVisibility(indexStr != null ? View.VISIBLE : View.GONE);
        tv_index.setText(currentStr);
        tv_name.setText(person.getName());
        return view;
    }
}
