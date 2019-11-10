package com.android.yapp.scenetalker;

import android.os.Build;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;

public class WebSocket{
    WebSocketClient mWebSocketClient;
    public void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://13.209.53.205/ws/chat/test/");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("에러","URL 에러");
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                JSONObject object = new JSONObject();
                try {
                    object.put("message","저 먼저 갈게요");
                }catch (Exception e){
                    e.printStackTrace();
                }
                mWebSocketClient.send(object.toString());
//                mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                Log.i("받은 메시지",message);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }
}
