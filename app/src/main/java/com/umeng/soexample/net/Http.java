package com.umeng.soexample.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Http {
    private Http(){};
    private  static Http okHttpUtils;
    public static Http getOkHttpUtils(){
        if(okHttpUtils==null){
            okHttpUtils = new Http();
        }
       return okHttpUtils;
    }
    public Http get(String url){
        final Message message= Message.obtain();
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                RequestBody body = request.body();
                String method = request.method();
                Log.i("Http", "intercept: "+body+"==="+method);
                return chain.proceed(request);
            }
        };
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                message.what=100;
                message.obj=e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                message.what=101;
                message.obj=response.body().string();
                handler.sendMessage(message);
            }
        });
        return this;
    }
    public Http post(String url, RequestBody body){
        final Message message= Message.obtain();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder() .post(body).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                message.what=100;
                message.obj=e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                message.what=101;
                message.obj=response.body().string();
                handler.sendMessage(message);
            }

        });
        return this;
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101://成功
                    String data= (String) msg.obj;
                    listener.success(data);
                    break;
                case 100://失败
                    String error= (String) msg.obj;
                    listener.fail(error);
                    break;
            }
        }
    };
    private HttpListener listener;
    public void result(HttpListener listener){
        this.listener=listener;
    }


    public interface HttpListener{
        void success(String data);
        void fail(String error);
    }

}
