package com.jhu.oose16.zombieattack.view.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jhu.oose16.zombieattack.view.attached.BitmapManager;
import com.jhu.oose16.zombieattack.view.attached.ModelViewScreenTrans;
import com.jhu.oose16.zombieattack.view.component.ViewComponent;
import com.jhu.oose16.zombieattack.view.component.ViewComponentManager;

public abstract class Menu<K> extends Activity {
	protected ViewComponentManager<K> viewComponentManager;

	@Override
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// lock the screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		Display display = getWindowManager().getDefaultDisplay();

		// deprecation with API 13
		ModelViewScreenTrans
				.initailize(display.getWidth(), display.getHeight());
		
		BitmapManager.setResources(getResources());

		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		initializeViewComponentManager();
		this.addContentView(viewComponentManager, p);
	}

	protected void initializeViewComponentManager() {
		viewComponentManager = new ViewComponentManager<K>(this);
	}

	public void addViewComponent(K key, ViewComponent viewComponent) {
		viewComponentManager.getViewComponentsWithKey().put(key, viewComponent);
	}
	
	public void removeViewComponent(K key) {
		viewComponentManager.getViewComponentsWithKey().remove(key);
	}

	public ViewComponentManager<K> getViewComponentManager() {
		return viewComponentManager;
	}

}
