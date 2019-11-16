
package com.android.yapp.scenetalker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.yapp.scenetalker.FeedPage.drama_id;

public class fPagerAdapter extends PagerAdapter {
    private Context mContext = null ;
    TextView episode_result,potato_percent,cider_percent;
    static int potato_count,cider_count;
    String episode_num;
    static int potato_pc,cider_pc;
    FeedPage fp;
    CountInfo item;
    ProgressBar progressBar;

    public fPagerAdapter(Context context) {
        mContext = context ;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null ;
        int drama_id=FeedPage.drama_id;
        String episode=fp.episode;
        if (mContext != null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_feed, container, false);
            episode_result=view.findViewById(R.id.episode_result);
            cider_percent=view.findViewById(R.id.cider_percent);
            potato_percent=view.findViewById(R.id.potato_percent);
            progressBar=view.findViewById(R.id.progressbar);
            getItems(2);


        }

        container.addView(view) ;

        return view ;
    }

    @Override
    public int getCount() {
        return 1;
    }
    @Override
    public float getPageWidth (int position) {
        return 0.9f;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }

    public void getItems(int itempage){
        Call<JsonArray> call2 = NetRetrofit.getInstance().getDramaCount(drama_id);
        call2.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Gson gson = new Gson();
                if(response.body()==null)
                    return;
                JsonArray array = response.body().getAsJsonArray();

                try{
                    Log.v("Text",response.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }
                ArrayList<CountInfo> counts=new ArrayList<>();

                for(int i=0;i<array.size();i++){
                    CountInfo info = gson.fromJson(array.get(i),CountInfo.class);

                    episode_num=info.getEpisode();
                    potato_count=info.getSweet_potato_count();
                    cider_count=info.getSoda_count();
                    potato_pc=(int)((double)potato_count/(double)(potato_count+cider_count)*100);
                    cider_pc=100-potato_pc;

                    episode_result.setText(episode_num+"차 결과");
                    potato_percent.setText(potato_pc+"%");
                    cider_percent.setText(cider_pc+"%");
                    progressBar.setProgress(potato_pc);
                    if(info != null)
                        counts.add(info);
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("err",t.getMessage());
                call.cancel();
            }
        });


    }
}

