package com.example.hp.holyquran.API;

import com.example.hp.holyquran.API.Model.AyaResponse;
import com.example.hp.holyquran.API.Model.RadioResponse;
import com.example.hp.holyquran.API.Model.RecuitersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apicalls {
    @GET("radio/radio_ar.json")
    Call<RadioResponse> getAllRadio();
    @GET("_arabic.json")
    Call<RecuitersResponse> getAllRecuiter();
    @GET("verse/radio_ar.json")
    Call<AyaResponse> getAllAya();



}
