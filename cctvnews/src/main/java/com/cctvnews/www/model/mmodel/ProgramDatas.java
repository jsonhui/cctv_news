package com.cctvnews.www.model.mmodel;

import java.util.List;

/**
 * 作者：Json on 2016/7/15 21:39
 * 邮箱：320175912@qq.com
 */
public class ProgramDatas {

    /**
     * isLive : 新闻联播（重播）
     * liveSt : 1468587600
     * channelName : CCTV-13 新闻
     * program : [{"t":"午夜新闻","st":1468512000,"et":1468515600,"showTime":"00:00","duration":3600},{"t":"新闻直播间","st":1468515600,"et":1468517400,"showTime":"01:00","duration":1800},{"t":"新闻1+1（重播）","st":1468517400,"et":1468519200,"showTime":"01:30","duration":1800},{"t":"新闻直播间","st":1468519200,"et":1468521180,"showTime":"02:00","duration":1980},{"t":"新闻1+1(重播)","st":1468521180,"et":1468522800,"showTime":"02:33","duration":1620},{"t":"新闻直播间","st":1468522800,"et":1468524600,"showTime":"03:00","duration":1800},{"t":"焦点访谈（重播）","st":1468524600,"et":1468525500,"showTime":"03:30","duration":900},{"t":"东方时空（重播）","st":1468525500,"et":1468526400,"showTime":"03:45","duration":900},{"t":"新闻直播间","st":1468526400,"et":1468528380,"showTime":"04:00","duration":1980},{"t":"新闻1+1（重播）","st":1468528380,"et":1468530000,"showTime":"04:33","duration":1620},{"t":"中菲南海争议 1","st":1468530000,"et":1468533600,"showTime":"05:00","duration":3600},{"t":"朝闻天下","st":1468533600,"et":1468544400,"showTime":"06:00","duration":10800},{"t":"新闻直播间","st":1468544400,"et":1468555200,"showTime":"09:00","duration":10800},{"t":"新闻30分","st":1468555200,"et":1468557180,"showTime":"12:00","duration":1980},{"t":"新闻直播间（特别版）","st":1468557180,"et":1468562400,"showTime":"12:33","duration":5220},{"t":"新闻直播间","st":1468562400,"et":1468576800,"showTime":"14:00","duration":14400},{"t":"共同关注","st":1468576800,"et":1468580400,"showTime":"18:00","duration":3600},{"t":"新闻联播","st":1468580400,"et":1468582200,"showTime":"19:00","duration":1800},{"t":"天气预报和联播后广告","st":1468582200,"et":1468582680,"showTime":"19:30","duration":480},{"t":"焦点访谈","st":1468582680,"et":1468584000,"showTime":"19:38","duration":1320},{"t":"东方时空","st":1468584000,"et":1468587600,"showTime":"20:00","duration":3600},{"t":"新闻联播（重播）","st":1468587600,"et":1468589400,"showTime":"21:00","duration":1800},{"t":"新闻1+1","st":1468589400,"et":1468591200,"showTime":"21:30","duration":1800},{"t":"国际时讯","st":1468591200,"et":1468593000,"showTime":"22:00","duration":1800},{"t":"环球视线","st":1468593000,"et":1468594800,"showTime":"22:30","duration":1800},{"t":"24小时","st":1468594800,"et":1468598340,"showTime":"23:00","duration":3540}]
     */

    private Cctv13Bean cctv13;

    public Cctv13Bean getCctv13() {
        return cctv13;
    }

    public void setCctv13(Cctv13Bean cctv13) {
        this.cctv13 = cctv13;
    }

    public static class Cctv13Bean {
        private String isLive;
        private int liveSt;
        private String channelName;
        /**
         * t : 午夜新闻
         * st : 1468512000
         * et : 1468515600
         * showTime : 00:00
         * duration : 3600
         */

        private List<ProgramBean> program;

        public String getIsLive() {
            return isLive;
        }

        public void setIsLive(String isLive) {
            this.isLive = isLive;
        }

        public int getLiveSt() {
            return liveSt;
        }

        public void setLiveSt(int liveSt) {
            this.liveSt = liveSt;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public List<ProgramBean> getProgram() {
            return program;
        }

        public void setProgram(List<ProgramBean> program) {
            this.program = program;
        }

        public static class ProgramBean {
            private String t;
            private int st;
            private int et;
            private String showTime;
            private int duration;

            public String getT() {
                return t;
            }

            public void setT(String t) {
                this.t = t;
            }

            public int getSt() {
                return st;
            }

            public void setSt(int st) {
                this.st = st;
            }

            public int getEt() {
                return et;
            }

            public void setEt(int et) {
                this.et = et;
            }

            public String getShowTime() {
                return showTime;
            }

            public void setShowTime(String showTime) {
                this.showTime = showTime;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }
        }
    }
}
