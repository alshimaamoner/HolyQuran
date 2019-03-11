package com.example.hp.holyquran;


import android.content.DialogInterface;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hp.holyquran.Adapters.AhadethAdapter;
import com.example.hp.holyquran.Base.BaseFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ahadeth extends BaseFragment {
    AhadethAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    ArrayList<String> data;
    ArrayList<String> ahadethData;

    public Ahadeth() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ahadeth, container, false);
        recyclerView = view.findViewById(R.id.Recyle_View);
        adapter = new AhadethAdapter(ReadFile());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.setOnItemClickListener(new AhadethAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos, String name) {
                  LoadData(data);

                showConfirmMessage(name, ahadethData.get(pos), new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        onDestroyView();
                    }
                });


            }
        });
        return view;
    }

    public ArrayList<String> ReadFile(){
        BufferedReader reader;
        data=new ArrayList<String>();


        try{
            final InputStream file = getContext().getAssets().open("ahadeth.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line=reader.readLine()) != null){
                if (line.contains("الحديث")) {
                    data.add(line);

                }else if(line.isEmpty()){
                   continue;
                }
            }

            }catch(IOException ioe){
            ioe.printStackTrace();
        }
return  data;
    }
public ArrayList<String> LoadData(ArrayList<String> data){
    ahadethData=new ArrayList<String>();
    BufferedReader reader;
    String s="";

        try{
            final InputStream file = getContext().getAssets().open("ahadeth.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line=reader.readLine()) != null){
                if(line.contains("الحديث")){
                    continue;
                }
               else if (!line.contains("#")) {
                    s+=line;
                }else{
                    ahadethData.add(s);
                    s="";
                }

            }


        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    return ahadethData;
}

    }




