package jiyun.com.openresausechina.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import jiyun.com.openresausechina.R;

public class AnimationActivity extends AppCompatActivity {
    private ImageView imageView;
    private Animation animation;
    private boolean isAutoLogin;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animtion_activity);
        getSupportActionBar().hide();
        imageView = (ImageView) findViewById(R.id.image_animtion);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        imageView.startAnimation(animation);
        isAutoLogin = sharedPreferences.getBoolean("isAutoLogin", true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(AnimationActivity.this.isAutoLogin){
                    Intent intent = new Intent(AnimationActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(AnimationActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
