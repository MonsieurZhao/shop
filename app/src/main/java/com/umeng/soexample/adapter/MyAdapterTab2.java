package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.umeng.soexample.R;
import com.umeng.soexample.model.BeanTab;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterTab2 extends RecyclerView.Adapter<MyAdapterTab2.MyViewHodlerTab2> {
    private Context context;
    public MyAdapterTab2(Context context){
        this.context=context;
    }
    private List<BeanTab.DataBean.ListBean> list = new ArrayList<>();
    public void setList( List<BeanTab.DataBean.ListBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHodlerTab2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.recyler2_tab,null);
        MyViewHodlerTab2 myViewHodlerTab2 = new MyViewHodlerTab2(view);
        return myViewHodlerTab2;
    }

    @Override
    public void onBindViewHolder(MyViewHodlerTab2 holder, int position) {
        holder.textView.setText(list.get(position).getName());
        Picasso.with(context).load(list.get(position).getIcon()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHodlerTab2 extends RecyclerView.ViewHolder{

        private  TextView textView;
        private  ImageView imageView;

        public MyViewHodlerTab2(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_img_tab2);
            textView = itemView.findViewById(R.id.tv_text_tab2);
        }
    }
}
