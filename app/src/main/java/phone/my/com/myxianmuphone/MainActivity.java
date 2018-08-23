package phone.my.com.myxianmuphone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import phone.my.com.myxianmuphone.presenter.MyRequerPresenter;
import phone.my.com.myxianmuphone.service.MyCallbackInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyRequerPresenter myRequerPresenter;
    private AutoCompleteTextView mEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        myRequerPresenter = new MyRequerPresenter();
    }

    private void initUI() {
        Button b_call=(Button)findViewById(R.id.b_call);
        Button b_query=(Button)findViewById(R.id.b_query);
        Button b_top=(Button)findViewById(R.id.b_top);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        b_call.setOnClickListener(this);
        b_query.setOnClickListener(this);
        b_top.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_call:
                String email = mEmailView.getText().toString();
                myRequerPresenter.getCall(email,new MyCallbackInterface() {
                    @Override
                    public void onSuccessful(String str) {

                    }

                    @Override
                    public void onFailure(String str) {

                    }
                });
                break;
            case R.id.b_query:
                setIntenrt(0);
//                myRequerPresenter.getBalance(new MyCallbackInterface() {
//                    @Override
//                    public void onSuccessful(String str) {
//                        setIntenrt(1);
//                    }
//
//                    @Override
//                    public void onFailure(String str) {
//
//                    }
//                });
                break;
            case R.id.b_top:
                setIntenrt(2);

                break;
        }
    }


    public void setIntenrt(int i){
        Intent intent=new Intent();
        if (i==0){
            intent.setClass(this,MyBalanceActivity.class);

        }else{
            intent.setClass(this,MyPayActivity.class);
        }
        startActivity(intent);
    }
}
