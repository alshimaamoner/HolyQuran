package com.example.hp.holyquran;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.holyquran.API.Model.APIManager;
import com.example.hp.holyquran.API.Model.RadioResponse;
import com.example.hp.holyquran.API.Model.RadiosItem;
import com.example.hp.holyquran.API.Model.RadiosItems;
import com.example.hp.holyquran.Adapters.ListenningAdapter;
import com.example.hp.holyquran.Base.BaseFragment;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListenFragment extends BaseFragment {


    public ListenFragment() {
        // Required empty public constructor
    }
View view;
RecyclerView recyclerView;
ListenningAdapter adapter;
RecyclerView.LayoutManager manager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                view= inflater.inflate(R.layout.fragment_listen, container, false);
                recyclerView=view.findViewById(R.id.listen_recyle);
                adapter=new ListenningAdapter(null);
                manager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
        SnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
                  adapter.setPlayonItemClickListener(new ListenningAdapter.OnItemClickListener() {
                      @Override
                      public void OnItemClick(int pos, RadiosItems channel) {
                            PlayChannel(channel.getURL());

                      }
                  });
        adapter.setStoponItemClickListener(new ListenningAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos, RadiosItems channel) {
                    StopChannel();
            }
        });
        getRadious();
        return view;
    }
    public  void getRadious(){
        showProgressBar();
        APIManager.getAPIs().getAllRadio().enqueue(new Callback<RadioResponse>() {
            @Override
            public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    List<RadiosItems> channel=response.body().getRadios();
                        adapter.changeData(response.body().getRadios());
                }
            }

            @Override
            public void onFailure(Call<RadioResponse> call, Throwable t) {
                hideProgressBar();
                showMessage("error",t.getLocalizedMessage(),"ok");
            }
        });
    }
    MediaPlayer mediaPlayer;
    public void PlayChannel(String URL)  {
        StopChannel();
        mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(URL);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IOException e) {
            showMessage("error","cannot play","ok");
        }


    }
    public void StopChannel(){
        if(mediaPlayer!=null)
            mediaPlayer.stop();

    }

}
