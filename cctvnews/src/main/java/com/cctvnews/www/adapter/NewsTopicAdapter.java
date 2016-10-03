package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.NewsSpecialTopicDatas;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class NewsTopicAdapter extends BaseExpandableListAdapter  {
    private Context mcontext;
    private LayoutInflater inflater;
    private List<NewsSpecialTopicDatas.DataBean.TopicListBean> topicListBeen;

    /**父布局的集合*/
    List<String> groupData;
    /**子布局的集合*/
    private Map<String,List<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean>> itemMap;

    public NewsTopicAdapter(Context mcontext, List<String> groupData, Map<String, List<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean>> itemMap) {
        this.mcontext = mcontext;
        this.groupData = groupData;
        this.itemMap = itemMap;
        inflater = LayoutInflater.from(mcontext);
    }

    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = groupData.get(groupPosition);
        List<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean> listBeen = itemMap.get(key);
        return listBeen.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = groupData.get(groupPosition);
        List<NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean> listBeen = itemMap.get(key);
        return listBeen.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
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
        if (groupData.size() != 0){
            String name = groupData.get(groupPosition);
            holderHeader.left_tv.setText(name);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
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
        NewsSpecialTopicDatas.DataBean.TopicListBean.ListBean bean = itemMap.get(groupData.get(groupPosition)).get(childPosition);
        noneCommon.nonecommon_tittle_tv.setText(bean.getItemTitle());
        Picasso.with(mcontext).load(bean.getItemImage().getImgUrl1())
                .into(noneCommon.nonecommon_iv);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
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
