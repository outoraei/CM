package cm.demo.connect.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkCommon {


    public static final String CM_SERVER_URL = "http://172.26.12.81:8080/CMWebPro/";
    public static final String CM_USER_SERVLET = "HelloWorldServlet";

//Request Mask
    public static final int REQUEST_TIMEOUT = 5000;

//Response Mask
    public static final int RESPONSE_CODE_OK = 200;


    public static final String REQUEST_TYPE_PARAM = "param";
    public static final String REQUEST_TYPE_VALUE = "value";
    public static final String REQUEST_TYPE_PATH = "path";

    public static String buildRequest() {
        StringBuffer sb = new StringBuffer();
        String path = "";

//        if (!params.containsKey(REQUEST_TYPE_PATH)) {
//            CMLog.LogE("error path");
//            return null;
//        } else {
//            sb.append(params.get(REQUEST_TYPE_PATH));
//        }

        JSONArray jArray = new JSONArray();
        JSONObject jObject = new JSONObject();
        try {
            jObject.put(REQUEST_TYPE_PARAM, "1");
            jObject.put(REQUEST_TYPE_VALUE, "2");
            jObject.put(REQUEST_TYPE_PATH, "3");

        } catch (JSONException e) {
            e.printStackTrace();
        }


//        for (int index = 0; index < params.size(); index++) {
//            if ()

//            sb.append(path).append("?").append("username").append("=").append("root").append("&").append("password").append("=").append("root1");
//        }

        return jObject.toString();
    }
}
