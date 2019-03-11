package com.example.hp.holyquran.Adapters;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.holyquran.R;
import com.example.hp.holyquran.SouraActivity;

import java.util.ArrayList;

public class SouraAdapter extends RecyclerView.Adapter<SouraAdapter.ViewHolder> {
    ArrayList<String> content;

    public void setAyaClick(OnItemClickListener ayaClick) {
        AyaClick = ayaClick;
    }

    OnItemClickListener AyaClick;
    public SouraAdapter(ArrayList<String> content) {
        this.content = content;

    }
 View view;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carditem_soura,viewGroup,false);


        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(content.get(i));
        if(AyaClick!=null){

             viewHolder.textView.setOnClickListener(new View.OnClickListener() {
        private boolean state;
                 @SuppressLint("ResourceAsColor")
                 @Override
                 public void onClick(View v) {
                     AyaClick.OnItemClick(viewHolder.itemView, i);
                 }
             });
        }
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
 TextView textView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.soura_text);

    }
}
public interface OnItemClickListener{
        void OnItemClick(View itemview, int postion);
}

}
