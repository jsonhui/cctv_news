package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.TimeChain;
import com.cctvnews.www.tool.commontool.HttpUtils;
import com.cctvnews.www.tool.commontool.RelativeDateformat;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Json on 2016/7/12 14:59
 * 邮箱：320175912@qq.com
 */
public class TimeChainAdapter extends AbsBaseAdapter<Object> {

    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;

    public TimeChainAdapter(Context context, List<Object> data) {
        super(context, data);
    }

    //获得布局类型的个数
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    //得到布局的类型
    @Override
    public int getItemViewType(int position) {
        Object obj = getData().get(position);
        return obj instanceof TimeChain.ItemListBean ? TYPE_TWO : TYPE_ONE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获得布局类型
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_ONE:
                OneViewHoder oneViewHoder;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.time_chain_pull_list_item_text, parent, false);
                    oneViewHoder = new OneViewHoder(convertView);
                } else {
                    oneViewHoder = (OneViewHoder) convertView.getTag();
                }
                /**右边时间**/
                String date = getData().get(position).toString();
                oneViewHoder.timeTV.setText(date);
                /**左边时间**/
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 yyyy年");
                    Date tempDate = sdf.parse(date);
                    oneViewHoder.dayTV.setText(RelativeDateformat.formatDay(tempDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case TYPE_TWO:
                TwoViewHoder twoViewHoder;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.time_chain_pull_list_item_layout, parent, false);
                    twoViewHoder = new TwoViewHoder(convertView);
                } else {
                    twoViewHoder = (TwoViewHoder) convertView.getTag();
                }
                TimeChain.ItemListBean itemListBean = (TimeChain.ItemListBean) getData().get(position);
                if (itemListBean != null && itemListBean.getItemImage() != null) {
                    /**设置图片**/
                    if ("".equals(itemListBean.getItemImage().getImgUrl1())) {
                        twoViewHoder.imageView.setVisibility(View.GONE);
                    } else {
                        Picasso.with(context).load(itemListBean.getItemImage().getImgUrl1()).into(twoViewHoder.imageView);
                    }
                    /**设置时间**/
                    long longTime = Long.parseLong(itemListBean.getPubDate());
                    Date longDate = new Date(longTime);
                    if ("今天".equals(RelativeDateformat.formatDay(longDate))) {
                        String stringTime = RelativeDateformat.format(longDate);
                        twoViewHoder.timeTV.setText(stringTime);
                    } else {
                        twoViewHoder.timeTV.setText(HttpUtils.toHM(longTime));
                    }
                    /**设置内容**/
                    twoViewHoder.contentTV.setText(itemListBean.getItemTitle());
                    break;
                }
        }
        return convertView;
    }

    static class TwoViewHoder {
        @BindView(R.id.pull_list_item_time)
        TextView timeTV;
        @BindView(R.id.pull_list_item_content)
        TextView contentTV;
        @BindView(R.id.pull_list_item_iv)
        ImageView imageView;

        public TwoViewHoder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }

    static class OneViewHoder {
        @BindView(R.id.pull_list_item_text_left)
        TextView dayTV;
        @BindView(R.id.pull_list_item_text_right)
        TextView timeTV;

        public OneViewHoder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
