package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.model.mmodel.DialogDatas;
import com.cctvnews.www.tool.commontool.getStandardDate;

import java.util.List;


/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class DialogAdapter extends BaseAdapter {
    private List<DialogDatas.DataBean.ContentBean.InfoBean> infoBeen ;
    private LayoutInflater inflater;
    private Context mContext;

    public DialogAdapter(List<DialogDatas.DataBean.ContentBean.InfoBean> infoBeen, Context mContext) {
        this.infoBeen = infoBeen;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return infoBeen != null ? infoBeen.size():0;
    }

    @Override
    public Object getItem(int position) {
        return infoBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderDialog dialog = null;
        if (convertView == null){
            dialog = new ViewHolderDialog();
            convertView = inflater.inflate(R.layout.dialog_item_pull_list,parent,false);
            dialog.name_tv = (TextView) convertView.findViewById(R.id.dialog_item_name_tv);
            dialog.content_tv = (TextView) convertView.findViewById(R.id.dialog_item_content);
            dialog.time_tv= (TextView) convertView.findViewById(R.id.dialog_item_time);
            convertView.setTag(dialog);
        }else {
            dialog = (ViewHolderDialog) convertView.getTag();
        }
        dialog.name_tv.setText(infoBeen.get(position).getAuthor());
        dialog.content_tv.setText(infoBeen.get(position).getMessage());

        String time = getStandardDate.getStandardDate(infoBeen.get(position).getDateline());
        dialog.time_tv.setText(time);
        return convertView;
    }

    public static class ViewHolderDialog{
        TextView name_tv,content_tv,time_tv;
    }
}
