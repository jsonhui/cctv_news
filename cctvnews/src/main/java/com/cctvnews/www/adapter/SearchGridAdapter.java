package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.SearchData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Json on 2016/7/14 19:02
 * 邮箱：320175912@qq.com
 */
public class SearchGridAdapter extends AbsBaseAdapter<SearchData> {
    public SearchGridAdapter(Context context, List<SearchData> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder hoder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.search_grid_view_item, parent, false);
            hoder = new ViewHoder(convertView);
        } else {
            hoder = (ViewHoder) convertView.getTag();
        }
        hoder.contenTV.setText(getData().get(position).getTitle());
        return convertView;
    }

    static class ViewHoder {
        @BindView(R.id.search_item_content_tv)
        TextView contenTV;

        public ViewHoder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
