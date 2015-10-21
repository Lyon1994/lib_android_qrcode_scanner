package org.lyon_yan.app.android.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
/**
 * 
 * @author Lyon_Yan
 * <br/><b>time</b>: 2015年10月21日 下午4:16:16
 */
public class Device {
	/** 
	 * 判断是否为平板 
	 *  
	 * @return 
	 */  
	public static boolean isPad(Context context) {  
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
