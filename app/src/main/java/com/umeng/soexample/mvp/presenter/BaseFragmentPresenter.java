package com.umeng.soexample.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.soexample.mvp.view.AppDelage;

public abstract class BaseFragmentPresenter<T extends AppDelage> extends Fragment{
    private T daleagt;

    public abstract Class<T> getClassPresenter();
    public BaseFragmentPresenter(){
        try {
            daleagt = getClassPresenter().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        daleagt.create(getLayoutInflater(),null,savedInstanceState);
        daleagt.getContext(getActivity());
        daleagt.initData();
        return daleagt.view();
    }
}
