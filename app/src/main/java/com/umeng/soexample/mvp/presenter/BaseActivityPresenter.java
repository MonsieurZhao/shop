package com.umeng.soexample.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.umeng.soexample.mvp.view.AppDelage;

public abstract class BaseActivityPresenter<T extends AppDelage> extends AppCompatActivity {

    private T daleagt;

    public abstract Class<T> getClassPresenter();
    public BaseActivityPresenter(){
        try {
            daleagt = getClassPresenter().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daleagt.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(daleagt.view());
        daleagt.getContext(this);
        daleagt.initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        daleagt.destry();
        daleagt=null;
    }
}
