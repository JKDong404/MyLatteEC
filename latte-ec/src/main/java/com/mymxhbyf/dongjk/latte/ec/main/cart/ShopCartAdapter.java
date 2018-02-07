package com.mymxhbyf.dongjk.latte.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.mymxhbyf.dongjk.latte.ec.R;
import com.mymxhbyf.dongjk.lattecore.app.Latte;
import com.mymxhbyf.dongjk.lattecore.net.RestClient;
import com.mymxhbyf.dongjk.lattecore.net.callback.ISuccess;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.MultipleFields;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.MultipleItemEntity;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.MultipleRecyclerAdapter;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by DongJK on 2018/2/6.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter{

    private boolean mIsSelectedAll = false;
    private ICartItemListener mCartItemListener = null;
    //所有商品的总价
    private double mTotalPrice = 0.00;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data){
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        //添加购物车item
        addItemType(ShopCartItemType.SHOP_CART_ITEM_TYPE, R.layout.item_shop_cart);
    }

    public void setIsSelectedAll(boolean isSelectedAll){
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setCartItemListener(ICartItemListener listener){
        this.mCartItemListener = listener;
    }

    public double getTotalPrice(){
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case ShopCartItemType.SHOP_CART_ITEM_TYPE:
                //先取出所有的值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count =  entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);

                //取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);

                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);

                //在左侧选中按钮渲染之前改变全选按钮的状态
                entity.setField(ShopCartItemFields.IS_SELECTED,mIsSelectedAll);

                //根据数据状态显示左侧是否选中
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                if (isSelected){
                    //选中，设置为主题色
                    iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(),R.color.app_main));
                }else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧选中按钮点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected){
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED,false);
                        }else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(),R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED,true);
                        }
                    }
                });

                //添加加减号的点击事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1){
                            RestClient.builder()
                                    .url("http://127.0.0.1/shop")
                                    .params("count",currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum --;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mCartItemListener != null){
                                                mTotalPrice = mTotalPrice - price;
                                                final double itemTotalPrice = count * price;
                                                mCartItemListener.onItemClick(itemTotalPrice);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                            RestClient.builder()
                                    .url("http://127.0.0.1/shop")
                                    .params("count",currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum ++;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mCartItemListener != null){
                                                mTotalPrice = mTotalPrice + price;
                                                final double itemTotalPrice = count * price;
                                                mCartItemListener.onItemClick(itemTotalPrice);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                });

                break;
                default:
                    break;
        }

    }
}
