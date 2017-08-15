package com.example.demo16__.Utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * data:2017/8/15
 * author:汉堡(Administrator)
 * function:工具类的方法,一般定义为static,方便调用,不用创建类对象直接就能调用
 * 思路:1.定义一个方法
 *      2.创建hanyupinyin对象
 *      3.把接收到的string转成字节数组
 *      4.定义一个StringBuilder
 *      5.遍历字节数组,进行判断
 *      6.判断字节为空格直接跳出循环;
 *        判断是否是汉字,不是直接添加到builder
 *        是就转换成拼音.toHanyuPinyinStringArray(c, format)[0].
 *      7.返回builder.toString
 */

public class PinYinUtil {
    public static String getPinyin(String string) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//不要拼音的音调
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//变大写
        char[] chars = string.toCharArray();
        //builder 比stringbuffter效率更高
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            //如果是空格,跳过当前循环
            if (Character.isWhitespace(c)) {
                continue;
            }
            //判断是否是汉字,如果不是汉字,就直接拼写
            if (c > -128 && c < 127) {
            }
            //如是汉字,就获取拼音
            else {
                try {
                    //获取某个字符对应的拼音,可以获取到多音字,(多音字数组,取第一个音就可以)
                    String s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
               sb.append(s);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
