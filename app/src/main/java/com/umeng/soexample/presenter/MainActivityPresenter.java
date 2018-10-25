package com.umeng.soexample.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.umeng.soexample.R;
import com.umeng.soexample.activity.FristFragment;
import com.umeng.soexample.activity.MainActivity;
import com.umeng.soexample.activity.MyFragment;
import com.umeng.soexample.activity.ShopFragment;
import com.umeng.soexample.activity.TabulateFragment;
import com.umeng.soexample.mvp.view.AppDelage;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter extends AppDelage {
    private List<Fragment> list = new ArrayList<>();
    private ViewPager viewPager;
    private ImageView img1,img2,img3,img4;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        super.initData();
        viewPager = get(R.id.vp_view_main);
        img1 = get(R.id.iv_img1_main);
        img2 = get(R.id.iv_img2_main);
        img3 = get(R.id.iv_img3_main);
        img4 = get(R.id.iv_img4_main);
        list.add(new FristFragment());
        list.add(new TabulateFragment());
        list.add(new ShopFragment());
        list.add(new MyFragment());
        MyAdapterFragment myAdapterFragment = new MyAdapterFragment(((MainActivity) context).getSupportFragmentManager());
        viewPager.setAdapter(myAdapterFragment);
        setOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                img1.setImageResource(R.drawable.index_yes);
                img2.setImageResource(R.drawable.list_no);
                img3.setImageResource(R.drawable.car_no);
                img4.setImageResource(R.drawable.me_no);
            }
        },R.id.iv_img1_main);
        setOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                img1.setImageResource(R.drawable.index_no);
                img2.setImageResource(R.drawable.list_yes);
                img3.setImageResource(R.drawable.car_no);
                img4.setImageResource(R.drawable.me_no);
            }
        },R.id.iv_img2_main);
        setOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                img1.setImageResource(R.drawable.index_no);
                img2.setImageResource(R.drawable.list_no);
                img3.setImageResource(R.drawable.car_yes);
                img4.setImageResource(R.drawable.me_no);
            }
        },R.id.iv_img3_main);
        setOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
                img1.setImageResource(R.drawable.index_no);
                img2.setImageResource(R.drawable.list_no);
                img3.setImageResource(R.drawable.car_no);
                img4.setImageResource(R.drawable.me_yes);
            }
        },R.id.iv_img4_main);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(0==position){
                    img1.setImageResource(R.drawable.index_yes);
                    img2.setImageResource(R.drawable.list_no);
                    img3.setImageResource(R.drawable.car_no);
                    img4.setImageResource(R.drawable.me_no);
                }else if(1==position){
                    img1.setImageResource(R.drawable.index_no);
                    img2.setImageResource(R.drawable.list_yes);
                    img3.setImageResource(R.drawable.car_no);
                    img4.setImageResource(R.drawable.me_no);
                }else if(2==position){
                    img1.setImageResource(R.drawable.index_no);
                    img2.setImageResource(R.drawable.list_no);
                    img3.setImageResource(R.drawable.car_yes);
                    img4.setImageResource(R.drawable.me_no);
                }else if(3==position){
                    img1.setImageResource(R.drawable.index_no);
                    img2.setImageResource(R.drawable.list_no);
                    img3.setImageResource(R.drawable.car_no);
                    img4.setImageResource(R.drawable.me_yes);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private class MyAdapterFragment extends FragmentPagerAdapter{

        public MyAdapterFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
