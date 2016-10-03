package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.NewsSpecialTopicDatas;
import com.squareup.picasso.Picasso;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class NewsSpecialTopicAdapter extends AbsBaseAdapter<NewsSpecialTopicDatas.DataBean.TopicListBean> implements StickyListHeadersAdapter  {

   // private List<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean> listBeen;
    private List<List<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean>> lists;
    public NewsSpecialTopicAdapter(Context context, List<NewsSpecialTopicDatas.DataBean.TopicListBean> data, List<List<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean>> lists) {
        super(context, data);
        this.lists = lists;
    }

//    @Override
//    public int getCount() {
//        return getData().size();
//    }

//    @Override
//    public Object getItem(int position) {
//        return getData().get(position);
//    }

//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        for (int i =0 ;i<lists.get(position).size();i++){
            ViewHolderNoneCommon noneCommon = null;
            if (convertView == null) {
                noneCommon = new ViewHolderNoneCommon();
                convertView = inflater.inflate(R.layout.news_pull_list_item_nonecommon, parent, false);
                noneCommon.nonecommon_iv = (ImageView) convertView.findViewById(R.id.news_item_nonecommon_iv);
                noneCommon.nonecommon_tittle_tv = (TextView) convertView.findViewById(R.id.news_item_nonecommon_tittle_tv);
                convertView.setTag(noneCommon);
            } else {
                noneCommon = (ViewHolderNoneCommon) convertView.getTag();
            }

            noneCommon.nonecommon_tittle_tv.setText(lists.get(position).get(i).getItemTitle());
            Picasso.with(this.context).load(lists.get(position).get(i).getItemImage().getImgUrl1())
                    .into(noneCommon.nonecommon_iv);
        }
//            noneCommon.nonecommon_tittle_tv.setText(getData().get(position).getList().get(position).getItemTitle());
//            Picasso.with(this.context).load(getData().get(position).getList().get(position).getItemImage().getImgUrl1())
//                    .into(noneCommon.nonecommon_iv);

        return convertView;
    }



    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        ViewHolderHeader holderHeader = null;
        if (convertView == null){
            holderHeader = new ViewHolderHeader();
            convertView = inflater.inflate(R.layout.special_topic_item_header,parent,false);
            holderHeader.left_tv = (TextView) convertView.findViewById(R.id.topic_item_headerleft_tv);
            holderHeader.right_tv = (TextView) convertView.findViewById(R.id.topic_item_headerright_tv);
            convertView.setTag(holderHeader);
        }else {
            holderHeader = (ViewHolderHeader) convertView.getTag();
        }
        holderHeader.left_tv.setText(getData().get(position).getForumName());
        return convertView;
    }

    @Override
    public long getHeaderId(int postion) {
        return postion;
    }




    /**
     * 适配news_pull_list_item_nonecommon.xml
     * 专题
     */
    public static class ViewHolderNoneCommon {
        ImageView nonecommon_iv;
        TextView nonecommon_tittle_tv;
    }
    /**
     * 适配special_topic_item_header.xml
     * 专题
     */
    public static class ViewHolderHeader {
        TextView right_tv;
        TextView left_tv;
    }
}
