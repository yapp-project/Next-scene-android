package com.android.yapp.scenetalker;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("drama/")
    Call<JsonObject> getDramaList(@Query("page") int page);
    @GET("drama/")
    Call<JsonObject> getDramaList(@Query("onair") boolean onair,@Query("page")int page);
    @POST("rest-auth/registration/")
    Call<JsonObject> signup(@Body User user);
    @POST("rest-auth/login/")
    Call<JsonObject> login(@Body User user);
}