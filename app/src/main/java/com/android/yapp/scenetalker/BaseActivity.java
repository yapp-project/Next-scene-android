package com.android.yapp.scenetalker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doFullScreen();
    }

    private void doFullScreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE|
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
