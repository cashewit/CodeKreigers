package com.example.codekreigers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DevCardAdapter extends RecyclerView.Adapter<DevCardAdapter.MyViewHolder> {

    private List<DevCard> mDevCards;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView devImageHolder;
        public TextView devYear, devName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            devImageHolder = itemView.findViewById(R.id.devImage);
            devYear = itemView.findViewById(R.id.devYear);
            devName = itemView.findViewById(R.id.devName);
        }
    }

    DevCardAdapter(List<DevCard> devCards, Context mContext){
        this.mDevCards = devCards;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DevCardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dev_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        DevCard devCard = mDevCards.get(position);
        myViewHolder.devImageHolder.setImageResource(devCard.getDevImg());
        myViewHolder.devName.setText(devCard.getName());
        myViewHolder.devYear.setText(devCard.getYear());
    }

    @Override
    public int getItemCount() {
        return mDevCards.size();
    }


}
