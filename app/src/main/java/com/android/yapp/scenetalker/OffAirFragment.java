package com.android.yapp.scenetalker;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class OffAirFragment extends Fragment {

    RecyclerView recyclerView;
    OffAirAdapter adapter;
    Context context;
    OnTabItemSelectedListener listener;
    ArrayList<DramaInfo> dramas = new ArrayList<>();

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
        if(context instanceof OnTabItemSelectedListener){
            listener = (OnTabItemSelectedListener)context;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();

        if(context != null) {
            context = null;
            listener = null;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_offair, container, false);
        initUi(rootView);
        return rootView;
    }
    private void initUi(ViewGroup rootView) {
//        Button gotofeed = rootView.findViewById(R.id.gotofeed);
//        gotofeed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        recyclerView = rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new OffAirAdapter();
        recyclerView.setAdapter(adapter);
        Call<JsonObject> service = NetRetrofit.getInstance().getService().getDramaList();
        service.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Gson gson = new Gson();
                Log.i("코드",""+response.code());
                Log.i("바디",response.body().toString());
                JsonArray array = response.body().getAsJsonArray("results");
                for(int i=0;i<array.size();i++){
                    DramaInfo info = gson.fromJson(array.get(i),DramaInfo.class);
                    if(info != null)
                        dramas.add(info);
                }
                adapter.setItems(dramas);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
