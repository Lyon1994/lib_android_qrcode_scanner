package org.lyon_yan.app.android.lib_android_qrcode_scanner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.zxing.client.android.CaptureActivityBindler;
import com.google.zxing.client.android.R;

/**
 * 二維碼掃描的師徒插件
 * Created by yanni on 2016/1/22.
 */
public class ScannerQrcodeView extends LinearLayout {
    private final CaptureActivityBindler captureActivityBindler;
    public ScannerQrcodeView(Context context) {
        super(context);
        captureActivityBindler=new CaptureActivityBindler();
        init();
    }

    public ScannerQrcodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        captureActivityBindler=new CaptureActivityBindler();
        init();
    }

    public ScannerQrcodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        captureActivityBindler=new CaptureActivityBindler();
        init();
    }

    @TargetApi(value = 21)
    public ScannerQrcodeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        captureActivityBindler=new CaptureActivityBindler();
        init();
    }

    public void init() {
        /**
         * 加载信息
         */
        LayoutInflater.from(getContext()).inflate(R.layout.view_qrcode_scanner,this,true);
    }


    public void Binding_onResume() {
        captureActivityBindler.Binding_onResume();
    }
    public void Binding_onPause() {
        captureActivityBindler.Binding_onPause();
    }
    public void Binding_onDestroy(){
        captureActivityBindler.Binding_onDestroy();
    }
    public boolean Binding_onKeyDown(int keyCode, KeyEvent event) {
        return captureActivityBindler.Binding_onKeyDown(keyCode, event);
    }
    public boolean Binding_onCreateOptionsMenu(Menu menu) {
        return captureActivityBindler.Binding_onCreateOptionsMenu(menu);
    }
    public boolean Binding_onOptionsItemSelected(MenuItem item) {
        return captureActivityBindler.Binding_onOptionsItemSelected(item);
    }

    /**
     * 必須實現的方法
     * @param baseActivity
     */
    public void Binding_onCreate(Activity baseActivity) {
        captureActivityBindler.Binding_onCreate(baseActivity,this);
    }
    public void Binding_onActivityResult(int requestCode, int resultCode, Intent intent) {
        captureActivityBindler.Binding_onActivityResult(requestCode,resultCode,intent);
    }

}
