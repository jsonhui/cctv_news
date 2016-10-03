package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class HeaderViewAdapter extends AbsBaseAdapter<NewsItem.DataBean.BigImgBean> {
    public HeaderViewAdapter(Context context, List<NewsItem.DataBean.BigImgBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderImage viewHolderImage;
        if (convertView == null) {
            viewHolderImage = new ViewHolderImage();
            convertView = inflater.inflate(R.layout.news_pull_list_item_image, parent, false);
            viewHolderImage.big_image_iv = (ImageView) convertView.findViewById(R.id.new_item_image_iv);
            viewHolderImage.big_image_tv = (TextView) convertView.findViewById(R.id.new_item_image_tv);
            convertView.setTag(viewHolderImage);
        } else {
            viewHolderImage = (ViewHolderImage) convertView.getTag();
        }

        viewHolderImage.big_image_tv.setText(getData().get(position).getItemTitle());
        Picasso.with(this.context).load(getData().get(position).getItemImage())
                .into(viewHolderImage.big_image_iv);


        return convertView;
    }

    /**
     * 适配newsitem_big_image.xml
     * 专题
     */
    class ViewHolderImage {
        ImageView big_image_iv;
        TextView big_image_tv;
    }
}


