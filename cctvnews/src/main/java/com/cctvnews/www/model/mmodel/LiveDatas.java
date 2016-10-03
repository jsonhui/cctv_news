package com.cctvnews.www.model.mmodel;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：Json on 2016/7/17 13:13
 * 邮箱：320175912@qq.com
 */
public class LiveDatas {

    /**
     * ack : yes
     * lc : {"isp_code":"2","city_code":"WH","provice_code":"HB","country_code":"CN","ip":"113.57.170.146"}
     * client_sid : e333f466-4bdc-11e6-a680-14dda9d686db
     * flv_cdn_info : {"cdn_code":"LIVE-FLV-CDN-FW","cdn_name":"3rd快网"}
     * flv_url : {"flv1":"","flv2":"http://cctv13.vtime.cntv.cloudcdn.net:8000/live/flv/channel23?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==","flv3":"","flv4":"http://cctv13.vtime.cntv.cloudcdn.net:8000/live/no/24_/seg0/index.m3u8?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==","flv5":"http://cctv13.vtime.cntv.cloudcdn.net/cache/24_/channel.pub?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==","flv6":""}
     * hls_cdn_info : {"cdn_code":"LIVE-HLS-CDN-FW","cdn_name":"3rd快网"}
     * hls_url : {"hls1":"http://cctv13.vtime.cntv.cloudcdn.net/live/cctv13hls_/index.m3u8?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==","hls2":"http://cctv13.vtime.cntv.cloudcdn.net/live/no/23_/seg0/index.m3u8?uid=default&AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==","hls3":"","hls4":"http://cctv13.vtime.cntv.cloudcdn.net:8000/live/no/24_/seg0/index.m3u8?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==","hls5":"http://cctv13.vtime.cntv.cloudcdn.net:8000/live/pic/channel24?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==","hls6":"http://audio.live.cntv.cloudcdn.net:8000/cache/24_/seg2/index.m3u8?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg=="}
     * hds_cdn_info : {"cdn_code":"LIVE-HDS-CDN-FW","cdn_name":"3rd快网"}
     * hds_url : {"hds1":"http://cctv13.vtime.cntv.cloudcdn.net/cache/cctv13hds.f4m?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==","hds2":"http://cctv13.vtime.cntv.cloudcdn.net:8000/live/flv/channel24?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==","hds3":"","hds4":"","hds5":"","hds6":""}
     * public : 1
     */

    private String ack;
    /**
     * isp_code : 2
     * city_code : WH
     * provice_code : HB
     * country_code : CN
     * ip : 113.57.170.146
     */

    private LcBean lc;
    private String client_sid;
    /**
     * cdn_code : LIVE-FLV-CDN-FW
     * cdn_name : 3rd快网
     */

    private FlvCdnInfoBean flv_cdn_info;
    /**
     * flv1 :
     * flv2 : http://cctv13.vtime.cntv.cloudcdn.net:8000/live/flv/channel23?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     * flv3 :
     * flv4 : http://cctv13.vtime.cntv.cloudcdn.net:8000/live/no/24_/seg0/index.m3u8?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     * flv5 : http://cctv13.vtime.cntv.cloudcdn.net/cache/24_/channel.pub?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     * flv6 :
     */

    private FlvUrlBean flv_url;
    /**
     * cdn_code : LIVE-HLS-CDN-FW
     * cdn_name : 3rd快网
     */

    private HlsCdnInfoBean hls_cdn_info;
    /**
     * hls1 : http://cctv13.vtime.cntv.cloudcdn.net/live/cctv13hls_/index.m3u8?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     * hls2 : http://cctv13.vtime.cntv.cloudcdn.net/live/no/23_/seg0/index.m3u8?uid=default&AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     * hls3 :
     * hls4 : http://cctv13.vtime.cntv.cloudcdn.net:8000/live/no/24_/seg0/index.m3u8?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     * hls5 : http://cctv13.vtime.cntv.cloudcdn.net:8000/live/pic/channel24?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     * hls6 : http://audio.live.cntv.cloudcdn.net:8000/cache/24_/seg2/index.m3u8?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     */

    private HlsUrlBean hls_url;
    /**
     * cdn_code : LIVE-HDS-CDN-FW
     * cdn_name : 3rd快网
     */

    private HdsCdnInfoBean hds_cdn_info;
    /**
     * hds1 : http://cctv13.vtime.cntv.cloudcdn.net/cache/cctv13hds.f4m?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     * hds2 : http://cctv13.vtime.cntv.cloudcdn.net:8000/live/flv/channel24?AUTH=xv0Y7EK4KyjRRXCVhwKp47mWtomSpSXXrqylZzwH7+RTPk9rEOKSfUPsXDmVR5kxaMF+G1pHzL8xwtySFXe+qg==
     * hds3 :
     * hds4 :
     * hds5 :
     * hds6 :
     */

    private HdsUrlBean hds_url;
    @SerializedName("public")
    private String publicX;

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public LcBean getLc() {
        return lc;
    }

    public void setLc(LcBean lc) {
        this.lc = lc;
    }

    public String getClient_sid() {
        return client_sid;
    }

    public void setClient_sid(String client_sid) {
        this.client_sid = client_sid;
    }

    public FlvCdnInfoBean getFlv_cdn_info() {
        return flv_cdn_info;
    }

    public void setFlv_cdn_info(FlvCdnInfoBean flv_cdn_info) {
        this.flv_cdn_info = flv_cdn_info;
    }

    public FlvUrlBean getFlv_url() {
        return flv_url;
    }

    public void setFlv_url(FlvUrlBean flv_url) {
        this.flv_url = flv_url;
    }

    public HlsCdnInfoBean getHls_cdn_info() {
        return hls_cdn_info;
    }

    public void setHls_cdn_info(HlsCdnInfoBean hls_cdn_info) {
        this.hls_cdn_info = hls_cdn_info;
    }

    public HlsUrlBean getHls_url() {
        return hls_url;
    }

    public void setHls_url(HlsUrlBean hls_url) {
        this.hls_url = hls_url;
    }

    public HdsCdnInfoBean getHds_cdn_info() {
        return hds_cdn_info;
    }

    public void setHds_cdn_info(HdsCdnInfoBean hds_cdn_info) {
        this.hds_cdn_info = hds_cdn_info;
    }

    public HdsUrlBean getHds_url() {
        return hds_url;
    }

    public void setHds_url(HdsUrlBean hds_url) {
        this.hds_url = hds_url;
    }

    public String getPublicX() {
        return publicX;
    }

    public void setPublicX(String publicX) {
        this.publicX = publicX;
    }

    public static class LcBean {
        private String isp_code;
        private String city_code;
        private String provice_code;
        private String country_code;
        private String ip;

        public String getIsp_code() {
            return isp_code;
        }

        public void setIsp_code(String isp_code) {
            this.isp_code = isp_code;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getProvice_code() {
            return provice_code;
        }

        public void setProvice_code(String provice_code) {
            this.provice_code = provice_code;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }

    public static class FlvCdnInfoBean {
        private String cdn_code;
        private String cdn_name;

        public String getCdn_code() {
            return cdn_code;
        }

        public void setCdn_code(String cdn_code) {
            this.cdn_code = cdn_code;
        }

        public String getCdn_name() {
            return cdn_name;
        }

        public void setCdn_name(String cdn_name) {
            this.cdn_name = cdn_name;
        }
    }

    public static class FlvUrlBean {
        private String flv1;
        private String flv2;
        private String flv3;
        private String flv4;
        private String flv5;
        private String flv6;

        public String getFlv1() {
            return flv1;
        }

        public void setFlv1(String flv1) {
            this.flv1 = flv1;
        }

        public String getFlv2() {
            return flv2;
        }

        public void setFlv2(String flv2) {
            this.flv2 = flv2;
        }

        public String getFlv3() {
            return flv3;
        }

        public void setFlv3(String flv3) {
            this.flv3 = flv3;
        }

        public String getFlv4() {
            return flv4;
        }

        public void setFlv4(String flv4) {
            this.flv4 = flv4;
        }

        public String getFlv5() {
            return flv5;
        }

        public void setFlv5(String flv5) {
            this.flv5 = flv5;
        }

        public String getFlv6() {
            return flv6;
        }

        public void setFlv6(String flv6) {
            this.flv6 = flv6;
        }
    }

    public static class HlsCdnInfoBean {
        private String cdn_code;
        private String cdn_name;

        public String getCdn_code() {
            return cdn_code;
        }

        public void setCdn_code(String cdn_code) {
            this.cdn_code = cdn_code;
        }

        public String getCdn_name() {
            return cdn_name;
        }

        public void setCdn_name(String cdn_name) {
            this.cdn_name = cdn_name;
        }
    }

    public static class HlsUrlBean {
        private String hls1;
        private String hls2;
        private String hls3;
        private String hls4;
        private String hls5;
        private String hls6;

        public String getHls1() {
            return hls1;
        }

        public void setHls1(String hls1) {
            this.hls1 = hls1;
        }

        public String getHls2() {
            return hls2;
        }

        public void setHls2(String hls2) {
            this.hls2 = hls2;
        }

        public String getHls3() {
            return hls3;
        }

        public void setHls3(String hls3) {
            this.hls3 = hls3;
        }

        public String getHls4() {
            return hls4;
        }

        public void setHls4(String hls4) {
            this.hls4 = hls4;
        }

        public String getHls5() {
            return hls5;
        }

        public void setHls5(String hls5) {
            this.hls5 = hls5;
        }

        public String getHls6() {
            return hls6;
        }

        public void setHls6(String hls6) {
            this.hls6 = hls6;
        }
    }

    public static class HdsCdnInfoBean {
        private String cdn_code;
        private String cdn_name;

        public String getCdn_code() {
            return cdn_code;
        }

        public void setCdn_code(String cdn_code) {
            this.cdn_code = cdn_code;
        }

        public String getCdn_name() {
            return cdn_name;
        }

        public void setCdn_name(String cdn_name) {
            this.cdn_name = cdn_name;
        }
    }

    public static class HdsUrlBean {
        private String hds1;
        private String hds2;
        private String hds3;
        private String hds4;
        private String hds5;
        private String hds6;

        public String getHds1() {
            return hds1;
        }

        public void setHds1(String hds1) {
            this.hds1 = hds1;
        }

        public String getHds2() {
            return hds2;
        }

        public void setHds2(String hds2) {
            this.hds2 = hds2;
        }

        public String getHds3() {
            return hds3;
        }

        public void setHds3(String hds3) {
            this.hds3 = hds3;
        }

        public String getHds4() {
            return hds4;
        }

        public void setHds4(String hds4) {
            this.hds4 = hds4;
        }

        public String getHds5() {
            return hds5;
        }

        public void setHds5(String hds5) {
            this.hds5 = hds5;
        }

        public String getHds6() {
            return hds6;
        }

        public void setHds6(String hds6) {
            this.hds6 = hds6;
        }
    }
}
