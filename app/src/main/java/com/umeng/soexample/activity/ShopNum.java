package com.umeng.soexample.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.umeng.soexample.R;
import com.umeng.soexample.adapter.MyAdapterShopList;
import com.umeng.soexample.model.BeanShop;

import java.util.List;

public class ShopNum extends RelativeLayout implements View.OnClickListener{

    private EditText mNum;
    private int num;

    public ShopNum(Context context) {
        super(context);
        init(context);
    }

    public ShopNum(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShopNum(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.shop_num,null);
        ImageView add = view.findViewById(R.id.shop_num_add);
        ImageView jian = view.findViewById(R.id.shop_num_jian);
        mNum = view.findViewById(R.id.shop_num_num);
        add.setOnClickListener(this);
        jian.setOnClickListener(this);
        addView(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shop_num_add:
                num++;
                mNum.setText(num+"");
                list.get(position).setNum(num);
                shopNumListener.setListShop();
                break;
            case R.id.shop_num_jian:
                if(num>1){
                    num--;
                    mNum.setText(num+"");
                    list.get(position).setNum(num);
                    shopNumListener.setListShop();
                }else{
                    Toast.makeText(getContext(), "商品数量最后为一", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
    private MyAdapterShopList myAdapterShopList;
    private List<BeanShop.DataBean.ListBean> list;
    private int position;
    public void setdata(MyAdapterShopList myAdapterShopList, List<BeanShop.DataBean.ListBean> list, int position) {
        this.myAdapterShopList=myAdapterShopList;
        this.list=list;
        this.position=position;
        num = list.get(position).getNum();
        mNum.setText(num+"");
    }
    private ShopNumListener shopNumListener;
    public void result( ShopNumListener shopNumListener){
        this.shopNumListener=shopNumListener;
    }
    public interface ShopNumListener{
        void setListShop();
    }
}
