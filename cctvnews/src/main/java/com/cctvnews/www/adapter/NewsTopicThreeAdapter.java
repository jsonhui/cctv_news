package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.NewsTopicThreeDatas;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class NewsTopicThreeAdapter extends BaseAdapter {
    private List<NewsTopicThreeDatas.DataBean.ListBean> listBeen ;
    private Context mContext;
    private LayoutInflater inflater;

    public NewsTopicThreeAdapter(List<NewsTopicThreeDatas.DataBean.ListBean> listBeen, Context mContext) {
        this.listBeen = listBeen;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderNoneCommon noneCommon  = null;
        if (convertView == null){
            noneCommon = new ViewHolderNoneCommon();
            convertView = inflater.inflate(R.layout.news_pull_list_item_nonecommon,parent,false);
            noneCommon.nonecommon_iv = (ImageView) convertView.findViewById(R.id.news_item_nonecommon_iv);
            noneCommon.nonecommon_tittle_tv = (TextView) convertView.findViewById(R.id.news_item_nonecommon_tittle_tv);
            convertView.setTag(noneCommon);
        }else {
            noneCommon = (ViewHolderNoneCommon) convertView.getTag();
        }
        noneCommon.nonecommon_tittle_tv.setText(listBeen.get(position).getItemTitle());
        Picasso.with(mContext).load(listBeen.get(position).getItemImage().getImgUrl1())
                .into(noneCommon.nonecommon_iv);

        return convertView;
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
