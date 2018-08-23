package phone.my.com.myxianmuphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import phone.my.com.myxianmuphone.presenter.MyRequerPresenter;
import phone.my.com.myxianmuphone.service.MyCallbackInterface;
import phone.my.com.myxianmuphone.utils.ACache;

public class MyPayActivity extends AppCompatActivity {

    private EditText t__kahao;
    private EditText t__mima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pay);
        initUi();
    }

    private void initUi() {
        t__kahao = (EditText) findViewById(R.id.t__kahao);
        t__mima = (EditText) findViewById(R.id.t__mima);
        Button b_pay = (Button) findViewById(R.id.b_pay);
        b_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPay();
            }
        });
    }

    private void setPay() {
        String st__kahao = t__kahao.getText().toString().trim();
        String st__mima = t__mima.getText().toString().trim();
        if (TextUtils.isEmpty(st__kahao) || TextUtils.isEmpty(st__mima)) {
            return;
        }
        MyApplication myApplication = (MyApplication) getApplication();
        MyRequerPresenter myRequerPresenter = new MyRequerPresenter(this, myApplication.getPhone());
        myRequerPresenter.getPaly(st__kahao, st__mima, new MyCallbackInterface() {
            @Override
            public void onSuccessful(String str) {

            }

            @Override
            public void onFailure(String str) {

            }
        });
    }
}
