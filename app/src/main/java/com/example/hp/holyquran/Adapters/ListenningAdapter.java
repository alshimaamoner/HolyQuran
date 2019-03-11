package com.example.hp.holyquran.Adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.holyquran.API.Model.RadiosItem;
import com.example.hp.holyquran.API.Model.RadiosItems;
import com.example.hp.holyquran.R;

import java.util.List;


public class ListenningAdapter extends RecyclerView.Adapter<ListenningAdapter.ViewHolder> {
 List<RadiosItems> channels;
OnItemClickListener PlayonItemClickListener;
OnItemClickListener StoponItemClickListener;

    public void setPlayonItemClickListener(OnItemClickListener playonItemClickListener) {
        PlayonItemClickListener = playonItemClickListener;
    }

    public void setStoponItemClickListener(OnItemClickListener stoponItemClickListener) {
        StoponItemClickListener = stoponItemClickListener;
    }

    public ListenningAdapter(List<RadiosItems> channels) {
        this.channels = channels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listen_item,viewGroup,false);

       return new ViewHolder(view);
    }


    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
      final RadiosItems channel=channels.get(i);
      viewHolder.textView.setText(channel.getName());
      if(PlayonItemClickListener!=null)
          viewHolder.play.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  PlayonItemClickListener.OnItemClick(i,channel);
              }
          });
        if(StoponItemClickListener!=null)
            viewHolder.stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StoponItemClickListener.OnItemClick(i,channel);
                }
            });


    }
 public void   changeData(List<RadiosItems> radiosItems){
     this.channels=radiosItems;
     notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(channels==null)
            return 0;

        return channels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

      TextView textView;
      ImageView play,stop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.kare2_name);
            play=itemView.findViewById(R.id.play);
            stop=itemView.findViewById(R.id.stop);
        }
    }

    public interface OnItemClickListener{
        public void OnItemClick(int pos, RadiosItems channel);
    }
}
