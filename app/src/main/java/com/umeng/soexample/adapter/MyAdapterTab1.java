package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.model.BeanTab;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterTab1 extends RecyclerView.Adapter<MyAdapterTab1.MyViewHodlerTab1> {
    private Context context;
    public MyAdapterTab1(Context context){
        this.context=context;
    }
    private List<BeanTab.DataBean> list = new ArrayList<>();
    public void setList( List<BeanTab.DataBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHodlerTab1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.recyler1_tab,null);
        MyViewHodlerTab1 myViewHodlerTab1 = new MyViewHodlerTab1(view);
        return myViewHodlerTab1;
    }

    @Override
    public void onBindViewHolder(MyViewHodlerTab1 holder, final int position) {
        holder.textView.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabListener.setTabList(list.get(position).getList());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHodlerTab1 extends RecyclerView.ViewHolder{

        private  TextView textView;

        public MyViewHodlerTab1(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_text_tab1);
        }
    }
    private TabListener tabListener;
    public void result(TabListener tabListener){
        this.tabListener=tabListener;
    }
    public interface TabListener{
        void setTabList(List<BeanTab.DataBean.ListBean> list);
    }

}
