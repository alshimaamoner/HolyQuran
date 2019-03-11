package com.example.hp.holyquran.Adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.holyquran.API.Model.RadiosItem;
import com.example.hp.holyquran.R;

import java.util.List;

public class AyaAdapter  extends RecyclerView.Adapter<AyaAdapter.ViewHolder> {
    public List<RadiosItem> AyaChannel;


 public AyaAdapter(List<RadiosItem> AyaChannel) {
        this.AyaChannel = AyaChannel;
    }


    @NonNull
    @Override
    public AyaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.aya_item, viewGroup, false);

        return new ViewHolder(view);
    }
public static  String  url;

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AyaAdapter.ViewHolder viewHolder, final int i) {
        final RadiosItem channel = AyaChannel.get(i);
        viewHolder.textView.setText(channel.getName());
                   url=AyaChannel.get(i).getURL();



    }


    public void changeData(List<RadiosItem> radiosItems) {
        this.AyaChannel = radiosItems;
       int j=0;
        while (j<AyaChannel.size()-1){
            if(AyaChannel.get(j).getName().equals(AyaChannel.get(j+1).getName()))
                AyaChannel.remove(j);
            j++;
        }

        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        if (AyaChannel == null)
            return 0;

        return AyaChannel.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.ayaText);

        }
    }

}


