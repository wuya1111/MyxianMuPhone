package phone.my.com.myxianmuphone.presenter;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.HashMap;
import okhttp3.Call;
import phone.my.com.myxianmuphone.dao.Url;
import phone.my.com.myxianmuphone.service.MyCallbackInterface;

public class MyRequerPresenter {

    //注册
    public void getRegisteredCode() {
        HashMap<String, String> hashMap = new HashMap<>();
        OkHttpUtils.post().url(Url.REGISTRT).params(hashMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }
    //验证码
    public void getVerification() {
        HashMap<String, String> hashMap = new HashMap<>();
        OkHttpUtils.post().url(Url.REGISTRT).params(hashMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }
    //登录
    public void getLogin(final MyCallbackInterface myCallbackInterface) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("","");
        OkHttpUtils.post().url(Url.REGISTRT).params(hashMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (myCallbackInterface!=null)
                    myCallbackInterface.onFailure("");
            }

            @Override
            public void onResponse(String response, int id) {
                if (myCallbackInterface!=null)
                     myCallbackInterface.onSuccessful("");
            }
        });
    }
    //呼叫
    public void getCall() {
        HashMap<String, String> hashMap = new HashMap<>();
        OkHttpUtils.post().url(Url.REGISTRT).params(hashMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }
    //充值
    public void getPaly() {
        HashMap<String, String> hashMap = new HashMap<>();
        OkHttpUtils.post().url(Url.REGISTRT).params(hashMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }
    //余额
    public void getBalance() {
        HashMap<String, String> hashMap = new HashMap<>();
        OkHttpUtils.post().url(Url.REGISTRT).params(hashMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }

}
