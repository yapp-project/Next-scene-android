package com.android.yapp.scenetalker;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.android.yapp.scenetalker.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity{
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
    }

    public void onClickLogin(View view){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }
    public void onClickSignUp(View view){
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }
}