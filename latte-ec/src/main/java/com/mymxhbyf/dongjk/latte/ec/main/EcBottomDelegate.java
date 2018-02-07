package com.mymxhbyf.dongjk.latte.ec.main;

import android.graphics.Color;

import com.mymxhbyf.dongjk.latte.ec.main.cart.ShopCartDelegate;
import com.mymxhbyf.dongjk.latte.ec.main.discover.DiscoverDelegate;
import com.mymxhbyf.dongjk.latte.ec.main.index.IndexDelegate;
import com.mymxhbyf.dongjk.latte.ec.main.sort.SortDelegate;
import com.mymxhbyf.dongjk.lattecore.delegates.bottom.BaseBottomDelegate;
import com.mymxhbyf.dongjk.lattecore.delegates.bottom.BottomItemDelegate;
import com.mymxhbyf.dongjk.lattecore.delegates.bottom.BottomTabBean;
import com.mymxhbyf.dongjk.lattecore.delegates.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * Created by DongJK on 2018/1/29.
 */

public class EcBottomDelegate extends BaseBottomDelegate{



    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
