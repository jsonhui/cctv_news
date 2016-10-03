package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.LivingDatas;
import com.cctvnews.www.tool.commontool.getStandardDate;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Administrator on 2016/7/25 0025.
 */
public class LivingAdapter extends BaseAdapter {
    private List<LivingDatas.MsgListBean> msgListBeen;
    private Context mContex;
    private LayoutInflater inflater;

    public LivingAdapter(List<LivingDatas.MsgListBean> msgListBeen, Context mContex) {
        this.msgListBeen = msgListBeen;
        this.mContex = mContex;
        inflater = LayoutInflater.from(mContex);
    }

    @Override
    public int getCount() {
        return msgListBeen != null ? msgListBeen.size():0;
    }

    @Override
    public Object getItem(int position) {
        return msgListBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LivingHodler livingHodler = null;
        if (convertView == null){
            livingHodler = new LivingHodler();
            convertView = inflater.inflate(R.layout.living_item,parent,false);
            livingHodler.time = (TextView) convertView.findViewById(R.id.living_item_time);
            livingHodler.tittle = (TextView) convertView.findViewById(R.id.living_item_tittle);
            livingHodler.content_tv = (TextView) convertView.findViewById(R.id.living_item_content);
            livingHodler.imageView = (ImageView) convertView.findViewById(R.id.living_item_iv);
            convertView.setTag(livingHodler);
        }else {
            livingHodler = (LivingHodler) convertView.getTag();
        }
        livingHodler.time.setText(getStandardDate.getStandardDate(msgListBeen.get(position).getMsgTime()/1000+""));
        livingHodler.tittle.setText(msgListBeen.get(position).getRwName());
        livingHodler.content_tv.setText(msgListBeen.get(position).getMsg());
        Picasso.with(mContex).load(msgListBeen.get(position).getRwImg()).into(livingHodler.imageView);

        return convertView;
    }

    public static class LivingHodler{
        TextView time;
        TextView tittle,content_tv;
        ImageView imageView;

    }
}
