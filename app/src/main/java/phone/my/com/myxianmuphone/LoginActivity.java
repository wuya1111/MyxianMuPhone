package phone.my.com.myxianmuphone;

import android.content.Intent;
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
public class LoginActivity extends AppCompatActivity  {

    private EditText mEmailView;
    private EditText mPasswordView;
    private MyApplication myApplication;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (EditText) findViewById(R.id.email);
        myApplication = (MyApplication) getApplication();
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button sign_in_button1 = (Button) findViewById(R.id.sign_in_button1);
        sign_in_button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        if (TextUtils.isEmpty(password) ) {
            return;
        }
        if (TextUtils.isEmpty(email)) {
            return;
        }
        MyRequerPresenter myRequerPresenter=new MyRequerPresenter(this, email);
        myRequerPresenter.getLogin(password,new MyCallbackInterface() {
            @Override
            public void onSuccessful(String str) {
                myApplication.setPhone(email);
                myApplication.setPassword(password);
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                MyToast.onToast(LoginActivity.this,"登录成功");
            }

            @Override
            public void onFailure(String str) {
                MyToast.onToast(LoginActivity.this,"登录失败");
            }
        });

    }
}

