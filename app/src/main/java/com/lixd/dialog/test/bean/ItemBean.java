package com.lixd.dialog.test.bean;

import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;

import com.lixd.dialog.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/3.
 */

public class ItemBean {

    public static final int TYPE_OPEN_EXCEPTION = 1;  //打开异常
    public static final int TYPE_CLOSE_EXCEPTION = 2;  //关闭异常
    public static final int TYPE_NOT_CLOSE_EXCEPTION = 3; //没有关闭异常

    //类型
    public int type;

    //资源图片
    public int resIcon;

    //标题
    public String title;

    public ItemBean(@IntRange(from = TYPE_OPEN_EXCEPTION, to = TYPE_NOT_CLOSE_EXCEPTION) int type, @DrawableRes int resIcon, String title) {
        this.type = type;
        this.resIcon = resIcon;
        this.title = title;
    }

    public static List<ItemBean> createItemBeans() {
        final List<ItemBean> list = new ArrayList<>();
        list.add(new ItemBean(TYPE_OPEN_EXCEPTION, R.mipmap.ic_launcher, "打开异常"));
        list.add(new ItemBean(TYPE_CLOSE_EXCEPTION, R.mipmap.ic_launcher, "关闭异常"));
        list.add(new ItemBean(TYPE_NOT_CLOSE_EXCEPTION, R.mipmap.ic_launcher, "未关闭异常"));
        return list;
    }
}
