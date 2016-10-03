package com.cctvnews.www.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 封装Adapter
 *
 * @param <T>
 * @author arvin
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {

    private List<T> data;
    protected Context context;
    protected LayoutInflater inflater;

    public AbsBaseAdapter(Context context, List<T> data) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }


    public List<T> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;

    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView,
                                 ViewGroup parent);

}
