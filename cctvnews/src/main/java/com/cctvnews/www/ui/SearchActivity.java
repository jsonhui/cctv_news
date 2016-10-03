package com.cctvnews.www.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cctvnews.www.R;
import com.cctvnews.www.adapter.SearchAdapter;
import com.cctvnews.www.adapter.SearchGridAdapter;
import com.cctvnews.www.config.URL;
import com.cctvnews.www.model.mmodel.SearchByString;
import com.cctvnews.www.model.mmodel.SearchData;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.logtool.LogTool;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends UMActivity {
    private List<SearchByString.ItemsBean> itemsBeen = new ArrayList<>();
    @BindView(R.id.search_back_iv)
    ImageView backImageView;
    @BindView(R.id.search_edit)
    EditText contentEdit;
    @BindView(R.id.search_err)
    ImageView errImageView;
    @BindView(R.id.search_iv)
    ImageView searchImageView;
    @BindView(R.id.search_grid_view)
    GridView searchGrid;
    @BindView(R.id.search_change_tv)
    TextView changeTV;
    @BindView(R.id.search_list_view)
    ListView listView;
    private List<SearchData> searchDatas = new ArrayList<>();
    private List<SearchData> data = new ArrayList<>();
    private SearchGridAdapter searchGridAdapter;
    private int flag = 0;
    private SearchAdapter searchAdapter;
    private String decode;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        /**Intent来的数据**/
        String path = getIntent().getStringExtra("searchData");
        LogTool.log(this.getClass(), path);
        /**适配器**/
        searchGridAdapter = new SearchGridAdapter(getBaseContext(), data);
        searchAdapter = new SearchAdapter(getBaseContext(), itemsBeen);
        /**绑定适配器**/
        searchGrid.setAdapter(searchGridAdapter);
        listView.setAdapter(searchAdapter);
        /**数据**/
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    LogTool.log(this.getClass(), jsonArray.length() + "");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        searchDatas.add(new SearchData((String) jsonObject.get("title"), (String) jsonObject.get("count")));
                    }
                    /**初始数据begin**/
                    data.clear();
                    for (int i = flag; i < flag + 10; i++) {
                        data.add(searchDatas.get(i));
                    }
                    flag = flag + data.size();
                    searchGridAdapter.notifyDataSetChanged();
                    /**初始数据end**/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        /*****************监听*****************/
        /**返回键的监听**/
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**Edit的监听**/
        contentEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    /**TODO 搜索**/
                    searchGrid.setVisibility(View.INVISIBLE);
                    changeTV.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    try {
                        decode = URLEncoder.encode(contentEdit.getText().toString(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    LogTool.log(getClass(), URL.SEARCH_BY_STRING
                            + decode);
                    OkHttpTool.newInstance().start(URL.SEARCH_BY_STRING
                            + decode)
                            .callback(new IOKCallBack() {
                                @Override
                                public void success(String result) {
                                    Gson gson = new Gson();
                                    SearchByString searchByString = gson.fromJson(result, SearchByString.class);
                                    List<SearchByString.ItemsBean> been = searchByString.getItems();
                                    searchAdapter.getData().clear();
                                    searchAdapter.getData().addAll(been);
                                    searchAdapter.notifyDataSetChanged();
                                }
                            });
                }
                return false;
            }
        });
        /**x键的监听**/
        errImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentEdit.setText("");
                if (listView.getVisibility() == View.VISIBLE) {
                    listView.setVisibility(View.INVISIBLE);
                    searchGrid.setVisibility(View.VISIBLE);
                    changeTV.setVisibility(View.VISIBLE);
                }
            }
        });
        /**change key world的监听**/
        changeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag) {
                    case 0:
                        data.clear();
                        for (int i = flag; i < flag + 10; i++) {
                            data.add(searchDatas.get(i));
                        }
                        flag = flag + data.size();
                        searchGridAdapter.notifyDataSetChanged();
                        break;
                    case 10:
                        data.clear();
                        for (int i = flag; i < flag + 10; i++) {
                            data.add(searchDatas.get(i));
                        }
                        flag = flag + data.size();
                        searchGridAdapter.notifyDataSetChanged();
                        break;
                    default:
                        data.clear();
                        for (int i = flag; i < searchDatas.size(); i++) {
                            data.add(searchDatas.get(i));
                        }
                        flag = 0;
                        searchGridAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        searchGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**TODO  跳转到下一页**/
                contentEdit.setText(searchGridAdapter.getData().get(position).getTitle());
                searchGrid.setVisibility(View.INVISIBLE);
                changeTV.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
                try {
                    decode = URLEncoder.encode(searchGridAdapter.getData().get(position).getTitle(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                LogTool.log(getClass(), URL.SEARCH_BY_STRING
                        + decode);
                OkHttpTool.newInstance().start(URL.SEARCH_BY_STRING
                        + decode)
                        .callback(new IOKCallBack() {
                            @Override
                            public void success(String result) {
                                Gson gson = new Gson();
                                SearchByString searchByString = gson.fromJson(result, SearchByString.class);
                                List<SearchByString.ItemsBean> been = searchByString.getItems();
                                searchAdapter.getData().clear();
                                searchAdapter.getData().addAll(been);
                                searchAdapter.notifyDataSetChanged();
                            }
                        });
            }
        });
    }
}
