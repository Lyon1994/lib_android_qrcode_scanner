//package org.lyon_yan.android.lib_android_qrcode_scanner.view;
//
//import java.io.IOException;
//
//import com.google.zxing.client.android.camera.AutoFocusManager;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.hardware.Camera;
//import android.hardware.Camera.AutoFocusCallback;
//import android.hardware.Camera.Parameters;
//import android.view.Surface;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//
//@SuppressLint("InlinedApi")
//@SuppressWarnings("deprecation")
//public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
//	private SurfaceHolder holder;
//	private Camera myCamera;
//	private Activity activity;
//	private int p_width;
//	private int p_height;
//
//	private AutoFocusManager autoFocusManager;
//
//	public CameraView(Activity activity, int p_width, int p_height) {
//		super(activity);
//		// TODO Auto-generated constructor stub
//		holder = getHolder();// 获得surfaceHolder引用
//		holder.addCallback(this);
//		setKeepScreenOn(true);
//		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// 设置类型
//		setActivity(activity);
//		setP_height(p_height);
//		setP_width(p_width);
//	}
//
//	/**
//	 * 让照相机成像正确的显示
//	 * 
//	 * @author Lyon_Yan <br/>
//	 *         <b>time</b>: 2015年9月28日 上午10:31:56
//	 * @param cameraId
//	 * @param camera
//	 */
//	public void setCameraDisplayOrientation(int cameraId,
//			android.hardware.Camera camera) {
//		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
//		android.hardware.Camera.getCameraInfo(cameraId, info);
//		int rotation = getActivity().getWindowManager().getDefaultDisplay()
//				.getRotation();
//		int degrees = 0;
//		switch (rotation) {
//		case Surface.ROTATION_0:
//			degrees = 0;
//			break;
//		case Surface.ROTATION_90:
//			degrees = 90;
//			break;
//		case Surface.ROTATION_180:
//			degrees = 180;
//			break;
//		case Surface.ROTATION_270:
//			degrees = 270;
//			break;
//		}
//		int result;
//		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
//			result = (info.orientation + degrees) % 360;
//			result = (360 - result) % 360;// compensate the mirror 
//		} else {// back-facing 
//			result = (info.orientation - degrees + 360) % 360;
//		}
//		camera.setDisplayOrientation(result);
//	}
//
//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		// TODO Auto-generated method stub
//		if (myCamera == null) {
//			myCamera = Camera.open();// 开启相机,不能放在构造函数中，不然不会显示画面.
//			myCamera.getParameters().setPreviewSize(p_width, p_height);
//			myCamera.getParameters().setFlashMode(Parameters.FLASH_MODE_TORCH);
//			myCamera.getParameters().setFocusMode(
//					Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 1连续对焦
//			setFocusable(true);
//			try {
//				myCamera.setPreviewDisplay(holder);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width,
//			int height) {
//		// TODO Auto-generated method stub
//		setCameraDisplayOrientation(0, myCamera);
//		myCamera.startPreview();
//		// 实现自动对焦
//		myCamera.autoFocus(new AutoFocusCallback() {
//			@Override
//			public void onAutoFocus(boolean success, Camera camera) {
//				if (success) {
//					camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
//				}
//			}
//
//		});
//		setAutoFocusManager(new AutoFocusManager(getActivity(), myCamera));
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//		// TODO Auto-generated method stub
//		myCamera.stopPreview();// 停止预览
//		myCamera.release();// 释放相机资源
//		myCamera = null;
//	}
//
//	public Activity getActivity() {
//		return activity;
//	}
//
//	public void setActivity(Activity activity) {
//		this.activity = activity;
//	}
//
//	public int getP_width() {
//		return p_width;
//	}
//
//	public void setP_width(int p_width) {
//		this.p_width = p_width;
//	}
//
//	public int getP_height() {
//		return p_height;
//	}
//
//	public void setP_height(int p_height) {
//		this.p_height = p_height;
//	}
//
//	public AutoFocusManager getAutoFocusManager() {
//		return autoFocusManager;
//	}
//
//	public void setAutoFocusManager(AutoFocusManager autoFocusManager) {
//		this.autoFocusManager = autoFocusManager;
//	}
//}
