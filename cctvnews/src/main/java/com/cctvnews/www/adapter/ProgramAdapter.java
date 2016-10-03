package com.cctvnews.www.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cctvnews.www.R;
import com.cctvnews.www.customview.MyJCVideoPlayerStandard;
import com.cctvnews.www.model.mmodel.ProgramDatas;
import com.cctvnews.www.tool.logtool.LogTool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Json on 2016/7/16 10:34
 * 邮箱：320175912@qq.com
 */
public class ProgramAdapter extends AbsBaseAdapter<ProgramDatas.Cctv13Bean.ProgramBean> {
    public static ArrayList<ProgramAdapter> ALL_INSTANTS = new ArrayList();
    private MyJCVideoPlayerStandard jiecaoView;
    private SharedPreferences sharedPreferences, sharedPreferences1;
    private SharedPreferences.Editor edit, edit1;
    private ProgramAdapter mContext = this;
    private String mark;

    public ProgramAdapter(Context context, List<ProgramDatas.Cctv13Bean.ProgramBean> data, String mark, SharedPreferences sharedPreferences, SharedPreferences sharedPreferences1, MyJCVideoPlayerStandard jiecaoView) {
        super(context, data);
        this.mark = mark;
        this.sharedPreferences = sharedPreferences;
        this.sharedPreferences1 = sharedPreferences1;
        edit = this.sharedPreferences.edit();
        edit1 = this.sharedPreferences1.edit();
        this.jiecaoView = jiecaoView;
        LogTool.log(getClass(), mContext.getClass().toString());
        ALL_INSTANTS.add(this);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHoder hoder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.program_item, parent, false);
            hoder = new ViewHoder(convertView);
        } else {
            hoder = (ViewHoder) convertView.getTag();
        }
        hoder.timeTV.setText(getData().get(position).getShowTime());
        hoder.contenTV.setText(getData().get(position).getT());
        long nowTime = new Date().getTime() / 1000;
        LogTool.log(getClass(), nowTime + "");
        if (nowTime <= getData().get(position).getEt() && nowTime >= getData().get(position).getSt()) {
            if (sharedPreferences1.getInt(mark + "flag1", -1) == position) {
                hoder.functionBTN.setText("播放中");
                hoder.functionBTN.setBackgroundColor(context.getResources().getColor(R.color.gray));
            } else {
                hoder.functionBTN.setText("直播");
                hoder.functionBTN.setBackgroundColor(context.getResources().getColor(R.color.live));
            }
        } else if (nowTime < getData().get(position).getSt()) {
            if (sharedPreferences.getInt(mark + "flag" + position, -1) == position) {
                hoder.functionBTN.setText("已预约");
                hoder.functionBTN.setBackgroundColor(context.getResources().getColor(R.color.order));
            } else {
                hoder.functionBTN.setText("预约");
                hoder.functionBTN.setBackgroundColor(context.getResources().getColor(R.color.order));
            }
        } else {
            if (sharedPreferences1.getInt(mark + "flag1", -1) == position) {
                hoder.functionBTN.setText("播放中");
                hoder.functionBTN.setBackgroundColor(context.getResources().getColor(R.color.gray));
            } else {
                hoder.functionBTN.setText("回看");
                hoder.functionBTN.setBackgroundColor(context.getResources().getColor(R.color.blue));
            }
        }
        hoder.functionBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("预约".equals(hoder.functionBTN.getText().toString())) {
                    edit.putInt(mark + "flag" + position, position);
                    edit.commit();
                    hoder.functionBTN.setText("已预约");
                    hoder.functionBTN.setBackgroundColor(context.getResources().getColor(R.color.order));
                    Toast.makeText(context, "预约成功", Toast.LENGTH_SHORT).show();
                } else if ("已预约".equals(hoder.functionBTN.getText().toString())) {
                    hoder.functionBTN.setText("预约");
                    hoder.functionBTN.setBackgroundColor(context.getResources().getColor(R.color.order));
                    Toast.makeText(context, "取消预约", Toast.LENGTH_SHORT).show();
                    edit.putInt(mark + "flag" + position, -2);
                    edit.commit();
                } else if ("回看".equals(hoder.functionBTN.getText().toString()) || "直播".equals(hoder.functionBTN.getText().toString())) {
                    hoder.functionBTN.setText("播放中");
                    hoder.functionBTN.setBackgroundColor(context.getResources().getColor(R.color.gray));
                    /**设置网络地址**/
                    if (jiecaoView != null) {
                        jiecaoView.setUp("http://i.snssdk.com/neihan/video/playback/?video_id=8d58f72376a54c5b913a62b4c777c824&quality=480p&line=0&is_gif=0", "地址加密");
                    }
                    /**播放视屏**/
                    jiecaoView.startButton.performClick();
                    /**清空所有的直播按钮选项刷新所有适配器**/
                    edit1.clear();
                    edit1.putInt(mark + "flag1", position);
                    edit1.commit();
                    mContext.notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    static class ViewHoder {
        @BindView(R.id.program_item_time_tv)
        TextView timeTV;
        @BindView(R.id.program_item_content_tv)
        TextView contenTV;
        @BindView(R.id.program_item_function_btn)
        Button functionBTN;

        public ViewHoder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }

}
