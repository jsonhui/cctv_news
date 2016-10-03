package com.cctvnews.www.model.imodel;

import com.cctvnews.www.handle.ICallBack;

import java.util.List;

/**
 * 作者：Json on 2016/7/21 20:05
 * 邮箱：320175912@qq.com
 */
public interface ITimeChain {
    /**
     * @param path      链接
     * @param iCallBack 回调
     */
    void getTimeChainData(String path, ICallBack iCallBack);
    /**
     * @param path      链接
     * @param info      源数据
     * @param iCallBack 回调
     */
    void getTimeChainMoreData(String path, List<Object> info, ICallBack iCallBack);
}
