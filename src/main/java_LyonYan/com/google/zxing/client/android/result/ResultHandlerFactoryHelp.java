package com.google.zxing.client.android.result;

import android.app.Activity;
import android.view.View;

import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.CaptureActivityBindler;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;

import org.lyon_yan.app.android.lib_android_qrcode_scanner.ScannerQrcodeView;

/**
 * Created by yanni on 2016/1/22.
 */
public class ResultHandlerFactoryHelp {
    private ResultHandlerFactoryHelp() {
    }

    public static ResultHandler makeResultHandler(Activity activity, Result rawResult, CaptureActivityBindler root) {
        ParsedResult result = parseResult(rawResult);
        switch (result.getType()) {
            case ADDRESSBOOK:
                return new AddressBookResultHandler(activity, result);
            case EMAIL_ADDRESS:
                return new EmailAddressResultHandler(activity, result);
            case PRODUCT:
                return new ProductResultHandler(activity, result, rawResult);
            case URI:
                return new URIResultHandler(activity, result);
            case WIFI:
                return new WifiResultHandlerHelp(activity, result, root);
            case GEO:
                return new GeoResultHandler(activity, result);
            case TEL:
                return new TelResultHandler(activity, result);
            case SMS:
                return new SMSResultHandler(activity, result);
            case CALENDAR:
                return new CalendarResultHandler(activity, result);
            case ISBN:
                return new ISBNResultHandler(activity, result, rawResult);
            default:
                return new TextResultHandler(activity, result, rawResult);
        }
    }

    private static ParsedResult parseResult(Result rawResult) {
        return ResultParser.parseResult(rawResult);
    }
}
