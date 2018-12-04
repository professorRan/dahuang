package net.tsingk.m.impl;

import com.google.gson.JsonObject;
import net.tsingk.m.CheckSumBuilder;
import net.tsingk.pojo.IMUser;
import net.tsingk.pojo.NeteaseIMUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class NeteaseService {

    private Log log = LogFactory.getLog(NeteaseService.class);

    @Value("${im.netease.key}")
    private String  appKey;

    @Value("${im.netease.secret}")
    private String appSecret;

    private static final String CREAT_USER_URL ="https://api.netease.im/nimserver/user/create.action";


    public IMUser createUser(String nickName, String accId) {
        IMUser imuser = new NeteaseIMUser();
        Map<String, String> values = new HashMap<>();
        values.put("accid", accId);
        values.put("name", nickName);
        try {
            byte[] data = sendRequest(CREAT_USER_URL, values);
            JSONObject jsonObject = new JSONObject(new String(data));

            int code = jsonObject.getInt("code");
            if ( code== 200) {
                JSONObject info = jsonObject.getJSONObject("info");
                String token = info.getString("token");
                imuser.setImUserToken(token);
                imuser.setImUserId(info.getString("accid"));
            } else {
                log.error("Create imuser failed " + code);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(" create im user failed " + accId +"  nick name :" + nickName);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imuser;
    }



    private byte[] sendRequest(String url, Map<String, String> values) throws IOException {
        HttpPost httpPost = new HttpPost(url);

        String nonce =  "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> e : values.entrySet()) {
            nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpClient client = HttpClients.createDefault();
        // 执行请求
        HttpResponse response = null;
        InputStream in = null;
        try {
            response = client.execute(httpPost);
            int len = (int)response.getEntity().getContentLength();
            byte[] data = new byte[len];
            in = response.getEntity().getContent();
            in.read(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
