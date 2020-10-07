package com.example.helpyar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class homeAdapter extends RecyclerView.Adapter <homeAdapter.homeItemViewHolder>{

    private ArrayList<homeItem> mHomeItemsList;

    public  class homeItemViewHolder extends  RecyclerView.ViewHolder{

        public TextView titleTv;
        public TextView amountTv;
        public TextView statusTv;
        public TextView dateTv;

        public homeItemViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.homeJobTitle);
            amountTv = itemView.findViewById(R.id.homeJobAmount);
            statusTv = itemView.findViewById(R.id.homeJobAssigned);
            dateTv = itemView.findViewById(R.id.homeJobDate);

        }
    }

    public homeAdapter(ArrayList<homeItem> homeItemList){
        mHomeItemsList = homeItemList;
    }
    @NonNull
    @Override
    public homeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent,false);

        homeItemViewHolder hvh = new homeItemViewHolder(v);

        return  hvh;
    }

    @Override
    public void onBindViewHolder(@NonNull homeItemViewHolder holder, int position) {

        homeItem currentItem = mHomeItemsList.get(position);

        holder.titleTv.setText(currentItem.getTitle());
        holder.amountTv.setText(currentItem.getAmount());
        holder.statusTv.setText(currentItem.getJobStatus());
        holder.dateTv.setText(currentItem.getPosted_date());

    }

    @Override
    public int getItemCount() {
        return mHomeItemsList.size();
    }
}
