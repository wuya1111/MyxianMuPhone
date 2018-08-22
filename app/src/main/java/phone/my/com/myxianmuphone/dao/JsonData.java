package phone.my.com.myxianmuphone.dao;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import phone.my.com.myxianmuphone.utils.ParameterizedTypeImpl;

/**
 * Created by Administrator on 2017/3/15.
 */

public class JsonData {
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    public static Gson getGson() {
        return gson;
    }


    public static int toJsonInt(String data, String string) {
        try {
            if (data == null)
                return -1;
            try {
                JSONObject jsonObject = new JSONObject(data);
                return (Integer) jsonObject.getInt(string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getToJsonInt(String data, String key) {
        try {
            if (data == null)
                return -1;
            try {
                JSONObject jsonObject = new JSONObject(data);
                return (Integer) jsonObject.getInt(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String toJsonString(String data, String string) {
        try {
            if (data == null)
                return null;
            try {
                JSONObject jsonObject = new JSONObject(data);
                return jsonObject.getString(string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getToJsonString(String data, String key) {
        try {
            if (data == null)
                return null;
            try {
                JSONObject jsonObject = new JSONObject(data);
                return jsonObject.getString(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJsonMap(Map<String, String> map) {
        if (map == null)
            return null;
        return gson.toJson(map);
    }


    public static String StringJson(String[] objects) {
        return (String) gson.toJson(objects);
    }


    public <T> List<T> getListGson(String data, Class<T> mClass) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        try {
            if (mClass != null) {
                // 生成List<T> 中的 List<T>
                Type listType = new ParameterizedTypeImpl(List.class, new Class[]{mClass});
                return gson.fromJson(data, listType);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解析对象
     * 将Json数据解析成相应的对象
     *
     * @param jsonData
     * @param mClass
     * @param <T>
     * @return
     */
    public  <T> T getClassGson(String jsonData, Class<T> mClass) {
        try {
            if (mClass != null) {
                Type type = new ParameterizedTypeImpl(mClass, new Class[]{mClass});
                return new Gson().fromJson(jsonData, type);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
