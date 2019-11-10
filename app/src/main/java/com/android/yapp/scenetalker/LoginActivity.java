package com.android.yapp.scenetalker;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.yapp.scenetalker.databinding.ActivityLoginBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class LoginActivity extends BaseActivity{
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        WebSocket socket = new WebSocket();
        socket.connectWebSocket();
    }

    public void onClickLogin(View view){
        if(binding.idEdit.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this,"아이디를 입력 해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }else if(binding.passwordEdit.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this,"비밀번호를 입력 해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(binding.idEdit.getText().toString(),binding.passwordEdit.getText().toString());
        Call<JsonObject> service = NetRetrofit.getInstance().login(user);
        service.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Gson gson = new Gson();
                if(response.message() != null) {
                    Log.i("에러 결과", response.toString());
                }
                if(response.body() == null){
                    return;
                }
                Log.i("결과",response.body().toString());
                Utils.user_key = response.body().get("key").getAsString();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
    public void onClickSignUp(View view){
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }
}