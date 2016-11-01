package com.github.cnlinjie.infrastructure.web.domain;

import com.github.cnlinjie.infrastructure.util.MapUtils;
import com.github.cnlinjie.infrastructure.util.net.HttpHelper;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Map;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-9-19
 */
public class SearchDomain {

    public static void main (String[] args) {

        HttpHelper httpHelper = new HttpHelper();
        //https://sg.godaddy.com/zh/domainsapi/v1/search/exact?q=abcqqqqqqq.com&key=dpp_search&pc=&ptl=
        Map<String,Object>  params =
                MapUtils.builder()
                .put("q","abccccccc.com")
                .put("key","dpp_search")
                .put("pc","")
                .put("ptl","")
                .get();
        try {
            String s = httpHelper.get("https://sg.godaddy.com/zh/domainsapi/v1/search/exact", params);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
