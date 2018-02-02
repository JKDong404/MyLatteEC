package com.mymxhbyf.dongjk.lattecore.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymxhbyf.dongjk.lattecore.R;
import com.mymxhbyf.dongjk.lattecore.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DongJK on 2018/1/30.
 */

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MultipleViewHolder> implements BaseQuickAdapter.SpanSizeLookup,OnItemClickListener{

    //确保初始化一次banner，防止重复item
    private boolean mIsInitBanner = false;

    private static final RequestOptions RECYCLER_OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();


    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data){
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter){
        return new MultipleRecyclerAdapter(converter.convert());
    }

    private void init(){
        //设置不同的item布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);//文字
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);//图片
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);//图，文
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);//轮播图

        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);

    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()){
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.text_single,text);
                break;

                case ItemType.IMAGE:
                    imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                    Glide.with(mContext)
                            .load(imageUrl)
                            .apply(RECYCLER_OPTIONS)
                            .into((ImageView) holder.getView(R.id.img_single));
                    break;

                    case ItemType.TEXT_IMAGE:
                        text = entity.getField(MultipleFields.TEXT);
                        imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                        holder.setText(R.id.tv_multiple,text);
                        Glide.with(mContext)
                                .load(imageUrl)
                                .apply(RECYCLER_OPTIONS)
                                .into((ImageView) holder.getView(R.id.img_multiple));
                        break;

//                        case ItemType.BANNER:
//                            if (!mIsInitBanner){
//                                bannerImages = entity.getField(MultipleFields.BANNERS);
//                                final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
//                                BannerCreator.setDefault(convenientBanner,bannerImages,this);
//                                mIsInitBanner = true;
//                            }
//
//                            break;
                default:
                    break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
