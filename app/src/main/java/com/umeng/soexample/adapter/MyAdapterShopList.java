package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.umeng.soexample.R;
import com.umeng.soexample.activity.ShopNum;
import com.umeng.soexample.model.BeanData;
import com.umeng.soexample.model.BeanShop;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterShopList extends RecyclerView.Adapter<MyAdapterShopList.MyViewHodler> {
    private Context context;
    private List<BeanShop.DataBean.ListBean> list = new ArrayList<>();
    private boolean ischeck;

    public MyAdapterShopList(Context context){
        this.context=context;
    }
    public void setList(List<BeanShop.DataBean.ListBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.shop_recyler_item,null);
        MyViewHodler myViewHodler = new MyViewHodler(view);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(final MyViewHodler holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        ischeck = list.get(position).isIscheck();
        if(ischeck){
            holder.img1.setImageResource(R.drawable.cricle_yes);
        }else{
            holder.img1.setImageResource(R.drawable.cricle_no);
        }
        Picasso.with(context).load(split[0]).fit().into(holder.img2);
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText("￥:"+list.get(position).getPrice());
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).isIscheck()){
                    list.get(position).setIscheck(false);
                }else {
                    list.get(position).setIscheck(true);
                }
                notifyItemChanged(position);
                shop1Listener.setListData();
            }
        });
        holder.shopNum.setdata(this,list,position);
        holder.shopNum.result(new ShopNum.ShopNumListener() {
            @Override
            public void setListShop() {
                shop1Listener.setListData();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder{

        private  ImageView img1,img2;
        private  TextView title,price;
        private  ShopNum shopNum;

        public MyViewHodler(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.iv_img1_recyler_shop);
            img2 = itemView.findViewById(R.id.iv_img2_recyler_shop);
            title = itemView.findViewById(R.id.tv_title_recyler_shop);
            price = itemView.findViewById(R.id.tv_price_recyler_shop);
            shopNum = itemView.findViewById(R.id.tv_num_recyler_shop);
        }
    }
    private Shop1Listener shop1Listener;
    public void result(Shop1Listener shop1Listener){
        this.shop1Listener=shop1Listener;
    }
    public interface Shop1Listener{
        void setListData();
    }
}
