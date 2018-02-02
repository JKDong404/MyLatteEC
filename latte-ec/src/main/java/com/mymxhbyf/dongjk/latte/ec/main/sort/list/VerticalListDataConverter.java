package com.mymxhbyf.dongjk.latte.ec.main.sort.list;

import android.util.Size;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.DataConverter;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.ItemType;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.MultipleFields;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.MultipleItemEntity;
import com.mymxhbyf.dongjk.lattecore.util.log.LatteLogger;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * 解析数据
 * Created by DongJK on 2018/2/1.
 */

public final class VerticalListDataConverter extends DataConverter{

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++){
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,name)
                    .setField(MultipleFields.TAG,false)//是否选中状态
                    .build();

            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG,true);
        }
        return dataList;
    }
}
