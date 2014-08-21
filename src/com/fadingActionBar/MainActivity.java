package com.fadingActionBar;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fadingActionBar.ScrollViewX.OnScrollViewListener;

public class MainActivity extends Activity {

	private ActionBar mActionBar;
	private TextView actionBarText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black);
		final BitmapDrawable bd = new BitmapDrawable(bitmap);
		final ColorDrawable cd = new ColorDrawable(Color.rgb(68, 74, 83));
		mActionBar = getActionBar();
		mActionBar.setBackgroundDrawable(cd);
		
		cd.setAlpha(0);

		mActionBar.setDisplayHomeAsUpEnabled(true); //to activate back pressed on home button press
		mActionBar.setDisplayShowHomeEnabled(false); //
		mActionBar.setTitle(Html.fromHtml("<b><font color='#ffffff'>Powai </font></b>"));
		mActionBar.setSubtitle(Html.fromHtml("<font color='#ffffff'>Hiranandani Gardens </font>"));
		
		
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		addTextViews(ll);

		
		
		ScrollViewX scrollView = (ScrollViewX) findViewById(R.id.scroll_view);
		scrollView.setOnScrollViewListener(new OnScrollViewListener() {
			
			@Override
			public void onScrollChanged(ScrollViewX v, int l, int t, int oldl, int oldt) {
				
				cd.setAlpha(getAlphaforActionBar(v.getScrollY()));
			}

			private int getAlphaforActionBar(int scrollY) {
				int minDist = 0,maxDist = 650;
				if(scrollY>maxDist){ 
					return 255;
					}
				else if(scrollY<minDist){
					return 0;
					}
				else {
					int alpha = 0;
					alpha = (int)  ((255.0/maxDist)*scrollY);
					return alpha;
				}
			}
		});
		
	}

	
	private void addTextViews(LinearLayout ll) {
		
		for (int i = 0; i < 26; i++) {
			TextView tv1 = new TextView(this);
			tv1.setText(String.valueOf(i));
			tv1.setTextSize(10);
			tv1.setWidth(500);
			tv1.setHeight(500);
			tv1.setBackgroundColor(Color.rgb(255-10*i, 255-10*i, 255-10*i)); //just for fun , varying back grounds
			tv1.setGravity(Gravity.CENTER);
			ll.addView(tv1);
			
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
