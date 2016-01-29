package org.lyon_yan.app.android.lib_android_qrcode_scanner.listener;

import android.app.Activity;
import android.graphics.Bitmap;

import com.google.zxing.Result;
import com.google.zxing.client.android.result.ResultHandler;

import org.lyon_yan.app.android.lib_android_qrcode_scanner.ScannerLinstenerManager;

/**
 * Created by yanni on 2016/1/22.
 */
public abstract class OnScannerSuccessListener {
    private Activity activity = null;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void call(Activity activity, Result rawResult, ResultHandler resultHandler, Bitmap barcode) {
        setActivity(activity);
        handleDecodeInternally(rawResult, resultHandler, barcode);
    }

    public abstract void handleDecodeInternally(Result rawResult, ResultHandler resultHandler, Bitmap barcode);
}
