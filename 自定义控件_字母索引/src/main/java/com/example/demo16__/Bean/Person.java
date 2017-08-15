package com.example.demo16__.Bean;

import com.example.demo16__.Utils.PinYinUtil;

/**
 * data:2017/8/15
 * author:汉堡(Administrator)
 * function:
 */

public class Person implements Comparable<Person>{
    private  String name;
    private String pinyin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public Person(String name) {
        this.name = name;
        //使用工具类,根据汉字拿到拼音
        this.pinyin= PinYinUtil.getPinyin(name);
    }

    @Override
    public int compareTo(Person person) {
        return this.pinyin.compareTo(person.pinyin);
    }
}
