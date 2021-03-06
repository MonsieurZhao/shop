package com.umeng.soexample.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AppDelage implements IDelage {

    private View rootView;

    @Override
    public void initData() {

    }
    private SparseArray<View> views = new SparseArray<>();
    public <T extends View> T get(int id){
        T view = (T) views.get(id);
        if(view==null){
            view = rootView.findViewById(id);
            views.put(id,view);
        }
        return  view;
    }
    public void setOnclick(View.OnClickListener listener,int...ids){
        if(ids==null){
            return;
        }
        for(int id:ids){
            get(id).setOnClickListener(listener);
        }
    };
    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayoutId(), viewGroup, false);
    }

    @Override
    public View view() {
        return rootView;
    }

    public abstract int getLayoutId();

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
    public void destry(){
        rootView=null;
    }
}
