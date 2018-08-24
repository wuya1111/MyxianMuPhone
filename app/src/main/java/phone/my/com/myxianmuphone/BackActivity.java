package phone.my.com.myxianmuphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import phone.my.com.myxianmuphone.dao.MyToast;
import phone.my.com.myxianmuphone.presenter.MyRequerPresenter;
import phone.my.com.myxianmuphone.service.MyCallbackInterface;

/**
 * A login screen that offers login via email/password.
 */
public class BackActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText verification_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        verification_code = (EditText) findViewById(R.id.verification_code);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button huoqu = (Button) findViewById(R.id.huoqu);
        huoqu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailView.getText().toString();
                MyRequerPresenter myRequerPresenter = new MyRequerPresenter(BackActivity.this, email);
                myRequerPresenter.getBackCode(new MyCallbackInterface() {
                    @Override
                    public void onSuccessful(String str) {
                        MyToast.onToast(BackActivity.this, "获取验证码成功");
                    }

                    @Override
                    public void onFailure(String str) {

                    }
                });
            }
        });
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String sms = verification_code.getText().toString();
        if (TextUtils.isEmpty(password)) {
            return;
        }
        if (TextUtils.isEmpty(email)) {
            return;
        }
        if (TextUtils.isEmpty(sms)) {
            return;
        }
        MyRequerPresenter myRequerPresenter = new MyRequerPresenter(BackActivity.this, email);
        myRequerPresenter.geBackPassw(password, sms, new MyCallbackInterface() {
            @Override
            public void onSuccessful(String str) {
                MyToast.onToast(BackActivity.this, "找回密码成功，请重新登录");
            }

            @Override
            public void onFailure(String str) {

            }
        });
    }

}

