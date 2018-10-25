package com.umeng.soexample.activity;


import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.presenter.InsertActivityPresenter;
import com.umeng.soexample.presenter.LoginActivityPresenter;

public class InsertActivity extends BaseActivityPresenter<InsertActivityPresenter>{

    @Override
    public Class<InsertActivityPresenter> getClassPresenter() {
        return InsertActivityPresenter.class;
    }
}

