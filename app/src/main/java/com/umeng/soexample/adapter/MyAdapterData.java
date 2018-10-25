package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.umeng.soexample.R;

import com.umeng.soexample.model.BeanData;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterData extends BaseAdapter {
    private Context context;
    private List<BeanData.DataBean> list = new ArrayList<>();
    public MyAdapterData(Context context){
        this.context=context;
    }
    public void setList(List<BeanData.DataBean> list){
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
            view = View.inflate(context,R.layout.list_item,null);
            viewHolder.textView = view.findViewById(R.id.tv_title_list);
            viewHolder.recyclerView = view.findViewById(R.id.rv_recyler_list);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(list.get(i).getSellerName());
        MyAdapterDataList myAdapterDataList = new MyAdapterDataList(context);
        viewHolder.recyclerView.setAdapter(myAdapterDataList);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(staggeredGridLayoutManager);
        myAdapterDataList.setList(list.get(i).getList());
        return view;
    }
    private class ViewHolder{
        private TextView textView;
        private RecyclerView recyclerView;
    }
}
