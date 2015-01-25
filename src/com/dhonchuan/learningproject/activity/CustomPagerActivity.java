package com.dhonchuan.learningproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.example.learningproject.R;

public class CustomPagerActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_viewpager);
		
		Button one = (Button) findViewById(R.id.one);
		final Button two = (Button) findViewById(R.id.two);
		final Button three = (Button) findViewById(R.id.three);
		
		AnimationSet set = (AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.test);
		final TranslateAnimation animOne = (TranslateAnimation) set.getAnimations().get(0);
		final TranslateAnimation animTwo = (TranslateAnimation) set.getAnimations().get(1);
		animOne.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				animation = new TranslateAnimation(0,0,0,0);
				two.startAnimation(animation);
				
			}
		});
		one.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				two.startAnimation(animOne);
				three.startAnimation(animTwo);
			}
		});
	}
	

}
