package com.android.yapp.scenetalker;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("drama/")
    Call<JsonObject> getDramaList();
}