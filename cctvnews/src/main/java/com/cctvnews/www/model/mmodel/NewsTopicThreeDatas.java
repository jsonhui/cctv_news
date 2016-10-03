package com.cctvnews.www.model.mmodel;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class NewsTopicThreeDatas {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pubDate : 1468890225000
         * detailUrl : http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIjvVk3O1oJdjbdA6pILOz160719&isfromapp=1
         * topicid : PAGEZ9sFgAVwlJErBvAcFvnF160704
         * topictitle : 首页
         * itemID : ARTIjvVk3O1oJdjbdA6pILOz160719
         * forumid : TDAT1467623423005250
         * tagDesc :
         * allow_praise : 1
         * forumtitle : 聚焦仲裁案
         * itemType : article_flag
         * videoPlayID :
         * photoCount : 0
         * imageNum : 1
         * commentNum :
         * allow_share : 1
         * num : 1
         * videoLength :
         * allow_comment : 1
         * itemBrief :
         * itemTitle : 菲律宾总统拟派特使访华 中方回应
         * brief :
         * itemImage : {"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/07/19/2016071909030193880.jpg","imgUrl3":"","imgUrl2":""}
         * tagColor :
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String pubDate;
            private String detailUrl;
            private String topicid;
            private String topictitle;
            private String itemID;
            private String forumid;
            private String tagDesc;
            private String allow_praise;
            private String forumtitle;
            private String itemType;
            private String videoPlayID;
            private String photoCount;
            private String imageNum;
            private String commentNum;
            private String allow_share;
            private String num;
            private String videoLength;
            private String allow_comment;
            private String itemBrief;
            private String itemTitle;
            private String brief;
            /**
             * imgUrl1 : http://p1.img.cctvpic.com/photoworkspace/2016/07/19/2016071909030193880.jpg
             * imgUrl3 :
             * imgUrl2 :
             */

            private ItemImageBean itemImage;
            private String tagColor;

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(String detailUrl) {
                this.detailUrl = detailUrl;
            }

            public String getTopicid() {
                return topicid;
            }

            public void setTopicid(String topicid) {
                this.topicid = topicid;
            }

            public String getTopictitle() {
                return topictitle;
            }

            public void setTopictitle(String topictitle) {
                this.topictitle = topictitle;
            }

            public String getItemID() {
                return itemID;
            }

            public void setItemID(String itemID) {
                this.itemID = itemID;
            }

            public String getForumid() {
                return forumid;
            }

            public void setForumid(String forumid) {
                this.forumid = forumid;
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

            public String getForumtitle() {
                return forumtitle;
            }

            public void setForumtitle(String forumtitle) {
                this.forumtitle = forumtitle;
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

            public String getImageNum() {
                return imageNum;
            }

            public void setImageNum(String imageNum) {
                this.imageNum = imageNum;
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

            public String getItemBrief() {
                return itemBrief;
            }

            public void setItemBrief(String itemBrief) {
                this.itemBrief = itemBrief;
            }

            public String getItemTitle() {
                return itemTitle;
            }

            public void setItemTitle(String itemTitle) {
                this.itemTitle = itemTitle;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public ItemImageBean getItemImage() {
                return itemImage;
            }

            public void setItemImage(ItemImageBean itemImage) {
                this.itemImage = itemImage;
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
    }
}
