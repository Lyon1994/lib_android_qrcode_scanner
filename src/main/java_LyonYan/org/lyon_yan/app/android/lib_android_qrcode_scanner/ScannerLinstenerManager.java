package org.lyon_yan.app.android.lib_android_qrcode_scanner;

import android.app.Activity;
import android.content.Loader;
import android.graphics.Bitmap;

import com.google.zxing.Result;
import com.google.zxing.client.android.result.ResultHandler;

import org.lyon_yan.app.android.lib_android_qrcode_scanner.listener.OnScannerFailedListener;
import org.lyon_yan.app.android.lib_android_qrcode_scanner.listener.OnScannerSuccessListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanni on 2016/1/22.
 */
public class ScannerLinstenerManager {
    private static ScannerLinstenerManager scannerLinstenerManager = null;

    public static ScannerLinstenerManager Load() {
        if (scannerLinstenerManager == null)
            return scannerLinstenerManager = new ScannerLinstenerManager();
        return scannerLinstenerManager;
    }

    private ScannerLinstenerManager() {

    }

    private List<OnScannerSuccessListener> onScannerSuccessListeners = new ArrayList<>();
    private List<OnScannerFailedListener> onScannerFailedListeners = new ArrayList<>();

    public List<OnScannerFailedListener> getOnScannerFailedListeners() {
        return onScannerFailedListeners;
    }

    public List<OnScannerSuccessListener> getOnScannerSuccessListeners() {
        return onScannerSuccessListeners;
    }

    public void clearALL() {
        onScannerSuccessListeners.clear();
        onScannerFailedListeners.clear();
    }

    public void handleDecodeInternally(Activity activity, Result rawResult, ResultHandler resultHandler, Bitmap barcode) {
        for (OnScannerSuccessListener onScannerSuccessListener : onScannerSuccessListeners) {
            onScannerSuccessListener.call(activity, rawResult, resultHandler, barcode);
        }
    }

    public interface ScannerListener {
        public ScannerLinstenerManager getScannerListenerManager();
    }
}
