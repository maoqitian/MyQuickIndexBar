package com.mao.myquickindexbar.bean;

import com.mao.myquickindexbar.utils.PinYinUtil;

/**
 * Created by 毛麒添 on 2017/2/27 0027.
 * 联系人实体对象
 */

public class Contacts implements Comparable<Contacts>{
    private String name;
    private String pinYin;

    public Contacts(String name){
        super();
        this.name=name;
        setPinYin(PinYinUtil.getPinYin(name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }
    //集合比较
    @Override
    public int compareTo(Contacts another) {
        /*String pinYin = PinYinUtil.getPinYin(getName());
        String anotherPinYin = PinYinUtil.getPinYin(o.getName());
        return pinYin.compareTo(anotherPinYin);*/
        return getPinYin().compareTo(another.getPinYin());
    }
}
