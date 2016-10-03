package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.SearchByString;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Json on 2016/7/18 18:48
 * 邮箱：320175912@qq.com
 */
public class SearchAdapter extends AbsBaseAdapter<SearchByString.ItemsBean> {
    public SearchAdapter(Context context, List<SearchByString.ItemsBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder hoder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.news_pull_list_item_common, parent, false);
            hoder = new ViewHoder(convertView);
        } else {
            hoder = (ViewHoder) convertView.getTag();
        }
        if (getData().get(position).getItemImage() != null &&
                !"".equals(getData().get(position).getItemImage().getImgUrl1())) {
            Picasso.with(context)
                    .load(getData().get(position).getItemImage().getImgUrl1())
                    .placeholder(R.drawable.bg_default_item)
                    .error(R.drawable.bg_default_item)
                    .into(hoder.commonIV);
        } else {
            hoder.commonIV.setVisibility(View.GONE);
        }
        hoder.commonTV.setText(getData().get(position).getKeywords());
        if ("article_flag".equals(getData().get(position).getItemType())) {
            hoder.bottomTV.setVisibility(View.VISIBLE);
        } else {
            hoder.bottomTV.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    static class ViewHoder {
        @BindView(R.id.news_item_common_iv)
        ImageView commonIV;
        @BindView(R.id.news_item_common_tittle_tv)
        TextView commonTV;
        @BindView(R.id.news_item_common_bottom_tv)
        TextView bottomTV;

        public ViewHoder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
