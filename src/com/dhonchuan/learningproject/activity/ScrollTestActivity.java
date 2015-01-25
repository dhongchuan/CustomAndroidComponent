package com.dhonchuan.learningproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.learningproject.R;

public class ScrollTestActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll_test);
		
		Button upButton = (Button) findViewById(R.id.up_button);
		Button downButton = (Button) findViewById(R.id.down_button);
		final TextView textView = (TextView) findViewById(R.id.textview);
		
		upButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView.scrollBy(10, 10);
				Log.d("upgetX", String.valueOf(textView.getX()));
				Log.d("upgetY", String.valueOf(textView.getY()));
				Log.d("ScrollX", String.valueOf(textView.getScrollX()));
				Log.d("ScrollY", String.valueOf(textView.getScrollY()));
			}
		});
		downButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView.scrollBy(-10, -10);
				Log.d("downgetX", String.valueOf(textView.getX()));
				Log.d("getY", String.valueOf(textView.getY()));
				Log.d("ScrollX", String.valueOf(textView.getScrollX()));
				Log.d("ScrollY", String.valueOf(textView.getScrollY()));
			}
		});
	}
	

}
