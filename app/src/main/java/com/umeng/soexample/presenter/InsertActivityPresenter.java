package com.umeng.soexample.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.soexample.R;
import com.umeng.soexample.activity.InsertActivity;
import com.umeng.soexample.activity.LoginActivity;
import com.umeng.soexample.activity.MainActivity;
import com.umeng.soexample.model.BeanLogin;
import com.umeng.soexample.mvp.view.AppDelage;
import com.umeng.soexample.net.Http;
import com.umeng.soexample.net.HttpUtiles;

public class InsertActivityPresenter extends AppDelage {
    private EditText mName,mPass;
    private String name,pass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_insert;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }
    @Override
    public void initData() {
        super.initData();
        mName = get(R.id.et_name_insert);
        mPass = get(R.id.et_pass_insert);
        setOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        },R.id.bt_insert_insert);


    }

    private void insert() {
        name = mName.getText().toString();
        pass = mPass.getText().toString().trim();
        if(name.length()!=11|| pass.length()<6){
            Toast.makeText(context, "请输入正确的手机号或密码", Toast.LENGTH_SHORT).show();
            return;
        }
        new HttpUtiles().get("http://www.zhaoapi.cn/user/reg?mobile="+name +"&password="+pass).result(new Http.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                BeanLogin beanLogin = gson.fromJson(data, BeanLogin.class);
                String code = beanLogin.getCode();
                if("1".equals(code)){
                    Toast.makeText(context, "账号或密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
