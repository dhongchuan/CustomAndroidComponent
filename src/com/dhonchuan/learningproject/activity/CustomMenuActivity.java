package com.dhonchuan.learningproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dhongchuan.learningproject.customview.MenuLayout;
import com.example.learningproject.R;

public class CustomMenuActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_menu);
		Button button = (Button) findViewById(R.id.button);
		final MenuLayout menu = (MenuLayout) findViewById(R.id.menu);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				menu.menuToggle();
			}
		});
		
	}

}
