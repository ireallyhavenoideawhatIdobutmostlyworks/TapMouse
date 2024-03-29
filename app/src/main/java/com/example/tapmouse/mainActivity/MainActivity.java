package com.example.tapmouse.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tapmouse.R;
import com.example.tapmouse.gameActivity.MouseActivity;
import com.example.tapmouse.settingsActivity.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private ActivityMainPresenter presenterGame;

    @BindView(R.id.settings)
    ImageView wheel;

    public static int heightWindow;
    public static int widthWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);
        presenterGame = new ActivityMainPresenterImpl(this);

        getSupportActionBar().hide();

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        heightWindow = outMetrics.heightPixels;
        widthWindow = outMetrics.widthPixels;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_wheel);
        wheel.startAnimation(animation);
    }

    @OnClick(R.id.exitButton)
    public void clickExit(View view) {
        finish();
    }

    @OnClick(R.id.playButton)
    public void clickPlay() {
        presenterGame.startActivityGame();
    }

    @OnClick(R.id.settings)
    public void clickSettings() {
        presenterGame.startActivitySettings();
    }

    public void startActivitySettings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void startActivityGame() {
        Intent intent = new Intent(this, MouseActivity.class);
        startActivity(intent);
    }
}
