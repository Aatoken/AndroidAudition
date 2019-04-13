package com.imooc.latte_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.imooc.latte_core.app.Latte;
import com.imooc.latte_core.net.callback.IRequest;
import com.imooc.latte_core.net.callback.ISuccess;
import com.imooc.latte_core.util.file.FileUtil;
import com.imooc.latte_core.util.str.StrUtils;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Author Aatoken
 * Date 2019/4/13 9:45
 * Description
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request,
                        ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    /**
     * 执行操作
     *
     * @param params 参数：下载文件目录，后缀名，文件全名，body
     * @return
     */
    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final String name = (String) params[2];
        final ResponseBody body = (ResponseBody) params[3];

        final InputStream is = body.byteStream();

        if (StrUtils.isEmpty(downloadDir)) {
            downloadDir = "down_loads";
        }
        if (StrUtils.isEmpty(extension)) {
            extension = "";
        }
        //name 是完整的文件名
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir,
                    extension.toUpperCase(), extension);
        } else {

            return FileUtil.writeToDisk(is, downloadDir, name);
        }

    }

    /**
     * 执行完成之后做个回调处理
     *
     * @param file
     */
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        autoInstallApk(file);

    }


    /**
     * 默认安装apk
     *
     * @param file 下载文件
     */
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }


}
