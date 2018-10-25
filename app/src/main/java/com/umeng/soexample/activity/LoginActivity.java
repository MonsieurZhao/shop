package com.umeng.soexample.activity;


import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.presenter.LoginActivityPresenter;
import com.umeng.soexample.presenter.MainActivityPresenter;

public class LoginActivity extends BaseActivityPresenter<LoginActivityPresenter>{

    @Override
    public Class<LoginActivityPresenter> getClassPresenter() {
        return LoginActivityPresenter.class;
    }
}

