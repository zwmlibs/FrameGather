package com.zm.framegather.util;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zm.framegather.R;


public class CustomDialog extends Dialog{

	public CustomDialog(Context context,String msg) {
		super(context, R.style.progress_style_dialog);
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);

		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
		spaceshipImage = (ImageView) v.findViewById(R.id.img);
		tipTextView = (TextView) v.findViewById(R.id.tipTextView);
		hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context,R.anim.loading_animation);
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		tipTextView.setText(msg);

		setContentView(
				layout,
				new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.MATCH_PARENT));
	}
	private Animation hyperspaceJumpAnimation;
	private ImageView spaceshipImage;
	private TextView tipTextView;

	@Override
	public void addContentView(View view, LayoutParams params) {
		super.addContentView(view, params);
	}


	public void setText(String msg) {
		tipTextView.setText(msg);
	}
	
	@Override
	public void show() {
		super.show();
		startAnimation();
	}
	
	private void startAnimation(){
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
	}



}
