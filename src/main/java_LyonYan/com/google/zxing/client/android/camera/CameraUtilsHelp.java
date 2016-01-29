package com.google.zxing.client.android.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;

import org.lyon_yan.app.android.lib_android_qrcode_scanner.utils.CameraUtils;

/**
 * Created by yanni on 2016/1/22.
 */
public class CameraUtilsHelp {

    public synchronized static Camera.Parameters Binding_initFromCameraParameters(com.google.zxing.client.android.camera.CameraConfigurationManager cameraConfigurationManager, Camera.Parameters parameters, Camera camera, Context context) {
        return CameraUtils.initParametersV1(parameters, camera, context);
    }
}
