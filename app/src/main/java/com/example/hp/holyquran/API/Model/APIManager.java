package com.example.hp.holyquran.API.Model;

import com.example.hp.holyquran.API.Apicalls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIManager {

    private static Retrofit retrofitInstance;
    private static Retrofit getInstance(){
        if(retrofitInstance==null){//create
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl("http://www.mp3quran.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }

   public static Apicalls getAPIs(){
        Apicalls services = getInstance().create(Apicalls.class);
        return services;
    }
}
