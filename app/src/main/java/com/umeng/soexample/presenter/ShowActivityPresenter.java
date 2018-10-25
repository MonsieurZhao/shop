package com.umeng.soexample.presenter;

import android.content.Context;

import com.umeng.soexample.R;
import com.umeng.soexample.mvp.view.AppDelage;

public class ShowActivityPresenter extends AppDelage {
    @Override
    public int getLayoutId() {
        return R.layout.activity_show;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }
    @Override
    public void initData() {
        super.initData();
    }
}
