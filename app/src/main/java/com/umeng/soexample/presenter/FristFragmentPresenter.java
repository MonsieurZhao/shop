package com.umeng.soexample.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
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
import com.umeng.soexample.mvp.view.AppDelage;
import com.umeng.soexample.net.Http;
import com.umeng.soexample.net.HttpUtiles;

import java.util.ArrayList;
import java.util.List;

public class FristFragmentPresenter extends AppDelage {

    private ViewPager viewPager;
    private ListView listView;
    private List<BeanBanner.DataBean> list = new ArrayList<>();
    private MyAdapterBanner myAdapterBanner;
    private MyAdapterData myAdapterData;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_frist;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }
    @Override
    public void initData() {
        super.initData();
        viewPager = get(R.id.vp_view_frist);
        listView = get(R.id.lv_list_frist);
        myAdapterBanner = new MyAdapterBanner();
        viewPager.setAdapter(myAdapterBanner);
        myAdapterData = new MyAdapterData(context);
        listView.setAdapter(myAdapterData);
        doHttpBanner();
        doHttpData();

    }

    private void doHttpData() {
        new HttpUtiles().get("http://www.zhaoapi.cn/product/getCarts?uid=71").result(new Http.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanData beanData = gson.fromJson(data, BeanData.class);
                List<BeanData.DataBean> data1 = beanData.getData();
                myAdapterData.setList(data1);
                Toast.makeText(context, data1.get(1).getSellerName()+"", Toast.LENGTH_SHORT).show();
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

    private class MyAdapterBanner extends PagerAdapter{

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
            ImageView imageView = new ImageView(context);
            Picasso.with(context).load(list.get(position).getIcon()).fit().into(imageView);
            container.addView(imageView);
            return imageView;
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
