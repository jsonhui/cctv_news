package com.cctvnews.www.model.mmodel;

import java.util.List;

/**
 * 作者：Json on 2016/7/18 11:23
 * 邮箱：320175912@qq.com
 */
public class DiscoverDatas {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String listUrl;

        private List<ItemListBean> itemList;

        private List<BigImgBean> bigImg;

        public String getListUrl() {
            return listUrl;
        }

        public void setListUrl(String listUrl) {
            this.listUrl = listUrl;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public List<BigImgBean> getBigImg() {
            return bigImg;
        }

        public void setBigImg(List<BigImgBean> bigImg) {
            this.bigImg = bigImg;
        }

        public static class ItemListBean {
            private String pubDate;
            private String detailUrl;
            private String itemID;
            private String tagDesc;
            private String allow_praise;
            private String itemType;
            private String videoPlayID;
            private String photoCount;
            private String imageNum;
            private String commentNum;
            private String allow_share;
            private String num;
            private String operate_time;
            private String videoLength;
            private String allow_comment;
            private String itemBrief;
            private String itemTitle;
            private String brief;
            /**
             * imgUrl1 : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2016/1/29/1454029154151_960.png
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

            public String getOperate_time() {
                return operate_time;
            }

            public void setOperate_time(String operate_time) {
                this.operate_time = operate_time;
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

        public static class BigImgBean {
            private String detailUrl;
            private String pubDate;
            private String itemID;
            private String tagDesc;
            private String allow_praise;
            private String itemType;
            private String videoPlayID;
            private String photoCount;
            private String allow_share;
            private String num;
            private String operate_time;
            private String videoLength;
            private String allow_comment;
            private String itemBrief;
            private String itemTitle;
            private String itemImage;
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

            public String getOperate_time() {
                return operate_time;
            }

            public void setOperate_time(String operate_time) {
                this.operate_time = operate_time;
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

            public String getItemImage() {
                return itemImage;
            }

            public void setItemImage(String itemImage) {
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
        }
    }
}
