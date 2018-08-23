package phone.my.com.myxianmuphone.dao;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

public class UpdataServiceApk {
    private String DOWNLOADPATH = "/download/";//下载路径，如果不定义自己的路径，6.0的手机不自动安装
    //文件路径
    private String myDestFileDir=Environment.getExternalStorageDirectory().getAbsolutePath() + DOWNLOADPATH;
    //文件名字
    private String myDestFileName="app-release.apk";
    private ProgressDialog pDialog;

    public UpdataServiceApk(){
    }
    private Context mContext;
    public UpdataServiceApk(Context context){
        this.mContext=context;
    }

    /**
     * 下载
     * @param url   下载的url
     * @param destFileDir   下载的文件路径
     * @param destFileName  下载的文件名字
     */
    public void onDownload(String url,String destFileDir,String destFileName){
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        pDialog = new ProgressDialog(mContext);
        // 设置进度条风格，风格为长形
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 标题
        pDialog.setTitle("条形进度条");
        // 设置ProgressDialog 提示信息
         pDialog.setMessage("正在下载中……");
         // 设置ProgressDialog 标题图标
         // 设置ProgressDialog 进度条进度
          pDialog.setProgress(100);
         // 设置ProgressDialog 的进度条是否不明确
         pDialog.setIndeterminate(false);
          // 设置ProgressDialog 是否可以按退回按键取消
         pDialog.setCancelable(true);
         // 让ProgressDialog显示
         pDialog.show();
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(destFileDir,destFileName) {
            @Override
            public void inProgress(float progress, long total, int id) {
                if (pDialog!=null){
                    pDialog.setProgress((int) (100 * progress));
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {
                if (pDialog!=null){
                    pDialog.dismiss();
                }
            }
            @Override
            public void onResponse(File response, int id) {
                if (pDialog!=null){
                    pDialog.dismiss();
                }
                try{
                    Uri uri= Uri.parse(myDestFileDir + myDestFileName);
                    installAPK(uri,mContext);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void installAPK(Uri apk, Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            Intent intents = new Intent();
            intents.setAction(Intent.ACTION_VIEW);
//                intents.addCategory("android.intent.category.DEFAULT");
            intents.setDataAndType(apk, "application/vnd.android.package-archive");
            intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intents);
        } else if (Build.VERSION.SDK_INT >= 24) {
            install(context);
        } else {
            File file = new File(myDestFileDir + myDestFileName);
            if (file.exists()) {
                openFile(file, context);
            } else {
            }
        }
    }

    /**
     * android7.0之后的更新
     * 通过隐式意图调用系统安装程序安装APK
     */
    public void install(Context context) {
        File file = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                , myDestFileName);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
        Uri apkUri =FileProvider.getUriForFile(context, "cn.jiandao.global.fileprovider", file);
        intent.addCategory("android.intent.category.DEFAULT");
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
    /**
     * android6.0之后的升级更新
     *
     * @param file
     * @param context
     */
    public void openFile(File file, Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction("android.intent.action.VIEW");
        String type = getMIMEType(file);
        intent.setDataAndType(Uri.fromFile(file), type);
        try {
            context.startActivity(intent);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(context, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }
    }

    public String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }


}
