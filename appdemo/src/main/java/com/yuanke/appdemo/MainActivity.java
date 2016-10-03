package com.yuanke.appdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> strings = new ArrayList<>();
    private ListView list;
    private MyAdapter adapter;
    private SharedPreferences sharedPreferences, sharedPreferences1;
    private SharedPreferences.Editor edit, edit1;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sharedPreferences = this.getSharedPreferences("预约", MODE_PRIVATE);
        sharedPreferences1 = this.getSharedPreferences("直播", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        edit1 = sharedPreferences1.edit();
        list = (ListView) findViewById(R.id.test_list);
        strings.add("0");
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("8");
        strings.add("12");
        strings.add("14");
        strings.add("15");
        strings.add("16");
        strings.add("17");
        strings.add("18");
        strings.add("19");
        strings.add("20");
        strings.add("24");
        strings.add("25");
        strings.add("26");
        strings.add("27");
        strings.add("28");
        strings.add("29");
        strings.add("30");
        adapter = new MyAdapter(this, strings);
        list.setAdapter(adapter);
    }

    private class MyAdapter extends AbsBaseAdapter<String> {
        private MyAdapter mContext = this;

        public MyAdapter(Context context, List<String> data) {
            super(context, data);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Log.i("jason", parent + "---" + position);
            final ViewHoder hoder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item, parent, false);
                hoder = new ViewHoder();
                hoder.tv = (TextView) convertView.findViewById(R.id.test_tv);
                hoder.btn = (Button) convertView.findViewById(R.id.test_btn);
                convertView.setTag(hoder);
            } else {
                hoder = (ViewHoder) convertView.getTag();
            }
            hoder.tv.setText(getData().get(position));
            if (Integer.parseInt(getData().get(position).toString()) < 12) {
                if (sharedPreferences1.getInt("flag1", -1) == position) {
                    hoder.btn.setText("播放中");
                    hoder.btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    hoder.btn.setText("回看");
                    hoder.btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            } else if (Integer.parseInt(getData().get(position).toString()) == 12) {
                if (sharedPreferences1.getInt("flag1", -1) == position) {
                    hoder.btn.setText("播放中");
                    hoder.btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    hoder.btn.setText("直播");
                    hoder.btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            } else {
                if (sharedPreferences.getInt("flag" + position, -1) == position) {
                    hoder.btn.setText("已预约");
                    hoder.btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                } else {
                    hoder.btn.setText("预约");
                    hoder.btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
            hoder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("预约".equals(hoder.btn.getText().toString())) {
                        edit.putInt("flag" + position, position);
                        edit.commit();
                        hoder.btn.setText("已预约");
                        hoder.btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        Toast.makeText(context, "预约成功", Toast.LENGTH_SHORT).show();
                    } else if ("已预约".equals(hoder.btn.getText().toString())) {
                        hoder.btn.setText("预约");
                        hoder.btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        Toast.makeText(context, "取消预约", Toast.LENGTH_SHORT).show();
                        edit.putInt("flag" + position, -2);
                        edit.commit();
                    } else if ("回看".equals(hoder.btn.getText().toString()) || "直播".equals(hoder.btn.getText().toString())) {
                        hoder.btn.setText("播放中");
                        hoder.btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        edit1.putInt("flag1", position);
                        edit1.commit();
                        mContext.notifyDataSetChanged();
                    }
                }
            });
            return convertView;
        }

        class ViewHoder {
            TextView tv;
            Button btn;
        }
    }
}