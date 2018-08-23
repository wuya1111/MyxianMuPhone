package phone.my.com.myxianmuphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import phone.my.com.myxianmuphone.presenter.MyRequerPresenter;
import phone.my.com.myxianmuphone.service.MyCallbackInterface;
import phone.my.com.myxianmuphone.utils.ACache;

public class MyBalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_balance);
        initUI();
    }

    private void initUI() {
        MyApplication myApplication = (MyApplication) getApplication();
        MyRequerPresenter myRequerPresenter = new MyRequerPresenter(this, myApplication.getPhone());
        myRequerPresenter.getBalance(new MyCallbackInterface() {
            @Override
            public void onSuccessful(String str) {

            }

            @Override
            public void onFailure(String str) {

            }
        });
    }
}
