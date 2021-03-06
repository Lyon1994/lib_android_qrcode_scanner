package org.lyon_yan.android.lib_android_qrcode_scanner.core;

import android.app.Activity;
import android.hardware.Camera;
import android.view.Surface;

public class CameraUtils {
	/**
	 * 让照相机成像正确的显示
	 * 
	 * @author Lyon_Yan <br/>
	 *         <b>time</b>: 2015年9月28日 上午10:31:56
	 * @param cameraId
	 * @param camera
	 *            android.hardware.Camera;
	 */
	@SuppressWarnings("deprecation")
	public static void setCameraDisplayOrientation(int cameraId,
			android.hardware.Camera camera, Activity activity) {
		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
		android.hardware.Camera.getCameraInfo(cameraId, info);
		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int degrees = 0;
		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0;
			break;
		case Surface.ROTATION_90:
			degrees = 90;
			break;
		case Surface.ROTATION_180:
			degrees = 180;
			break;
		case Surface.ROTATION_270:
			degrees = 270;
			break;
		}
//		/**
//		 * 调整屏幕
//		 */
//		degrees+=90;
		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360;// compensate the mirror 
		} else {// back-facing 
			result = (info.orientation - degrees + 360) % 360;
		}
		camera.setDisplayOrientation(result);
	}
}
