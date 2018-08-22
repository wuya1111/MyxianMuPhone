package phone.my.com.myxianmuphone.dao;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class MyToast {
    public static void onToast(Context context,String string){
        if (context==null)
            return;
        if (TextUtils.isEmpty(string)){
            return;
        }
        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
    }
}
