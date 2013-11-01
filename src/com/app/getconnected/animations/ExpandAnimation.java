package com.app.getconnected.animations;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;


public class ExpandAnimation extends Animation implements Animation.AnimationListener {
	
	private View view;
	private static int ANIMATION_DURATION;
	private int LastHeight;
	private int ToHeight;

	/**
	 * Constructor
	 * @param v
	 * @param FromHeight
	 * @param ToHeight
	 * @param Duration
	 */
	public ExpandAnimation(View v, int FromHeight, int ToHeight, int Duration) {
		
		this.view = v;
		this.ToHeight = ToHeight;
		ANIMATION_DURATION = 1;
		setDuration(ANIMATION_DURATION);
		setRepeatCount(20);
		setFillAfter(false);
		setInterpolator(new AccelerateInterpolator());
		setAnimationListener(this);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		LayoutParams lyp =  view.getLayoutParams();
		lyp.height = LastHeight += ToHeight/20;
		view.setLayoutParams(lyp);
		view.requestFocus();
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		LayoutParams lyp =  view.getLayoutParams();
		lyp.height = 0;
		view.setLayoutParams(lyp);
		LastHeight = 0;
		
	}
}