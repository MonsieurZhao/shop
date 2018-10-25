package com.umeng.soexample.activity;


import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.presenter.MainActivityPresenter;

public class MainActivity extends BaseActivityPresenter<MainActivityPresenter>{

    @Override
    public Class<MainActivityPresenter> getClassPresenter() {
        return MainActivityPresenter.class;
    }
}

