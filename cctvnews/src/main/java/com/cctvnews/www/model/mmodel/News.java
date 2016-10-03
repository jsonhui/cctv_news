package com.cctvnews.www.model.mmodel;

import java.util.List;

/**
 * 作者：Json on 2016/7/12 14:04
 * 邮箱：320175912@qq.com
 */
public class News {
    /**
     * title : 要闻
     * identify :
     * url : http://hot.news.cntv.cn/index.php?controller=list&action=getHandDataInfoNew&handdata_id=TDAT1372928688333145&n1=3&n2=20&toutuNum=3
     * isShow : 1
     * iosImgUrl : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2014/3/17/1395024170876_519.png
     * androidImgUrl : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2014/3/17/1395024311351_411.png
     * itemType : news_flag
     * order : 1
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String identify;
        private String url;
        private String isShow;
        private String iosImgUrl;
        private String androidImgUrl;
        private String itemType;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIdentify() {
            return identify;
        }

        public void setIdentify(String identify) {
            this.identify = identify;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getIosImgUrl() {
            return iosImgUrl;
        }

        public void setIosImgUrl(String iosImgUrl) {
            this.iosImgUrl = iosImgUrl;
        }

        public String getAndroidImgUrl() {
            return androidImgUrl;
        }

        public void setAndroidImgUrl(String androidImgUrl) {
            this.androidImgUrl = androidImgUrl;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
