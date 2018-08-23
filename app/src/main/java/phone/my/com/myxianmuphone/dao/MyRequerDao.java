package phone.my.com.myxianmuphone.dao;

import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import phone.my.com.myxianmuphone.service.MyCallbackInterface;
import phone.my.com.myxianmuphone.utils.DateUtils;

public class MyRequerDao {
    public void onPostReuQuer(String url, HashMap<String,String> hashMap, final MyCallbackInterface myCallbackInterface){
        HashMap<String,String>myhashmap;
        if (hashMap!=null){
            myhashmap=hashMap;
        }else{
            myhashmap=new HashMap<>();
        }

        OkHttpUtils.post().url(url).params(myhashmap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (myCallbackInterface!=null){
                    myCallbackInterface.onFailure("");
                }
            }
            @Override
            public void onResponse(String response, int id) {
                if (!TextUtils.isEmpty(response)){
                    JsonData jsonData=new JsonData();
                   if (jsonData.getToJsonInt(response,"msg")==0){
                       if (myCallbackInterface!=null){
                           myCallbackInterface.onSuccessful("");
                       }
                   }
                }else{
                    if (myCallbackInterface!=null){
                        myCallbackInterface.onFailure("");
                    }

                }
            }
        });
    }
    public void onGetReuQuer(String url,HashMap<String,String>hashMap,final MyCallbackInterface myCallbackInterface){
        HashMap<String,String>myhashmap;
        if (hashMap!=null){
            myhashmap=hashMap;
        }else{
            myhashmap=new HashMap<>();
        }
        OkHttpUtils.post().url(url).params(myhashmap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (myCallbackInterface!=null){
                    myCallbackInterface.onFailure("");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                if (myCallbackInterface!=null){
                    myCallbackInterface.onSuccessful("");
                }
            }
        });
    }
}
