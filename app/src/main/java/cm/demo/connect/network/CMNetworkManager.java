package cm.demo.connect.network;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cm.demo.util.CMLog;

public class CMNetworkManager {

    public static String sendRequest(final String path) {
        CMLog.LogD("sendRequest");
        String parseStream = null;
        HttpURLConnection connection = null;
        String result = null;
        StringBuilder sb = new StringBuilder();
        sb.append(path).append("?").append("username").append("=").append("root").append("&").append("password").append("=").append("root1");

        try {
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(NetworkCommon.REQUEST_TIMEOUT);
            connection.setReadTimeout(NetworkCommon.REQUEST_TIMEOUT);
            connection.setRequestMethod("POST");

            int responseCode = connection.getResponseCode();
            CMLog.LogD("responseCode : " + responseCode);
            if (NetworkCommon.RESPONSE_CODE_OK == responseCode) {
                InputStream inputStream = connection.getInputStream();
                parseStream = parseInputStream(inputStream);
                CMLog.LogD(parseStream);

                if (null != parseStream && parseStream.length() != 0) {
                    JSONObject jsonObject = new JSONObject(parseStream);
                    if (jsonObject.has("result")) {
                        result = jsonObject.get("result").toString();
                    }
                }


            } else {
                CMLog.LogE("net work status error");
            }
        } catch (Exception e) {
            CMLog.LogE("Exception");
            e.printStackTrace();
        } finally {
            if (null == connection) {
                connection.disconnect();
            }
        }
        return result;
    }

    public static String parseInputStream(InputStream inputStream) {
        try {
            // 定义一个字节数组输出流
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            // 定义一个字节数组
            byte[] buffer = new byte[1024];
            // 定义初始长度
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                // 将读的内容，写到字节数组输出流中
                arrayOutputStream.write(buffer, 0, len);
            }
            // 将字节输出流转成字符串
            return arrayOutputStream.toString("utf-8");
            // utf-8 大小写都可以，gbk 必须大写
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean parseJsonResult(String inputStr) {
        if (null == inputStr) return false;
        if (0 == inputStr.length()) return false;
        return true;
    }

}
