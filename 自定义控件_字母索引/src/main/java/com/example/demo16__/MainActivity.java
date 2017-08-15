package com.example.demo16__;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.example.demo16__.Bean.Person;
import com.example.demo16__.UI.QuickIndexBar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 侧拉索引:音乐APP,即时通讯,电商选择城市,短信验证选择城市都有这个类型自定义控件.
 * 实现步骤:
 * 1.绘制a-z的字母列表(自绘式自定义控件)
 * 2.响应触摸事件
 * 3.提供监听回调
 * 4.获取汉字的拼音首字母:(pinying4J通过汉字得到他的拼音,只能一个字符一个字符的转换成拼音)
 * 5.根据拼音排序
 * 6.根据首字母分组
 * 7.把监听回调和listview 结合起来
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消activityBar(toolBar)
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
 QuickIndexBar bar= (QuickIndexBar) findViewById(R.id.bar);
       /* bar.setLetterChangeListener(new QuickIndexBar.OnLetterChangeListener() {
            @Override
            public void OnLetterChange(String letter) {
                toastUtil.showToast(MainActivity.this, letter);
            }
        });*/
        //D.view层
        listView = (ListView) findViewById(R.id.lv);

        //model层,创建集合
        persons = new ArrayList<>();
        //填充并排序
        fillAndSortData(persons);
        listView.setAdapter(new MyAdapter(persons,this));
        //根据用户按住的字符,自动跳到对应的listview条目上
        bar.setLetterChangeListener(new QuickIndexBar.OnLetterChangeListener() {
            @Override
            public void OnLetterChange(String letter) {
                for (int i = 0; i <persons.size() ; i++) {
                    String l=persons.get(i).getPinyin().charAt(0)+"";
                    if (TextUtils.equals(letter,l)){
                        //找到第一个首字母是letter条目
                        listView.setSelection(i);
                        break;
                    }
                }
            }
        });
    }
    /**
     * 填充数据,并进行排序
     */
    private void fillAndSortData(ArrayList<Person> persons) {
       //填充
        for (int i = 0; i <Cheese.NAMES.length ; i++) {
            String name=Cheese.NAMES[i];
            persons.add(new Person(name));
        }
        //排序
        Collections.sort(persons);
    }
}
