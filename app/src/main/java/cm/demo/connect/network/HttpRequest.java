package cm.demo.connect.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRequest {

    public static final int REQUEST_TIMEOUT = 10 * 1000;//设置请求超时10秒钟
    public static final int SO_TIMEOUT = 10 * 1000;  //设置等待数据超时时间10秒钟

    public String doRequest(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setConnectTimeout(5 * 1000);
        urlConn.connect();
        BufferedReader buffer = null;
        String line = null;
        StringBuffer sb = new StringBuffer();
        if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {

            buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((line = buffer.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } else {
            return null;
        }
    }
}
