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

import com.dhongchuan.learingproject.util.AnimatorUtils;
import com.dhongchuan.learingproject.util.Utils;
import com.example.learningproject.R;

public class MenuLayout extends LinearLayout {
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

	private void toggleIsMenuOpen() {
		mIsMenuOpen = !mIsMenuOpen;
	}

	private void initView() {
		setOrientation(LinearLayout.VERTICAL);
		mContext = getContext();
		menuItemSize = 40;
		mIsMenuOpen = false;
		mIsAnimationRun = false;
		LinearLayout.LayoutParams imageWrapperLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ArrayList<Integer> id = new ArrayList<>();
        id.add(R.drawable.icn_1);
        id.add(R.drawable.icn_2);
        id.add(R.drawable.icn_3);
        id.add(R.drawable.icn_4);
        id.add(R.drawable.icn_5);
		for (int i = 0; i < 5; i++) {
			addView(Utils.getImageWrapper(mContext, 80, id.get(i), null), imageWrapperLayoutParams);
		}
//		resetAnimations();
		mAnimatorSetShowMenu = setOpenCloseAnimation(false);
		mAnimatorSetHideMenu = setOpenCloseAnimation(true);

	}

	private AnimatorSet setOpenCloseAnimation(boolean isCloseAnimation) {
		List<Animator> imageAnimations = new ArrayList<Animator>();
		if (isCloseAnimation) {
			for (int i = getChildCount() - 1; i >= 0; i--) {
				fillCloseAnimations(imageAnimations, i);
			}
		} else {
			for (int i = 0; i < getChildCount(); i++) {
				fillOpenAnimations(imageAnimations, i);
			}
		}

		AnimatorSet imageCloseAnimatorSet = new AnimatorSet();
		imageCloseAnimatorSet.playSequentially(imageAnimations);

		AnimatorSet animatorFullSet = new AnimatorSet();
		animatorFullSet.play(imageCloseAnimatorSet);
		// animatorFullSet.playTogether(imageCloseAnimatorSet,
		// textCloseAnimatorSet);
		animatorFullSet.setDuration(1000);
		// animatorFullSet.addListener(mCloseOpenAnimatorListener);
		animatorFullSet.setStartDelay(0);
		// animatorFullSet.setInterpolator(new HesitateInterpolator());
		return animatorFullSet;
	}

	private void fillOpenAnimations(List<Animator> imageAnimations,
			int wrapperPosition) {
		Animator imageRotation = wrapperPosition == 0 ? AnimatorUtils
				.rotationOpenFromRight(getChildAt(wrapperPosition))
				: AnimatorUtils
						.rotationOpenVertical(getChildAt(wrapperPosition));
		imageAnimations.add(imageRotation);
	}

	private void fillCloseAnimations(List<Animator> imageAnimations,
			int wrapperPosition) {
		Animator imageRotation = wrapperPosition == 0 ? AnimatorUtils
				.rotationCloseToRight(getChildAt(wrapperPosition))
				: AnimatorUtils
						.rotationCloseVertical(getChildAt(wrapperPosition));
		imageAnimations.add(imageRotation);
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
		view.setRotationX(-90);
		view.setRotationY(0);
		view.setPivotX(menuItemSize / 2);
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
		view.setPivotY(menuItemSize / 2);
	}

}
