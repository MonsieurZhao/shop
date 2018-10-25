package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.umeng.soexample.R;
import com.umeng.soexample.model.BeanData;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterDataList extends RecyclerView.Adapter<MyAdapterDataList.MyViewHodler> {
    private Context context;
    private List<BeanData.DataBean.BeanList> list = new ArrayList<>();
    public MyAdapterDataList(Context context){
        this.context=context;
    }
    public void setList(List<BeanData.DataBean.BeanList> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.recyler_item,null);
        MyViewHodler myViewHodler = new MyViewHodler(view);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        Picasso.with(context).load(split[0]).fit().into(holder.img);
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText("￥:"+list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder{

        private  ImageView img;
        private  TextView title,price;

        public MyViewHodler(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_img_recyler);
            title = itemView.findViewById(R.id.tv_title_recyler);
            price = itemView.findViewById(R.id.tv_price_recyler);
        }
    }
}
