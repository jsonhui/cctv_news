package com.cctvnews.www.model.mmodel;

import com.cctvnews.www.handle.ICallBack;
import com.cctvnews.www.model.imodel.ITimeChain;
import com.cctvnews.www.tool.commontool.HttpUtils;
import com.cctvnews.www.tool.httptool.IOKCallBack;
import com.cctvnews.www.tool.httptool.OkHttpTool;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Json on 2016/7/12 14:05
 * 邮箱：320175912@qq.com
 */
public class TimeChain implements ITimeChain {
    private int total;
    private long serverTime;
    private List<ItemListBean> itemList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBean {
        private String detailUrl;
        private String pubDate;
        private String itemID;
        private String tagDesc;
        private String allow_praise;
        private String itemType;
        private String videoPlayID;
        private String photoCount;
        private String commentNum;
        private String allow_share;
        private String num;
        private String videoLength;
        private String allow_comment;
        private String itemTitle;
        private ItemImageBean itemImage;
        private String brief;
        private String tagColor;

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getItemID() {
            return itemID;
        }

        public void setItemID(String itemID) {
            this.itemID = itemID;
        }

        public String getTagDesc() {
            return tagDesc;
        }

        public void setTagDesc(String tagDesc) {
            this.tagDesc = tagDesc;
        }

        public String getAllow_praise() {
            return allow_praise;
        }

        public void setAllow_praise(String allow_praise) {
            this.allow_praise = allow_praise;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getVideoPlayID() {
            return videoPlayID;
        }

        public void setVideoPlayID(String videoPlayID) {
            this.videoPlayID = videoPlayID;
        }

        public String getPhotoCount() {
            return photoCount;
        }

        public void setPhotoCount(String photoCount) {
            this.photoCount = photoCount;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getAllow_share() {
            return allow_share;
        }

        public void setAllow_share(String allow_share) {
            this.allow_share = allow_share;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(String videoLength) {
            this.videoLength = videoLength;
        }

        public String getAllow_comment() {
            return allow_comment;
        }

        public void setAllow_comment(String allow_comment) {
            this.allow_comment = allow_comment;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        public ItemImageBean getItemImage() {
            return itemImage;
        }

        public void setItemImage(ItemImageBean itemImage) {
            this.itemImage = itemImage;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getTagColor() {
            return tagColor;
        }

        public void setTagColor(String tagColor) {
            this.tagColor = tagColor;
        }

        public static class ItemImageBean {
            private String imgUrl1;
            private String imgUrl3;
            private String imgUrl2;

            public String getImgUrl1() {
                return imgUrl1;
            }

            public void setImgUrl1(String imgUrl1) {
                this.imgUrl1 = imgUrl1;
            }

            public String getImgUrl3() {
                return imgUrl3;
            }

            public void setImgUrl3(String imgUrl3) {
                this.imgUrl3 = imgUrl3;
            }

            public String getImgUrl2() {
                return imgUrl2;
            }

            public void setImgUrl2(String imgUrl2) {
                this.imgUrl2 = imgUrl2;
            }
        }
    }

    @Override
    public void getTimeChainData(String path, final ICallBack iCallBack) {
        /**数据**/
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                List<Object> info = new ArrayList<>();
                Gson gson = new Gson();
                TimeChain timeChain = gson.fromJson(result, TimeChain.class);
                List<ItemListBean> timeChainData = timeChain.getItemList();
                long time = Long.parseLong(timeChainData.get(0).getPubDate());
                String data = HttpUtils.toData(time);
                info.clear();
                info.add(data);
                for (int i = 0; i < timeChainData.size(); i++) {
                    TimeChain.ItemListBean listBean = timeChainData.get(i);
                    /**判断是否一个日期**/
                    String dataTemp = HttpUtils.toData(Long.parseLong(listBean.getPubDate()));
                    if (data.equals(dataTemp)) {
                        info.add(listBean);
                    } else {
                        time = Long.parseLong(listBean.getPubDate());
                        data = HttpUtils.toData(time);
                        info.add(data);
                        info.add(listBean);
                    }
                }
                iCallBack.callData(info);
            }
        });
    }

    @Override
    public void getTimeChainMoreData(String path, final List<Object> info, final ICallBack iCallBack) {
        /**数据**/
        OkHttpTool.newInstance().start(path).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                TimeChain timeChain = gson.fromJson(result, TimeChain.class);
                List<TimeChain.ItemListBean> timeChainData = timeChain.getItemList();
                long time = Long.parseLong(timeChainData.get(0).getPubDate());
                String data = HttpUtils.toData(time);
                if (!info.contains(data)) {
                    info.add(data);
                }
                for (int i = 0; i < timeChainData.size(); i++) {
                    TimeChain.ItemListBean listBean = timeChainData.get(i);
                    /**判断是否一个日期**/
                    String dataTemp = HttpUtils.toData(Long.parseLong(listBean.getPubDate()));
                    if (data.equals(dataTemp)) {
                        info.add(listBean);
                    } else {
                        time = Long.parseLong(listBean.getPubDate());
                        data = HttpUtils.toData(time);
                        info.add(data);
                        info.add(listBean);
                    }
                }
                iCallBack.callData(info);
            }
        });
    }
}
