package phone.my.com.myxianmuphone.presenter;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;
import phone.my.com.myxianmuphone.dao.MyRequerDao;
import phone.my.com.myxianmuphone.dao.MyTool;
import phone.my.com.myxianmuphone.dao.Url;
import phone.my.com.myxianmuphone.service.MyCallbackInterface;
import phone.my.com.myxianmuphone.utils.DateUtils;
import phone.my.com.myxianmuphone.utils.MyMd5;

public class MyRequerPresenter {
    private  MyRequerDao myRequerDao;
    private Context mContext;
    private String myPhone;
    public MyRequerPresenter(){
        init();
    }

    public MyRequerPresenter(Context context){
        init();
        mContext=context;
    }
    public MyRequerPresenter(Context context,String phone){
        init();
        mContext=context;
        myPhone=phone;
    }

    private void init(){
        myRequerDao = new MyRequerDao();
    }
    //注册
    public void getRegisteredCode(String password,String sms,MyCallbackInterface myCallbackInterface) {
        HashMap<String, String> hashMap = new HashMap<>();
        setHashMap(myPhone,hashMap);
        hashMap.put("sms",sms+"");
        hashMap.put("pass",password+"");
        myRequerDao.onPostReuQuer(Url.REGISTRT,hashMap,myCallbackInterface);
    }
    //验证码
    public void getVerification(MyCallbackInterface myCallbackInterface) {
        HashMap<String, String> hashMap = new HashMap<>();
        setHashMap(myPhone,hashMap);
        myRequerDao.onPostReuQuer(Url.VERIFICATION,hashMap,myCallbackInterface);
    }
    //找回验证码
    public void getBackCode(MyCallbackInterface myCallbackInterface) {
        HashMap<String, String> hashMap = new HashMap<>();
        setHashMap(myPhone,hashMap);
        myRequerDao.onPostReuQuer(Url.GETPASS,hashMap,myCallbackInterface);
    }
    //找回验证码
    public void geBackPassw(String password,String sms,MyCallbackInterface myCallbackInterface) {
        HashMap<String, String> hashMap = new HashMap<>();
        setHashMap(myPhone,hashMap);
        hashMap.put("sms",sms+"");
        hashMap.put("pass",password+"");
        myRequerDao.onPostReuQuer(Url.BACK_REGISTRT,hashMap,myCallbackInterface);
    }
    //登录
    public void getLogin(String password,final MyCallbackInterface myCallbackInterface) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pass",password);
        setHashMap(myPhone,hashMap);
        myRequerDao.onPostReuQuer(Url.LOGIN,hashMap,myCallbackInterface);
    }
    //呼叫
    public void getCall(String call, MyCallbackInterface myCallbackInterface) {
        HashMap<String, String> hashMap = new HashMap<>();
        setHashMap(myPhone,call,hashMap);
        myRequerDao.onPostReuQuer(Url.CALL,hashMap,myCallbackInterface);
    }
    //充值
    public void getPaly(String kahao,String mima, final MyCallbackInterface myCallbackInterface) {
        HashMap<String, String> hashMap = new HashMap<>();
        setHashMap(myPhone,hashMap);
        hashMap.put("kahao",kahao+"");
        hashMap.put("mima",mima+"");
        myRequerDao.onPostReuQuer(Url.PALY,hashMap,myCallbackInterface);
    }
    //余额
    public void getBalance(MyCallbackInterface myCallbackInterface) {
        HashMap<String, String> hashMap = new HashMap<>();
        setHashMap(myPhone,hashMap);
        myRequerDao.onPostReuQuer(Url.BALANCE,hashMap,myCallbackInterface);
    }


    private void setHashMap(String phone ,HashMap<String,String>hashMap){
        if (TextUtils.isEmpty(phone)){
            return;
        }
        if (phone.length()<11){
            return;
        }
        if (mContext==null)
            return;
        hashMap.put("phone",phone+"");

       String logtime= DateUtils.dateToUnixTimestampepoch()+"";
        hashMap.put("times",    logtime);
        hashMap.put("v", MyTool.getLocalVersion(mContext)+"");
        String sphone=phone.substring(phone.length()-4,phone.length());
        String token=  MyMd5.md5((MyMd5.md5(sphone)+ logtime+""));
        hashMap.put("token",token);
    }
    private void setHashMap(String phone, String calls,HashMap<String,String>hashMap){
        if (TextUtils.isEmpty(phone)){
            return;
        }
        if (TextUtils.isEmpty(calls)){
            return;
        }
        if (phone.length()<11){
            return;
        }
        if (calls.length()<11){
            return;
        }
        if (mContext==null)
            return;
        hashMap.put("phone",phone+"");
       String logtime= DateUtils.dateToUnixTimestampepoch()+"";
        hashMap.put("times",    logtime);
        hashMap.put("v", MyTool.getLocalVersion(mContext)+"");
        String sphone=phone.substring(phone.length()-4,phone.length());
        String token=  MyMd5.md5((MyMd5.md5(sphone)+ logtime+""));

        String spcalls=calls.substring(calls.length()-4,calls.length());
        String shake=  MyMd5.md5((MyMd5.md5(calls)+ logtime+""));
        hashMap.put("call",calls+"");
        hashMap.put("shake",shake+"");
        hashMap.put("token",token);
    }


}
