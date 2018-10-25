package com.umeng.soexample.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.MyAdapterTab1;
import com.umeng.soexample.adapter.MyAdapterTab2;
import com.umeng.soexample.model.BeanTab;
import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.mvp.presenter.BaseFragmentPresenter;
import com.umeng.soexample.net.Http;
import com.umeng.soexample.net.HttpUtiles;
import com.umeng.soexample.presenter.MainActivityPresenter;
import com.umeng.soexample.presenter.TabulateFragmentPresenter;

import java.util.List;

public class TabulateFragment extends Fragment{

    private RecyclerView recyclerView1,recyclerView2;
    private MyAdapterTab1 myAdapterTab1;
    private MyAdapterTab2 myAdapterTab2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_tabulate,null);
        recyclerView1 = view.findViewById(R.id.rv_recyler1_tab);
        recyclerView2 = view.findViewById(R.id.rv_recyler2_tab);
        doHttp();
        myAdapterTab1 = new MyAdapterTab1(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(myAdapterTab1);
        myAdapterTab2 = new MyAdapterTab2(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView2.setLayoutManager(gridLayoutManager);
        recyclerView2.setAdapter(myAdapterTab2);

        myAdapterTab1.result(new MyAdapterTab1.TabListener() {
            @Override
            public void setTabList(List<BeanTab.DataBean.ListBean> list) {

                myAdapterTab2.setList(list);
            }
        });
        return view;
    }



    private void doHttp() {
        new HttpUtiles().get("http://www.zhaoapi.cn/product/getProductCatagory").result(new Http.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanTab beanTab = gson.fromJson(data, BeanTab.class);
                List<BeanTab.DataBean> data1 = beanTab.getData();
                myAdapterTab1.setList(data1);
                myAdapterTab2.setList(data1.get(0).getList());
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}

