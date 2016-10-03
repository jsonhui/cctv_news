package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.DiscoverDatas;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Json on 2016/7/18 14:51
 * 邮箱：320175912@qq.com
 */
public class TwoWayGridAdapter extends AbsBaseAdapter<DiscoverDatas.DataBean.ItemListBean> {
    public TwoWayGridAdapter(Context context, List<DiscoverDatas.DataBean.ItemListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder hoder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.discover_server_item, parent, false);
            hoder = new ViewHoder(convertView);
        } else {
            hoder = (ViewHoder) convertView.getTag();
        }
        Picasso.with(context).load(getData().get(position).getItemImage().getImgUrl1()).into(hoder.serverIM);
        hoder.serverTV.setText(getData().get(position).getItemTitle());
        return convertView;
    }

    static class ViewHoder {
        @BindView(R.id.discover_server_im)
        ImageView serverIM;
        @BindView(R.id.discover_server_tv)
        TextView serverTV;

        public ViewHoder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
