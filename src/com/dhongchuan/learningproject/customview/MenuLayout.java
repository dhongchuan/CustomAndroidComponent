package com.dhongchuan.learningproject.customview;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.learningproject.R;

public class MenuLayout extends RelativeLayout {
	private boolean mIsMenuOpen;
	private boolean mIsAnimationRun;
	private Context mContext;
	private int menuItemSize;
	
	private AnimatorSet mAnimatorSetHideMenu;
    private AnimatorSet mAnimatorSetShowMenu;

    
	public MenuLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initView();
	}

	public MenuLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView();
	}

	public MenuLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	public void menuToggle() {
        if (!mIsAnimationRun) {
            resetAnimations();
            mIsAnimationRun = true;
            if (mIsMenuOpen) {
                mAnimatorSetHideMenu.start();
            } else {
                mAnimatorSetShowMenu.start();
            }
            toggleIsMenuOpen();
        }
    }
	
	private void initView(){
		mContext = getContext();
		menuItemSize = 40;
		mIsMenuOpen = false;
		mIsAnimationRun = false;
		LinearLayout.LayoutParams imageWrapperLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		for(int i = 0; i < 5; i++){
			addView(getImageWrapper());
		}
		resetAnimations();
		mAnimatorSetShowMenu = setOpenCloseAnimation(false);
        mAnimatorSetHideMenu = setOpenCloseAnimation(true);

	}
	
	 private AnimatorSet setOpenCloseAnimation(boolean isCloseAnimation) {
	        List<Animator> textAnimations = new ArrayList<Animator>();
	        List<Animator> imageAnimations = new ArrayList<Animator>();

	        if (isCloseAnimation) {
	            for (int i = getChildCount() - 1; i >= 0; i--) {
	                fillOpenClosingAnimations(true, textAnimations, imageAnimations, i);
	            }
	        } else {
	            for (int i = 0; i < getChildCount(); i++) {
	                fillOpenClosingAnimations(false, textAnimations, imageAnimations, i);
	            }
	        }

	        AnimatorSet textCloseAnimatorSet = new AnimatorSet();
	        textCloseAnimatorSet.playSequentially(textAnimations);

	        AnimatorSet imageCloseAnimatorSet = new AnimatorSet();
	        imageCloseAnimatorSet.playSequentially(imageAnimations);

	        AnimatorSet animatorFullSet = new AnimatorSet();
	        animatorFullSet.playTogether(imageCloseAnimatorSet, textCloseAnimatorSet);
	        animatorFullSet.setDuration(1000);
//	        animatorFullSet.addListener(mCloseOpenAnimatorListener);
	        animatorFullSet.setStartDelay(0);
//	        animatorFullSet.setInterpolator(new HesitateInterpolator());
	        return animatorFullSet;
	    }
	
	private RelativeLayout getImageWrapper(){
		RelativeLayout imageWrapper = new RelativeLayout(getContext());
		LinearLayout.LayoutParams imageWrapperLayoutParams = new LinearLayout.LayoutParams(menuItemSize, menuItemSize);
        imageWrapper.setLayoutParams(imageWrapperLayoutParams);
//        imageWrapper.setBackgroundColor(mContext.getResources().getColor(R.color.menu_item_background));
//        imageWrapper.setOnClickListener(onCLick);
        imageWrapper.addView(getItemImageButton());
//        imageWrapper.addView(getDivider(context));
        return imageWrapper;
	}
	public ImageView getItemImageButton() {
        ImageView imageView = new ImageButton(mContext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(lp);
//        imageView.setPadding((int) context.getResources().getDimension(R.dimen.menu_item_padding),
//                (int) context.getResources().getDimension(R.dimen.menu_item_padding),
//                (int) context.getResources().getDimension(R.dimen.menu_item_padding),
//                (int) context.getResources().getDimension(R.dimen.menu_item_padding));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
        imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icn_1));
        imageView.setClickable(false);
        imageView.setFocusable(false);
        return imageView;
    }
	
	private void resetAnimations() {
		int count = getChildCount();
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                resetSideAnimation(getChildAt(i));
            } else {
                resetVerticalAnimation(getChildAt(i));
            }
        }
    }
	
	private void resetVerticalAnimation(View view) {
		view.setRotation(0);
		view.setRotationX(0);
		view.setRotationY(-90);
		view.setPivotX(menuItemSize/2);
		view.setPivotY(0);
    }

    /**
     * Set starting params to side animations
     */
    private void resetSideAnimation(View view) {
    	view.setRotation(0);
		view.setRotationX(0);
		view.setRotationY(-90);
		view.setPivotX(menuItemSize);
		view.setPivotY(menuItemSize/2);
    }

}
