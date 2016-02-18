package org.lyon_yan.app.android.lib_android_qrcode_scanner.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * (c) Livotov Labs Ltd. 2012
 * Date: 03/11/2014
 */
public class CameraUtils {
    private static Activity activity = null;

    public synchronized static void Binding_activity(Activity activity) {
        CameraUtils.activity = activity;
    }

    public synchronized static Camera.Parameters initParametersV1(Camera.Parameters parameters, Camera camera, Context context) {
        Point cameraResolution = findBestPreviewSizeValue(parameters, context);
        Configuration mConfiguration = activity.getResources().getConfiguration(); //获取设置的配置信息
        if (mConfiguration.orientation == mConfiguration.ORIENTATION_LANDSCAPE){
//            parameters.setPictureSize(cameraResolution.y, cameraResolution.x);
//            parameters.setPreviewSize(cameraResolution.y, cameraResolution.x);
        } else{
            parameters.setPictureSize(cameraResolution.x, cameraResolution.y);
            parameters.setPreviewSize(cameraResolution.x, cameraResolution.y);
            Log.v("-----cameraResolution--", "cameraResolution:{" + cameraResolution.x + "," + cameraResolution.y + "}");
        }

//        try {
//            parameters.setPictureSize(cameraResolution.x, cameraResolution.y);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            parameters.setPreviewSize(cameraResolution.x, cameraResolution.y);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Log.v("-----cameraResolution--", "cameraResolution:{" + cameraResolution.x + "," + cameraResolution.y + "}");


        if (activity != null) {
            int degrees = 90;
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
            switch (rotation) {
                case Surface.ROTATION_0:
                    degrees = 90;
                    Log.v("....................", "ROTATION_0");
                    break;
                case Surface.ROTATION_90:
                    degrees = 0;
                    Log.v("....................", "ROTATION_90");
                    break;
                case Surface.ROTATION_180:
                    degrees = 270;
                    Log.v("....................", "ROTATION_180");
                    break;
                case Surface.ROTATION_270:
                    degrees = 180;
                    Log.v("....................", "ROTATION_270");
                    break;
                default:
                    Log.v("....................", "ROTATION_NULL");
                    break;
            }
            /**
             * 兼容平板模式
             */
            if (isTablet(activity)) {
                switch (degrees){
                    case 0:
                        degrees=270;
                        break;
                    case 90:
                        degrees=0;
                        break;
                    case 180:
                        degrees=90;
                        break;
                    case 270:
                        degrees=180;
                        break;
                }
            }
            parameters.set("orientation", "portrait");
            parameters.set("rotation", degrees);
            camera.setDisplayOrientation(degrees);
            Log.e("..............", "orientation--------------->=" + parameters.get("orientation"));
        }
        return parameters;
    }

    private static Point findBestPreviewSizeValue(Camera.Parameters paramParameters, Context context) {
        //noinspection ResourceType
        Display localDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point paramPoint = new Point();
        localDisplay.getSize(paramPoint);
        List localList = paramParameters.getSupportedPreviewSizes();
        Point localObject1 = null;
        if (localList == null) {
            Camera.Size localSize4 = paramParameters.getPreviewSize();
            if (localSize4 == null)
                throw new IllegalStateException("Parameters contained no preview size!");
            localObject1 = new Point(localSize4.width, localSize4.height);
            return localObject1;
        }
        ArrayList localArrayList = new ArrayList(localList);
        Collections.sort(localArrayList, new CameraConfigurationUtilsComparator());
        if (Log.isLoggable("CameraConfiguration", 4)) {
            StringBuilder localStringBuilder = new StringBuilder();
            Iterator localIterator1 = localArrayList.iterator();
            while (localIterator1.hasNext()) {
                Camera.Size localSize3 = (Camera.Size) localIterator1.next();
                localStringBuilder.append(localSize3.width).append('x').append(localSize3.height).append(' ');
            }
        }
        double d1 = paramPoint.x / paramPoint.y;
        int i = 0;
        if (d1 < 1.0D) {
            i = 1;
//            if (i != 0)
//                break label673;
        }
        label260:
        label662:
        label673:
        {
            for (double d2 = 1.0D / d1; i != 0; d2 = d1) {
                localObject1 = null;
                double d3 = (1.0D / 0.0D);
                Iterator localIterator2 = localArrayList.iterator();
                label343:
                label352:
                {
                    double d4;
                    label422:
                    label429:
                    label436:
                    {
                        Object localObject2;
                        while (true)
                            if (localIterator2.hasNext()) {
                                Camera.Size localSize2 = (Camera.Size) localIterator2.next();
                                int j = localSize2.width;
                                int k = localSize2.height;
                                int m = j * k;
                                if ((m < 153600) || (m > 1024000)) {
                                    localIterator2.remove();
                                    continue;
                                } else {
                                    i = 0;
                                }
                                int n;
                                int i1 = 0;
                                if (j > k) {
                                    n = 1;
                                    if (n == 0)
                                        break label422;
                                    i1 = k;
                                    if (n == 0)
                                        break label429;
                                }
                                if ((i1 != paramPoint.x) || (j != paramPoint.y)) {
                                    localObject1 = new Point(j, k);
                                    d4 = Math.abs(i1 / j - d2);
                                    if (d4 >= d3)
                                        break label662;
                                    localObject1 = new Point(j, k);
                                    return localObject1;
                                }
                            } else {
                                if (localObject1 == null) {
                                    Camera.Size localSize1 = paramParameters.getPreviewSize();
                                    if (localSize1 == null)
                                        throw new IllegalStateException("Parameters contained no preview size!");
                                    return localObject1 = new Point(localSize1.width, localSize1.height);
                                }
                            }
                    }
                }
            }
        }
        return localObject1;
    }

    public static class CameraConfigurationUtilsComparator implements Comparator<Camera.Size> {

        @Override
        public int compare(Camera.Size lhs, Camera.Size rhs) {
            int i = lhs.height * lhs.width;
            int j = rhs.height * rhs.width;
            int k;
            if (j < i)
                k = -1;
            else {
                if (j > i)
                    k = 1;
                else
                    k = 0;
            }
            return k;
        }
    }
    /**
     * 判断是否为平板
     * @param context
     * @return
     */
    private static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
    /**
     * 判断是否为平板
     *
     * @return
     */
    private static boolean isPad(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        // 屏幕宽度
        float screenWidth = display.getWidth();
        // 屏幕高度
        float screenHeight = display.getHeight();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        // 屏幕尺寸
        double screenInches = Math.sqrt(x + y);
        // 大于6尺寸则为Pad
        if (screenInches >= 6.0) {
            return true;
        }
        return false;
    }
}
