package com.example.hp.holyquran;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hp.holyquran.Base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class sebhaFragment extends BaseFragment {
    private TextView mention_counter;
    private TextView totalMentionCounter;
    private ImageView reset,sebha;
    private int counter;
    int total;
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.imageView:
                    plusCounter();
                    break;
                case R.id.reset:
                    initCounter();
                    break;
            }
        }
    };

    public sebhaFragment() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sebha, container, false);

        final Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_arr, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView)spinner.getSelectedView()).setTextColor(Color.parseColor("#fac14c"));
                ((TextView)spinner.getSelectedView()).setTextSize(20);

            }
        });
        spinner.setAdapter(adapter);


        mention_counter = view.findViewById(R.id.mention_counter);
       // mention_counter.setOnClickListener(clickListener);

        totalMentionCounter = view.findViewById(R.id.totalMentionCounter);
          sebha=view.findViewById(R.id.imageView);
          sebha.setOnClickListener(clickListener);

        reset = view.findViewById(R.id.reset);
        reset.setOnClickListener(clickListener);
       // initCounter();

     //   mention_counter = view.findViewById(R.id.mention_counter);
       // mention_counter.setOnClickListener(clickListener);

    //    totalMentionCounter = view.findViewById(R.id.totalMentionCounter);

     /*   reset = view.findViewById(R.id.reset);
        reset.setOnClickListener(clickListener);
        initCounter();*/
        return view;

    }

    public void initCounter() {
        counter = 0;
        mention_counter.setText(counter + "");
        totalMentionCounter.setText(counter+"");
    }

    public void plusCounter() {
        counter++;
        mention_counter.setText(counter + "");
        totalMentionCounter.setText(counter + "");
    }

}




