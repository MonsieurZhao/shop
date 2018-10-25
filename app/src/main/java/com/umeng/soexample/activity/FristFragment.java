package com.umeng.soexample.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.umeng.soexample.R;
import com.umeng.soexample.adapter.MyAdapterData;
import com.umeng.soexample.model.BeanBanner;
import com.umeng.soexample.model.BeanData;
import com.umeng.soexample.mvp.presenter.BaseFragmentPresenter;
import com.umeng.soexample.net.Http;
import com.umeng.soexample.net.HttpUtiles;
import com.umeng.soexample.presenter.FristFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class FristFragment extends Fragment{
    private ListView listView;
    private ViewPager viewPager;
    private List<BeanBanner.DataBean> list = new ArrayList<>();
    private MyAdapterBanner myAdapterBanner;
    private MyAdapterData myAdapterData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(),R.layout.fragment_frist,null);
        viewPager = view.findViewById(R.id.vp_view_frist);
        listView = view.findViewById(R.id.lv_list_frist);
        myAdapterBanner = new MyAdapterBanner();
        viewPager.setAdapter(myAdapterBanner);
        myAdapterData = new MyAdapterData(getActivity());
        listView.setAdapter(myAdapterData);
        doHttpBanner();
        doHttpData();
        return view;
    }
    private void doHttpData() {
        new HttpUtiles().get("http://www.zhaoapi.cn/product/getCarts?uid=71").result(new Http.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanData beanData = gson.fromJson(data, BeanData.class);
                List<BeanData.DataBean> data1 = beanData.getData();
                data1.remove(0);
                myAdapterData.setList(data1);
            }

            @Override
            public void fail(String error) {

            }
        });

    }

    private void doHttpBanner() {
        new HttpUtiles().get("http://www.zhaoapi.cn/ad/getAd").result(new Http.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanBanner beanBanner = gson.fromJson(data, BeanBanner.class);
                List<BeanBanner.DataBean> data1 = beanBanner.getData();
                list.addAll(data1);
                myAdapterBanner.notifyDataSetChanged();
                handler.sendEmptyMessageDelayed(1000,3000);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    private class MyAdapterBanner extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            ImageView imageView = new ImageView(getActivity());
            View view = View.inflate(getActivity(),R.layout.banner_img,null);
            ImageView imageView =view.findViewById(R.id.iv_img_banner);
            Picasso.with(getActivity()).load(list.get(position).getIcon()).fit().into(imageView);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    private Handler handler = new Handler(){

        private int currentItem;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1000){
                currentItem = viewPager.getCurrentItem();
                if(currentItem <list.size()-1){
                    currentItem++;
                }else{
                    currentItem =0;
                }
                viewPager.setCurrentItem(currentItem);
                handler.sendEmptyMessageDelayed(1000,3000);
            }
        }
    };
}

