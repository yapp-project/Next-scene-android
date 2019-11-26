package com.android.yapp.scenetalker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChattingActivity extends AppCompatActivity {
    private RecyclerView recyclerView=null;
    private ChattingAdapter chattingAdapter=null;
    private List<ChattingInfo> dataList=null;
    private LottieAnimationView lottie;
    private WebSocket webSocket;
    private String drama_id;
    private String episode;
    private String user_id;
    private String drama_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chattingroom);
        init();
        setRecyclerView();
        setCurrentInfo();

        FloatingActionButton sweetpotato = findViewById(R.id.sweetpotato);
        lottie = findViewById(R.id.lottie_action);
        sweetpotato.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                lottie.setAnimation("sweet_potato.json");
                lottie.playAnimation();
                send_count("potato");
            }
        });
        FloatingActionButton cider = findViewById(R.id.cider);
        cider.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                lottie.setAnimation("saida.json");
                lottie.playAnimation();
                send_count("soda");
            }
        });
        Button button = findViewById(R.id.chattingbtn);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText editText = findViewById(R.id.Edit);
                String message = editText.getText().toString();
                JSONObject object = new JSONObject();
                try {
                    object.put("type","chat_message");
                    object.put("message",message);
                }catch (Exception e){
                    e.printStackTrace();
                }
                webSocket.mWebSocketClient.send(object.toString());
                editText.setText("");
            }
        });
        ImageButton close=findViewById(R.id.closebtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init(){
        drama_title = getIntent().getStringExtra("drama_title");
        TextView title = findViewById(R.id.title);
        title.setText(drama_title+" 실시간 톡방");
        recyclerView = findViewById(R.id.recyclerview2);
        dataList = new ArrayList<ChattingInfo>();
    }

    private void setCurrentInfo(){
        drama_id = getIntent().getStringExtra("drama_id");
        episode = getIntent().getStringExtra("drama_episode");
        String user_key = Utils.user_key;
        Log.i("토큰", user_key);
        Token token = new Token(user_key);
        Call<JsonObject> service = NetRetrofit.getInstance().getUser(token);
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
                JSONParser parser = new JSONParser();
                Object obj = null;
                try {
                    obj = parser.parse(response.body().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                JSONObject jsonObj = (JSONObject) obj;

                user_id = jsonObj.get("user_id").toString();

                webSocket = new WebSocket(handler, drama_id, episode, user_id);
                webSocket.connectWebSocket();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void setRecyclerView(){
        chattingAdapter = new ChattingAdapter(getApplicationContext(),R.layout.chattingitem,dataList);
        chattingAdapter.setHasStableIds(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chattingAdapter);
    }
    private void send_count(String kind){
        JSONObject object = new JSONObject();
        try {
            object.put("type","count");
            object.put("kind",kind);
            object.put("count",1);
        }catch (Exception e){
            e.printStackTrace();
        }
        webSocket.mWebSocketClient.send(object.toString());
    }

    class FABClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){

        }
    }

    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            final String message = bundle.getString("message");
            JSONParser p = new JSONParser();
            try {
                JSONObject obj = (JSONObject)p.parse(message);

                String sender = obj.get("sender").toString();
                String message_text = obj.get("message").toString();

                if (sender.equals("AdminServer")){
                    // Log.i("AdminServer", message_text);
                }
                else {
                    dataList.add(new ChattingInfo(sender, message_text));
                    chattingAdapter.notifyItemInserted(dataList.size());
                    recyclerView.scrollToPosition(dataList.size()-1);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };
}