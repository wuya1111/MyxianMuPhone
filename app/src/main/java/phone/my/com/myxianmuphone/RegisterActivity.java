package phone.my.com.myxianmuphone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import phone.my.com.myxianmuphone.dao.MyToast;
import phone.my.com.myxianmuphone.presenter.MyRequerPresenter;
import phone.my.com.myxianmuphone.service.MyCallbackInterface;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity  {

    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText verification_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        verification_code = (EditText) findViewById(R.id.verification_code);
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
            public void onClick(View view) {
                String email = mEmailView.getText().toString();
                MyRequerPresenter myRequerPresenter=new MyRequerPresenter(RegisterActivity.this,email);
                myRequerPresenter.getVerification( new MyCallbackInterface() {
                    @Override
                    public void onSuccessful(String str) {
                        MyToast.onToast(RegisterActivity.this,"获取验证码成功");
                    }

                    @Override
                    public void onFailure(String str) {
                    }
                });
            }
        });

        Button zhaohui_sign_in_button = (Button) findViewById(R.id.zhaohui_sign_in_button);
        zhaohui_sign_in_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(RegisterActivity.this,BackActivity.class);
                startActivity(intent);
                finish();
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
        if (TextUtils.isEmpty(sms)) {
            return;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            return;
        }
        MyRequerPresenter myRequerPresenter=new MyRequerPresenter(this,email);
        myRequerPresenter.getRegisteredCode(password,sms, new MyCallbackInterface() {
            @Override
            public void onSuccessful(String str) {

                finish();
            }

            @Override
            public void onFailure(String str) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}

