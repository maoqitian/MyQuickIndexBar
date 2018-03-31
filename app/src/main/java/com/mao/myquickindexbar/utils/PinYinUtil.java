package com.mao.myquickindexbar.utils;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by 毛麒添 on 2017/3/1 0001.
 * 汉字转换成英文字母工具类
 */

public class PinYinUtil {
    /**
     * 汉字转换成英文字母工具类
     * @param chinese 需要转换的汉字
     * @return 汉字的大写拼音字母
     */
    public static String getPinYin(String chinese){

        String pinYin="";

        //设置转换的格式
        HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//大写字母
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//没有声调

        //如果传入的汉字为空，返回null
        if(TextUtils.isEmpty(chinese))return null;
        //转化拼音的类库只能转化单个汉字，所以需要将传入的汉字转换成字节数组
        char[] charArray = chinese.toCharArray();
        for (int i = 0; i <charArray.length ; i++) {
            //过滤空格
            if(Character.isWhitespace(charArray[i]))continue;
            //一个汉字占两个字节，一字节8位，范围-128~~127
            if(charArray[i]>127){//可能是汉字
                try {
                    String[] pinYinArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], format);
                    if(pinYinArray!=null){//转换成功
                        //返回数组是因为有可能该汉字是多音字，但是不管是否多音字都只取第一个
                        pinYin+=pinYinArray[0];
                    }else {
                        //转化失败，不做操作，忽略
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                    //转化失败则将其忽略
                }

            }else {//不是汉字，不需要转换将其和返回值拼接在一起
               pinYin+=charArray[i];
            }
        }
        return pinYin;
    }
}
