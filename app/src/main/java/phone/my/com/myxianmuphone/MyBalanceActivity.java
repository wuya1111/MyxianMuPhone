package phone.my.com.myxianmuphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import phone.my.com.myxianmuphone.presenter.MyRequerPresenter;
import phone.my.com.myxianmuphone.service.MyCallbackInterface;
import phone.my.com.myxianmuphone.utils.ACache;

public class MyBalanceActivity extends AppCompatActivity {

    private TextView tyue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_balance);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initUI();
    }

    private void initUI() {
        tyue = (TextView)findViewById(R.id.tyue);
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
