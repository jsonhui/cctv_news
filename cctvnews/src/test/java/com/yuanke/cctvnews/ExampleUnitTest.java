package com.yuanke.cctvnews;

import com.cctvnews.www.config.URL;
import com.cctvnews.www.tool.commontool.HttpUtils;
import com.cctvnews.www.tool.commontool.RelativeDateformat;

import org.junit.Test;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String encode = URLDecoder.decode("联播看什么", "utf-8");
        System.out.println(encode);
    }

    @Test
    public void test() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2016-07-13 09:31:35");
        System.out.println(RelativeDateformat.format(date));
    }

    @Test
    public void tdat() {
        String path = "http://hot.news.cntv.cn/index.php?controller=list&action=getHandDataInfoNew&handdata_id=TDAT1372928688333145&n1=3&n2=20&toutuNum=3";
        System.out.println(path);
        System.out.println(URL.FRONT_DATA + HttpUtils.getTAdA(path) + HttpUtils.getToutuNum(path) + URL.CENTRE_DATA + 1 + URL.END_DATA);
    }

}