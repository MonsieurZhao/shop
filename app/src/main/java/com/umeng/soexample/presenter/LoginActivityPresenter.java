package com.umeng.soexample.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.activity.InsertActivity;
import com.umeng.soexample.activity.MainActivity;
import com.umeng.soexample.model.BeanLogin;
import com.umeng.soexample.mvp.view.AppDelage;
import com.umeng.soexample.net.Http;
import com.umeng.soexample.net.HttpUtiles;

public class LoginActivityPresenter extends AppDelage {

    private EditText mName;
    private EditText mPass;
    private SharedPreferences sp;
    private String name;
    private String pass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }
    @Override
    public void initData() {
        super.initData();
        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        mName = get(R.id.et_name_login);
        mPass = get(R.id.et_pass_login);
        setOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InsertActivity.class);
                context.startActivity(intent);
            }
        },R.id.tv_insert_login);
        setOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        },R.id.bt_login_login);

    }

    private void login() {
        name = mName.getText().toString();
        pass = mPass.getText().toString().trim();
        if(name.length()!=11|| pass.length()<6){
            Toast.makeText(context, "请输入正确的手机号或密码", Toast.LENGTH_SHORT).show();
            return;
        }
        new HttpUtiles().get("http://www.zhaoapi.cn/user/login?mobile="+name +"&password="+pass).result(new Http.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanLogin beanLogin = gson.fromJson(data, BeanLogin.class);
                String code = beanLogin.getCode();
                if("1".equals(code)){
                    Toast.makeText(context, "账号或密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                String token = beanLogin.getData().getToken();
                sp.edit().putString("mobile", name).putString("password", pass).putString("token",token).putBoolean("islogin",true).commit();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
