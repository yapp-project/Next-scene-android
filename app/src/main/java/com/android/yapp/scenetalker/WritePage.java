package com.android.yapp.scenetalker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePage extends AppCompatActivity {
    private final int GET_GALLERY_IMAGE = 1;
    private File tempFile;
    TextView dramaname;
    EditText write_ed;
    ImageButton image_btn;
    ImageView write_imageView;
    Button finish;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.write_page);
        final Intent intent = new Intent(this.getIntent());
        String drama_title = intent.getExtras().getString("name");

        finish = (Button) findViewById(R.id.finish_btn);
        image_btn = (ImageButton) findViewById(R.id.image_btn);
        write_ed = (EditText) findViewById(R.id.feed_write_et);
        write_imageView = (ImageView) findViewById(R.id.write_imageview);
        dramaname = (TextView) findViewById(R.id.dramaname);
        dramaname.setText(drama_title);
        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostInfo postInfo = new PostInfo(write_ed.getText().toString());
                Call<JsonObject> service = NetRetrofit.getInstance().feed(postInfo,"44");
                service.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Gson gson = new Gson();
                        if (response.message() != null) {
                            Log.i("에러 결과", response.toString());
                        }
                        if (response.body() == null) {
                            return;
                        }
                        Log.i("결과", response.body().toString());

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",response.body().toString());
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
                //Intent intent = new Intent(WritePage.this,FeedPage.class);
                //finish();
                //startActivityForResult(intent,1);

            }

        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            write_imageView.setImageURI(selectedImageUri);
        }
    }
}