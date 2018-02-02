package com.mymxhbyf.dongjk.latte.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.DataConverter;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.ItemType;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.MultipleFields;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.MultipleItemEntity;
import com.mymxhbyf.dongjk.lattecore.util.log.LatteLogger;


import java.util.ArrayList;

/**
 * Created by DongJK on 2018/1/30.
 */

public class IndexDataConverter extends DataConverter{
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++){
            final JSONObject data = dataArray.getJSONObject(i);

            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            //banners 是个数组,所以用array的方式取出
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null){
                type = ItemType.TEXT;
            }else if (imageUrl != null && text == null){
                type = ItemType.IMAGE;
            }else if (imageUrl != null){
                type = ItemType.TEXT_IMAGE;
            }else if (banners != null){
                type = ItemType.BANNER;
                //banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++){
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,type)
                    .setField(MultipleFields.SPAN_SIZE,spanSize)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,text)
                    .setField(MultipleFields.IMAGE_URL,imageUrl)
                    .setField(MultipleFields.BANNERS,banners)
                    .build();
            ENTITYS.add(entity);
        }

        return ENTITYS;
    }
}
