package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.model.BeanData;
import com.umeng.soexample.model.BeanShop;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterShop extends BaseAdapter {
    private Context context;
    private List<BeanShop.DataBean> list = new ArrayList<>();
    public MyAdapterShop(Context context){
        this.context=context;
    }
    public void setList(List<BeanShop.DataBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
            view = View.inflate(context,R.layout.shop_list_item,null);
            viewHolder.textView = view.findViewById(R.id.tv_title_list_shop);
            viewHolder.recyclerView = view.findViewById(R.id.rv_recyler_list_shop);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(list.get(i).getSellerName());
        MyAdapterShopList myAdapterDataList = new MyAdapterShopList(context);
        viewHolder.recyclerView.setAdapter(myAdapterDataList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(manager);
        myAdapterDataList.setList(list.get(i).getList());
        myAdapterDataList.result(new MyAdapterShopList.Shop1Listener() {
            @Override
            public void setListData() {
                shopListener.setList(list);
            }
        });
        return view;
    }
    private class ViewHolder{
        private TextView textView;
        private RecyclerView recyclerView;
    }
    private ShopListener shopListener;
    public void result(ShopListener shopListener){
        this.shopListener=shopListener;
    }
    public interface ShopListener{
        void setList(List<BeanShop.DataBean> list);
    }
}
