package com.umeng.soexample.activity;


import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.presenter.MainActivityPresenter;
import com.umeng.soexample.presenter.ShowActivityPresenter;

public class ShowActivity extends BaseActivityPresenter<ShowActivityPresenter>{

    @Override
    public Class<ShowActivityPresenter> getClassPresenter() {
        return ShowActivityPresenter.class;
    }
}

