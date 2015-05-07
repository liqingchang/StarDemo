package com.liqingchang.stardemo;

import android.content.Context;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by TeRRY on 2015/5/7.
 */
public class HttpUtil {

    /**
     * TODO :context其实可以不用返回结果再处理，懒了
     * @param context
     * @param url
     * @param data
     */
    public static void post(Context context, String url, Map<String, String> data) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("url");

        try {
            // 为httpPost设置HttpEntity对象
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            Iterator<String> iterator = data.keySet().iterator();
            while(iterator.hasNext()) {
                String key = iterator.next();
                String value = data.get(key);
                parameters.add(new BasicNameValuePair(key, value));
            }

            HttpEntity entity = new UrlEncodedFormEntity(parameters);
            httpPost.setEntity(entity);
            // httpClient执行httpPost表单提交
            HttpResponse response = httpClient.execute(httpPost);
            // 得到服务器响应实体对象
            HttpEntity responseEntity = response.getEntity();
            StringBuilder sb = new StringBuilder();
            if (responseEntity != null) {
                sb.append(EntityUtils.toString(responseEntity, "utf-8"));
                sb.append("\n");
                sb.append("success");
            } else {
                sb.append("error");
            }
            Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            httpClient.getConnectionManager().shutdown();
        }
    }


}
