package org.lyon_yan.android.lib_android_qrcode_scanner;

import org.lyon_yan.android.lib_FontAwesome.FontManager;
import org.lyon_yan.android.lib_android_qrcode_scanner.view.CameraView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.wangjie.wavecompat.WaveContainer;
import com.wangjie.wavecompat.WaveDrawable;

public class MainActivity extends AppCompatActivity {
	private static Typeface fontAwesome = null;
	private Toolbar toolbar = null;
	private LinearLayout camera_content = null;
	private CameraView cameraView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		initConfig();
		initView();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			View contentView = findViewById(android.R.id.content);
			Bitmap bitmap = convertViewToBitmap(contentView);
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();

			View view = new View(this);
			view.setLayoutParams(new ViewGroup.LayoutParams(width, height));
			WaveDrawable waveDrawable = new WaveDrawable().setColor(0xff8B2252)
					.setTouchPoint(
							new Point((int) event.getX(), (int) event.getY()));
			waveDrawable.setFrameBitmap(bitmap);
			int statusBarHeight = getStatusBarHeight(this);

			new WaveContainer(view, waveDrawable, width, height) {
				@Override
				protected void onWaveDrawableAnimatorEndExtraAction() {
				}
			}
			// .showAtLocation(contentView, Gravity.NO_GRAVITY, 0, 0);
			.showAtLocation(contentView, Gravity.NO_GRAVITY, 0, statusBarHeight);
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private static int getStatusBarHeight(Activity activity) {
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		return frame.top;
	}

	private static Bitmap convertViewToBitmap(View view) {
		// view.measure(View.MeasureSpec.makeMeasureSpec(0,
		// View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0,
		// View.MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.setDrawingCacheEnabled(true);
		// view.buildDrawingCache();
		return view.getDrawingCache();
	}

	@SuppressWarnings("deprecation")
	private void initView() {
		// TODO Auto-generated method stub
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		setSupportActionBar(toolbar);
		getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		camera_content = (LinearLayout) findViewById(R.id.camera_content);
		int beside = getWindowManager().getDefaultDisplay().getWidth();
		cameraView = new CameraView(this,beside,beside);
		LayoutParams layoutParams = new LayoutParams(beside, beside);
		camera_content.addView(cameraView, layoutParams);
	}

	private void initConfig() {
		// TODO Auto-generated method stub
		setFontAwesome(FontManager.getTypeface(getApplicationContext(),
				FontManager.FONTAWESOME));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static Typeface getFontAwesome() {
		return fontAwesome;
	}

	public static void setFontAwesome(Typeface fontAwesome) {
		MainActivity.fontAwesome = fontAwesome;
	}
}
