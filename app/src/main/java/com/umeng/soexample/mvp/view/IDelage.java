package com.umeng.soexample.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IDelage {
    //初始化方法
    void initData();
    //初始化布局
    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);
    //获取上下文
    void getContext(Context context);
    //获取view
    View view();
}
