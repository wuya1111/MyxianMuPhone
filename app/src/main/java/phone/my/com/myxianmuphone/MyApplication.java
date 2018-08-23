package phone.my.com.myxianmuphone;

import android.app.Application;

public class MyApplication extends Application {
    private String phone = "";
    private String password = "";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
