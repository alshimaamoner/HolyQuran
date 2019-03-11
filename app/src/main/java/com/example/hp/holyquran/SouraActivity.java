package com.example.hp.holyquran;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hp.holyquran.API.Model.APIManager;
import com.example.hp.holyquran.API.Model.AyaResponse;
import com.example.hp.holyquran.API.Model.RadiosItem;
import com.example.hp.holyquran.Adapters.AyaAdapter;
import com.example.hp.holyquran.Adapters.SouraAdapter;
import com.example.hp.holyquran.Base.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SouraActivity extends BaseActivity {
    ArrayList<String> soura;
    SouraAdapter adapter;
    AyaAdapter adapter2;
    RecyclerView.LayoutManager manager,manager2;
    RecyclerView recyclerView,AyaRecyle;
    TextView soura_name;
   public String ayat="",s="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soura);
       String name= getIntent().getStringExtra("name");
       final String File=getIntent().getStringExtra("filename");
       final int index= getIntent().getIntExtra("id",0);
       soura_name=findViewById(R.id.soura_name);
       soura_name.setText(name);
      ReadFile(File);
       recyclerView=findViewById(R.id.recylerView);
       adapter=new SouraAdapter(soura);
       manager=new LinearLayoutManager(SouraActivity.this,LinearLayoutManager.VERTICAL,false);
       recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        adapter2=new AyaAdapter(null);
        manager2=new LinearLayoutManager(SouraActivity.this,LinearLayoutManager.HORIZONTAL,false);
        AyaRecyle=findViewById(R.id.AyaRecyle);
        AyaRecyle.setAdapter(adapter2);
        AyaRecyle.setLayoutManager(manager2);

        adapter.setAyaClick(new SouraAdapter.OnItemClickListener() {
          @SuppressLint("ResourceAsColor")
          @Override
          public void OnItemClick(View itemView,int postion) {
              getAya();
              ayat = index + "";
              s = (postion + 1) + "";
              while (ayat.length() < 3)
                  ayat = "0" + ayat;
              while (s.length() < 3){
                  if(index==1) {
                      s = "00" + (postion + 2);
                  }else{
                          s = "0" + s;
                      }}



              String URL = adapter2.url+ ayat + s + ".mp3";
              PlayChannel(URL);
              StopChannel();

          }
      });

    }
    @Override
    protected void onPause() {
        super.onPause();
        StopChannel();

    }

    @Override
    protected void onStart() {
        super.onStart();
        StopChannel();
    }

    public void getAya() {
        APIManager.getAPIs().getAllAya().enqueue(new Callback<AyaResponse>() {

            @Override
            public void onResponse(Call<AyaResponse> call, Response<AyaResponse> response) {

                if (response.isSuccessful()) {
                    List<RadiosItem> channel = response.body().getRadios();
                    adapter2.changeData(response.body().getRadios());

                }
            }


            @Override
            public void onFailure(Call<AyaResponse> call, Throwable t) {

                showMessage("error", t.getLocalizedMessage(), "ok");
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
            showMessage("error","Click again","ok");
        }


    }
    public void StopChannel(){
        if(mediaPlayer!=null)

            mediaPlayer.stop();

    }

    public ArrayList<String> ReadFile(String FileName){
        BufferedReader reader;
          soura=new ArrayList<String>();

        try{
            final InputStream file = getAssets().open(FileName);
            reader = new BufferedReader(new InputStreamReader(file));
            String line;
            int i=1;
            while((line=reader.readLine()) != null){
               // Log.d("StackOverflow", line);
                if(line.endsWith("")){
                    line=line+"{"+(i++)+"}";

                }else if(line.isEmpty()){
                    break;
                }
                soura.add(line);

            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
        return soura;
    }




}
