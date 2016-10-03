package com.cctvnews.www.model.mmodel;

import java.util.List;

/**
 * Created by Administrator on 2016/7/24 0024.
 */
public class LivingDatas {
    /**
     * msgCount : 18
     * refresh : 15
     * detailUrl : http://hot.news.cntv.cn/api/list/liveMessageList?id=INTE1469263537430985
     * liveDesc :
     * liveId : INTE1469263537430985
     * liveImage : http://p1.img.cctvpic.com/photoworkspace/2016/07/24/2016072414522011718.jpg
     * liveTitle : 成都论剑！G20财金掌门人“把脉”全球经济
     * liveType : 1
     * liveUrl :
     * liveState : 0
     * liveDate : 1469263537000
     * width : 640
     * height : 360
     * liveRate : 1
     */

    private DataBean data;
    /**
     * detailUrl : http://m.news.cntv.cn/c/art/index.shtml?id=ARTIWuJufGuFA92x8dvS9ME7160724
     * msgTitle :
     * videoImg :
     * videoUrl :
     * rwName : 央视新闻客户端
     * msgTime : 1469343314000
     * type : 0
     * msg : 【G20财长和央行行长会公报发布】2016年第三次G20财长和央行行长会议于7月23-24日在成都召开。以下为G20财长和央行行长会公报内容全文↓↓↓
     * imgList : []
     * msgId : 1469345292469
     * liveUrl :
     * rwImg : http://p1.img.cctvpic.com/photoworkspace/2016/07/23/2016072316455269920.jpg
     * urlDesc : 点击看公报全文
     * msgImage :
     */

    private List<MsgListBean> msgList;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<MsgListBean> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<MsgListBean> msgList) {
        this.msgList = msgList;
    }

    public static class DataBean {
        private String msgCount;
        private String refresh;
        private String detailUrl;
        private String liveDesc;
        private String liveId;
        private String liveImage;
        private String liveTitle;
        private String liveType;
        private String liveUrl;
        private String liveState;
        private String liveDate;
        private String width;
        private String height;
        private String liveRate;

        public String getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(String msgCount) {
            this.msgCount = msgCount;
        }

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getLiveDesc() {
            return liveDesc;
        }

        public void setLiveDesc(String liveDesc) {
            this.liveDesc = liveDesc;
        }

        public String getLiveId() {
            return liveId;
        }

        public void setLiveId(String liveId) {
            this.liveId = liveId;
        }

        public String getLiveImage() {
            return liveImage;
        }

        public void setLiveImage(String liveImage) {
            this.liveImage = liveImage;
        }

        public String getLiveTitle() {
            return liveTitle;
        }

        public void setLiveTitle(String liveTitle) {
            this.liveTitle = liveTitle;
        }

        public String getLiveType() {
            return liveType;
        }

        public void setLiveType(String liveType) {
            this.liveType = liveType;
        }

        public String getLiveUrl() {
            return liveUrl;
        }

        public void setLiveUrl(String liveUrl) {
            this.liveUrl = liveUrl;
        }

        public String getLiveState() {
            return liveState;
        }

        public void setLiveState(String liveState) {
            this.liveState = liveState;
        }

        public String getLiveDate() {
            return liveDate;
        }

        public void setLiveDate(String liveDate) {
            this.liveDate = liveDate;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getLiveRate() {
            return liveRate;
        }

        public void setLiveRate(String liveRate) {
            this.liveRate = liveRate;
        }
    }

    public static class MsgListBean {
        private String detailUrl;
        private String msgTitle;
        private String videoImg;
        private String videoUrl;
        private String rwName;
        private long msgTime;
        private String type;
        private String msg;
        private String msgId;
        private String liveUrl;
        private String rwImg;
        private String urlDesc;
        private String msgImage;
        private List<?> imgList;

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getMsgTitle() {
            return msgTitle;
        }

        public void setMsgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
        }

        public String getVideoImg() {
            return videoImg;
        }

        public void setVideoImg(String videoImg) {
            this.videoImg = videoImg;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getRwName() {
            return rwName;
        }

        public void setRwName(String rwName) {
            this.rwName = rwName;
        }

        public long getMsgTime() {
            return msgTime;
        }

        public void setMsgTime(long msgTime) {
            this.msgTime = msgTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public String getLiveUrl() {
            return liveUrl;
        }

        public void setLiveUrl(String liveUrl) {
            this.liveUrl = liveUrl;
        }

        public String getRwImg() {
            return rwImg;
        }

        public void setRwImg(String rwImg) {
            this.rwImg = rwImg;
        }

        public String getUrlDesc() {
            return urlDesc;
        }

        public void setUrlDesc(String urlDesc) {
            this.urlDesc = urlDesc;
        }

        public String getMsgImage() {
            return msgImage;
        }

        public void setMsgImage(String msgImage) {
            this.msgImage = msgImage;
        }

        public List<?> getImgList() {
            return imgList;
        }

        public void setImgList(List<?> imgList) {
            this.imgList = imgList;
        }
    }
}
