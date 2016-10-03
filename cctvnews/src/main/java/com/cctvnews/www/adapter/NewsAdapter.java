package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.NewsRefreshDatas;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class NewsAdapter extends BaseAdapter {
    private List<NewsRefreshDatas.ItemListBean> itemListBeen;
    private Context mcontext;
    private LayoutInflater inflater;

    public static final int TYPE_0 = 0;//普通布局
    public static final int TYPE_1 = 1;//视频布局
    public static final int TYPE_2 = 2;//三张大图布局
    public static final int TYPE_3 = 3;//没有“新播报”的布局

    public NewsAdapter(List<NewsRefreshDatas.ItemListBean> itemListBeen, Context mcontext) {
        this.itemListBeen = itemListBeen;
        this.mcontext = mcontext;
        inflater = LayoutInflater.from(mcontext);
    }

    @Override
    public int getCount() {
        return itemListBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return itemListBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 作用：返回当前是哪种类型数据
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        String articleType = itemListBeen.get(position).getItemType();
        // String articleType = dataBean.getItemList().get(position).getItemType();
        int type = 0;
        switch (articleType) {
            case "classtopic_flag":
                type = TYPE_0;
                break;
            case "vod_flag":
                type = TYPE_1;
                break;
            case "album_flag":
                if ("".equals(itemListBeen.get(position).getItemImage().getImgUrl3())) {
                    type = TYPE_3;
                } else if ("".equals(itemListBeen.get(position).getItemImage().getImgUrl2())){
                    type = TYPE_3;
                }else {
                    type = TYPE_2;
                }
                break;
            case "article_flag":
                type = TYPE_0;
                break;
            case "it_flag":
                type = TYPE_3;
                break;
        }
        return type;
    }
    /**
     * 确定一共有多少个布局
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolderCommom commom = null;
        ViewHolderVideo video = null;
        ViewHolderMoreImgge moreImage = null;
        ViewHolderNoneCommon noneCommon = null;
        if (convertView == null) {
            switch (type) {
                case TYPE_0:
                    commom = new ViewHolderCommom();
                    convertView = inflater.inflate(R.layout.news_pull_list_item_common, parent, false);
                    commom.common_iv = (ImageView) convertView.findViewById(R.id.news_item_common_iv);
                    commom.common_tittle_tv = (TextView) convertView.findViewById(R.id.news_item_common_tittle_tv);
                    commom.common_bottom_tv = (TextView) convertView.findViewById(R.id.news_item_common_bottom_tv);
                    convertView.setTag(commom);
                    break;
                case TYPE_1:
                    video = new ViewHolderVideo();
                    convertView = inflater.inflate(R.layout.news_pull_list_item_video, parent, false);
                    video.video_iv = (ImageView) convertView.findViewById(R.id.news_item_video_iv);
                    video.tittle_tv = (TextView) convertView.findViewById(R.id.news_item_video_tittle_tv);
                    convertView.setTag(video);
                    break;
                case TYPE_2:
                    moreImage = new ViewHolderMoreImgge();
                    convertView = inflater.inflate(R.layout.news_pull_list_item_moreimage, parent, false);
                    moreImage.moreImage_iv01 = (ImageView) convertView.findViewById(R.id.news_item_moreimage_iv01);
                    moreImage.moreImage_iv02 = (ImageView) convertView.findViewById(R.id.news_item_moreimage_iv02);
                    moreImage.moreImage_iv03 = (ImageView) convertView.findViewById(R.id.news_item_moreimage_iv03);
                    moreImage.moreImage_tv = (TextView) convertView.findViewById(R.id.news_item_moreimage_tv);
                    convertView.setTag(moreImage);
                    break;
                case TYPE_3:
                    noneCommon = new ViewHolderNoneCommon();
                    convertView = inflater.inflate(R.layout.news_pull_list_item_nonecommon, parent, false);
                    noneCommon.nonecommon_iv = (ImageView) convertView.findViewById(R.id.news_item_nonecommon_iv);
                    noneCommon.nonecommon_tittle_tv = (TextView) convertView.findViewById(R.id.news_item_nonecommon_tittle_tv);
                    convertView.setTag(noneCommon);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_0:
                    commom = (ViewHolderCommom) convertView.getTag();
                    break;
                case TYPE_1:
                    video = (ViewHolderVideo) convertView.getTag();
                    break;
                case TYPE_2:
                    moreImage = (ViewHolderMoreImgge) convertView.getTag();
                    break;
                case TYPE_3:
                    noneCommon = (ViewHolderNoneCommon) convertView.getTag();
            }
        }
        /**数据映射*/
        switch (type) {
            case TYPE_0:
                commom.common_tittle_tv.setText(itemListBeen.get(position).getItemTitle());
                Picasso.with(mcontext).load(itemListBeen.get(position).getItemImage().getImgUrl1())
                        .into(commom.common_iv);
                if (itemListBeen.get(position).getPubDate().equals("0")) {
                    commom.common_bottom_tv.setVisibility(View.INVISIBLE);
                } else {
                    commom.common_bottom_tv.setVisibility(View.VISIBLE);
                }
                break;
            case TYPE_1:
                video.tittle_tv.setText(itemListBeen.get(position).getItemTitle());
                Picasso.with(mcontext).load(itemListBeen.get(position).getItemImage().getImgUrl1())
                        .into(video.video_iv);
                break;
            case TYPE_2:
                moreImage.moreImage_tv.setText(itemListBeen.get(position).getItemTitle());
                Picasso.with(mcontext).load(itemListBeen.get(position).getItemImage().getImgUrl1())
                        .into(moreImage.moreImage_iv01);
                Picasso.with(mcontext).load(itemListBeen.get(position).getItemImage().getImgUrl2())
                        .into(moreImage.moreImage_iv02);
                Picasso.with(mcontext).load(itemListBeen.get(position).getItemImage().getImgUrl3())
                        .into(moreImage.moreImage_iv03);
                break;
            case TYPE_3:
                noneCommon.nonecommon_tittle_tv.setText(itemListBeen.get(position).getItemTitle());
                Picasso.with(mcontext).load(itemListBeen.get(position).getItemImage().getImgUrl1())
                        .into(noneCommon.nonecommon_iv);
                break;
        }
        return convertView;
    }


    /**
     * 适配news_pull_list_item_common.xml
     * 专题
     */
    public static class ViewHolderCommom {
        ImageView common_iv;
        TextView common_tittle_tv;
        TextView common_bottom_tv;
    }

    /**
     * 适配news_pull_list_item_video.xml
     * 专题
     */
    public static class ViewHolderVideo {
        ImageView video_iv;
        TextView tittle_tv;
    }

    /**
     * 适配news_pull_list_item_moreimage.xml
     * 专题
     */
    public static class ViewHolderMoreImgge {
        ImageView moreImage_iv01;
        ImageView moreImage_iv02;
        ImageView moreImage_iv03;
        TextView moreImage_tv;
    }
    /**
     * 适配news_pull_list_item_nonecommon.xml
     * 专题
     */
    public static class ViewHolderNoneCommon {
        ImageView nonecommon_iv;
        TextView nonecommon_tittle_tv;
    }
}
