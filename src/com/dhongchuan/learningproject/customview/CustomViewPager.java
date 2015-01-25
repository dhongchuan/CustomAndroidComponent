package com.dhongchuan.learningproject.customview;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomViewPager extends ViewGroup{
	private int lastX=0;
	private int lastY=0;

	public CustomViewPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initView();
	}

	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView();
	}

	public CustomViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	@SuppressLint("ResourceAsColor")
	private void initView(){
		TextView viewone = new TextView(getContext());
		viewone.setText("first page");
		viewone.setBackgroundColor(R.color.darker_gray);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		addView(viewone, lp);
		TextView viewtwo = new TextView(getContext());
		viewtwo.setText("second page");
		viewtwo.setBackgroundColor(R.color.holo_orange_dark);
		addView(viewtwo, lp);
		
		
	}
	
	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		final int count = getChildCount();
		
		for(int i = 0; i < count; i++){
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		final int count = getChildCount();
		View view;
		int left = 0;
		int right = 0;
		for(int i = 0; i < count; i++){
			view = getChildAt(i);
			right += view.getMeasuredWidth()/2;
			view.layout(left, 0, right, getHeight());
			left += view.getMeasuredWidth()/2;
			Log.d("left", String.valueOf(left));
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int currentX = (int) event.getX();
		int currentY = (int) event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d("Event", "Down");
			int deltat = lastX - currentX;
			Log.d("lastX", String.valueOf(lastX));
			scrollBy(deltat, 0);
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d("Event", "Move");
			int delta = lastX - currentX;
			Log.d("lastX", String.valueOf(lastX));
			scrollBy(delta, 0);
			invalidate();
			break;
		case MotionEvent.ACTION_SCROLL:
			Log.d("Event", "Scroll");
			break;
		case MotionEvent.ACTION_UP:
			Log.d("Event", "Up");
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		default:
			break;
		}
		lastX = currentX;
		return true;
	}
	
	

}
