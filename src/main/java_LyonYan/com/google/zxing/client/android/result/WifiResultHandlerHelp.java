package com.google.zxing.client.android.result;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.CaptureActivityBindler;
import com.google.zxing.client.android.R;
import com.google.zxing.client.android.wifi.WifiConfigManager;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.WifiParsedResult;

import org.lyon_yan.app.android.lib_android_qrcode_scanner.ScannerQrcodeView;

/**
 * Created by yanni on 2016/1/22.
 */
public class WifiResultHandlerHelp extends ResultHandler {

    private static final String TAG = WifiResultHandler.class.getSimpleName();

    private final Activity parent;
    private final CaptureActivityBindler parent_root;

    public WifiResultHandlerHelp(Activity activity, ParsedResult result, CaptureActivityBindler root) {
        super(activity, result);
        parent = activity;
        parent_root=root;
    }

    @Override
    public int getButtonCount() {
        // We just need one button, and that is to configure the wireless.  This could change in the future.
        return 1;
    }

    @Override
    public int getButtonText(int index) {
        return R.string.button_wifi;
    }

    @Override
    public void handleButtonPress(int index) {
        if (index == 0) {
            WifiParsedResult wifiResult = (WifiParsedResult) getResult();
            WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager == null) {
                Log.w(TAG, "No WifiManager available from device");
                return;
            }
            final Activity activity = getActivity();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity.getApplicationContext(), R.string.wifi_changing_network, Toast.LENGTH_SHORT).show();
                }
            });
            new WifiConfigManager(wifiManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wifiResult);
            parent_root.restartPreviewAfterDelay(0L);
        }
    }

    // Display the name of the network and the network type to the user.
    @Override
    public CharSequence getDisplayContents() {
        WifiParsedResult wifiResult = (WifiParsedResult) getResult();
        return wifiResult.getSsid() + " (" + wifiResult.getNetworkEncryption() + ')';
    }

    @Override
    public int getDisplayTitle() {
        return R.string.result_wifi;
    }
}