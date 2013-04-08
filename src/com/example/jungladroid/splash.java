package com.example.jungladroid;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class splash extends Activity {

	AnimationSet animationSet;
	ImageView imagen;
	LinearLayout layout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		imagen = (ImageView) findViewById(R.id.logo);
		layout = (LinearLayout) findViewById(R.id.inicio);
		configurarAnimacion();
		imagen.startAnimation(animationSet);

	}

	public void configurarAnimacion() {
		animationSet = new AnimationSet(true);

		Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
		fadein.setDuration(100);
		Animation fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		fadeout.setDuration(600);
		fadeout.setStartOffset(2000); // Numero de milisegundos que quedara la
									// imagen antes de desaparecer
		animationSet.addAnimation(fadein);
		animationSet.addAnimation(fadeout);
		animationSet.setStartOffset(1000);

		animationSet.setAnimationListener(new AnimationListener() {

			public void onAnimationEnd(Animation animation) {
				layout.removeAllViews();
				Intent intent = new Intent(splash.this, Login.class);
				startActivity(intent);
				finish();
			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				//imagen.setImageResource(R.drawable.jungla320x480);
			}
		});

	}
}
