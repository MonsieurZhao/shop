package com.umeng.soexample.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.model.BeanLogin;
import com.umeng.soexample.mvp.presenter.BaseActivityPresenter;
import com.umeng.soexample.mvp.presenter.BaseFragmentPresenter;
import com.umeng.soexample.net.Http;
import com.umeng.soexample.net.HttpUtiles;
import com.umeng.soexample.presenter.MainActivityPresenter;
import com.umeng.soexample.presenter.MyFragmentPresenter;

public class MyFragment extends Fragment{

    private SharedPreferences sp;
    private TextView name;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_my,null);
        imageView = view.findViewById(R.id.iv_img_my);
        name = view.findViewById(R.id.tv_name_my);
        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        if(sp.getBoolean("islogin",false)){
            dologin();
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("islogin",false)){
//                    dologin();
                }else{
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }


            }
        });
        return view;
    }

    private void dologin() {
        String mobile = sp.getString("mobile", "");
        String password = sp.getString("password", "");
        new HttpUtiles().get("http://www.zhaoapi.cn/user/login?mobile="+ mobile +"&password="+ password).result(new Http.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanLogin beanLogin = gson.fromJson(data, BeanLogin.class);
                BeanLogin.DataBean data1 = beanLogin.getData();
                String nickname = (String) data1.getNickname();
                String username = data1.getUsername();
                if(TextUtils.isEmpty(nickname)){
                    name.setText(username);
                }else{
                    name.setText(nickname);
                }
            }

            @Override
            public void fail(String error) {

            }
        });

    }
}

