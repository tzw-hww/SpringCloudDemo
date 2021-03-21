package com.example.adminservice;

import com.alibaba.fastjson.JSONObject;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DingDingMessageUtil {
    public static String access_token = "96461c7a4ba3a8132cd7b7ae3f49ff2ee5e2726fee1b079a0ea72af26ce5a3c8";
    //https://oapi.dingtalk.com/robot/send?access_token=96461c7a4ba3a8132cd7b7ae3f49ff2ee5e2726fee1b079a0ea72af26ce5a3c8
    public static void sendTextMessage(String msg) {
        try {
            Message message = new Message();
            message.setMsgtype("text");
            message.setText(new MessageInfo(msg));
            URL url = new URL("https://oapi.dingtalk.com/robot/send?access_token=" + access_token);
            // 建立 http 连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
            conn.connect();
            OutputStream out = conn.getOutputStream();
            String textMessage = JSONObject.toJSONString(message);
            System.out.println(msg);
            System.out.println(textMessage);
            byte[] data = textMessage.getBytes();
            out.write(data);
            out.flush();
            out.close();
            InputStream in = conn.getInputStream();
            byte[] data1 = new byte[in.available()];
            in.read(data1);
            System.out.println(new String(data1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
