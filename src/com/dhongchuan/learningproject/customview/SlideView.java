package com.dhongchuan.learningproject.customview;

import com.example.learningproject.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class SlideView extends LinearLayout {
	private LinearLayout mMainViewContainer;
	private RelativeLayout mFunctionViewContainer;
	private Scroller mScroller;
	private Context mContext;
	
	private final int fFuctionViewWidth = 150;
	private final int tan = 2;
	
	private OnSlideListener mSlideListener;
	
	private int mLastX = 0;
	private int mLastY = 0;
	
	public interface OnSlideListener{
		public static final int SLIDE_STATUS_OFF = 0;
		public static final int SLIDE_STATUS_START_SCROLL = 1;
		public static final int SLIDE_STATUS_ON = 2;
		
		public void onSLide(View view, int status);
	}

	public SlideView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public SlideView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SlideView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	private void initView(){
		mContext = getContext();
		mScroller = new Scroller(mContext);
		setOrientation(LinearLayout.HORIZONTAL);
		View.inflate(mContext, R.layout.list_item_slide_view, this);
		mMainViewContainer = (LinearLayout) findViewById(R.id.view_container);
		
	}
	
	public void setContentView(View view){
		mMainViewContainer.addView(view);
	}
	
	public void setOnSlideListener(OnSlideListener listener){
		mSlideListener = listener;
	}
	
	/**
	 * setting status as off
	 */
	public void shrink(){
		if(getScrollX() != 0){
			smoothScrollTo(0, 0);
		}
	}
	
	public void onCustomTouchEvent(MotionEvent event){
		int x = (int) event.getX();
		int y = (int) event.getY();
		int scrollX = getScrollX();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(!mScroller.isFinished()){
				mScroller.abortAnimation();
			}
			if(mSlideListener != null){
				mSlideListener.onSLide(this, OnSlideListener.SLIDE_STATUS_START_SCROLL);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaX = x - mLastX;
			int deltaY = y - mLastY;
			if(Math.abs(deltaX) < Math.abs(deltaY) * tan){
				break;
			}
			//计算滑动终点是否合法，防止滑动越界
			int newScrollX = scrollX - deltaX;
			if(deltaX != 0){
				if(newScrollX < 0){
					newScrollX = 0;
				}else if(newScrollX > fFuctionViewWidth){
					newScrollX = fFuctionViewWidth;
				}
				scrollTo(newScrollX, 0);
			}
			break;
		case MotionEvent.ACTION_UP:
			int newScrollX2 = 0;
			//判断松手时，向哪边滑动
			if(scrollX - fFuctionViewWidth * 0.75 > 0){
				newScrollX2 = fFuctionViewWidth;
			}
			smoothScrollTo(newScrollX2, 0);
			if(mSlideListener != null){
				mSlideListener.onSLide(this, newScrollX2==0?OnSlideListener.SLIDE_STATUS_OFF:OnSlideListener.SLIDE_STATUS_ON);
			}
			break;
		default:
			break;
			
			
		}
		mLastX = x;
		mLastY = y;
	}
	
	private void smoothScrollTo(int destX, int destY){
		int scrollX = getScrollX();
		int delta = destX - scrollX;
		
		mScroller.startScroll(scrollX, 0, delta, 0);
		invalidate();
	}

	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		if(mScroller.computeScrollOffset()){
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}
	

}
