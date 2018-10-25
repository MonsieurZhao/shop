package com.umeng.soexample.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.MyAdapterShop;
import com.umeng.soexample.model.BeanData;
import com.umeng.soexample.model.BeanShop;
import com.umeng.soexample.mvp.presenter.BaseFragmentPresenter;
import com.umeng.soexample.net.Http;
import com.umeng.soexample.net.HttpUtiles;
import com.umeng.soexample.presenter.FristFragmentPresenter;
import com.umeng.soexample.presenter.ShopFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment implements View.OnClickListener{
    private String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    private MyAdapterShop myAdapterShop;
    private ImageView imageView;
    private TextView mPriceAll,mNummAll;
    private boolean isAll=false;
    private List<BeanShop.DataBean> data1=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_shop,null);
        ListView listView = view.findViewById(R.id.lv_list_shop);
        myAdapterShop = new MyAdapterShop(getActivity());
        listView.setAdapter(myAdapterShop);
        imageView = view.findViewById(R.id.iv_img_shop);
        mPriceAll = view.findViewById(R.id.tv_priceAll_shop);
        mNummAll = view.findViewById(R.id.tv_num_shop);
        imageView.setOnClickListener(this);
        doHttp();
        myAdapterShop.result(new MyAdapterShop.ShopListener() {
            @Override
            public void setList(List<BeanShop.DataBean> list) {
                int numAll=0;
                double priceAll=0;
                int numsAll=0;
                int nums=0;
                for(int i =0;i<list.size();i++){
                    List<BeanShop.DataBean.ListBean> list1 = data1.get(i).getList();
                    for(int j=0;j<list1.size();j++){
                        boolean ischeck = list1.get(j).isIscheck();
                        numsAll++;
                        if(ischeck){
                            numAll+=list1.get(j).getNum();
                            priceAll+=list1.get(j).getPrice()*list1.get(j).getNum();
                            nums++;
                        }
                    }
                }
                if(nums<numsAll){
                    isAll=false;
                    imageView.setImageResource(R.drawable.cricle_no);
                }else{
                    isAll=true;
                    imageView.setImageResource(R.drawable.cricle_yes);
                }
                mPriceAll.setText(priceAll+"");
                mNummAll.setText("("+numAll+")");
                myAdapterShop.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void doHttp() {
        new HttpUtiles().get(url).result(new Http.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanShop beanShop = gson.fromJson(data, BeanShop.class);
                data1 = beanShop.getData();
                data1.remove(0);
                myAdapterShop.setList(data1);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.iv_img_shop:
                Allselect();
            break;

        }
    }

    private void Allselect() {
        double priceAll=0;
        int numAll=0;
        isAll=!isAll;
        for(int i =0;i<data1.size();i++){
            List<BeanShop.DataBean.ListBean> list = data1.get(i).getList();
            for(int j=0;j<list.size();j++){
                list.get(j).setIscheck(isAll);
                numAll+=list.get(j).getNum();
                priceAll+=list.get(j).getPrice()*list.get(j).getNum();
            }
        }
        if(isAll){
            imageView.setImageResource(R.drawable.cricle_yes);
            mPriceAll.setText(priceAll+"");
            mNummAll.setText("("+numAll+")");
            myAdapterShop.notifyDataSetChanged();
        }else{
            imageView.setImageResource(R.drawable.cricle_no);
            mPriceAll.setText("合计:00.00");
            mNummAll.setText("(0)");
            myAdapterShop.notifyDataSetChanged();
        }

    }
}

