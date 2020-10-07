package com.example.helpyar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class postjobadapter extends RecyclerView.Adapter <postjobadapter.jobItemViewHolder>{
private ArrayList<jobposteditem> mJobList;
    public  class jobItemViewHolder extends  RecyclerView.ViewHolder{
            public TextView titleTv;
        public TextView amountTv;
        public TextView statusTv;
        public TextView dateTv;

        public jobItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv=itemView.findViewById(R.id.postedjobtitle);
            amountTv=itemView.findViewById(R.id.postedjobamount);
                    statusTv=itemView.findViewById(R.id.postedjobstatus);
            dateTv=itemView.findViewById(R.id.postedjobdate);
        }
    }

    public postjobadapter(ArrayList<jobposteditem>jobList){
mJobList=jobList;
    }
    @NonNull
    @Override
    public jobItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.postedjobitem,parent,false);
        jobItemViewHolder jvh=new jobItemViewHolder(v);
        return  jvh;
    }

    @Override
    public void onBindViewHolder(@NonNull jobItemViewHolder holder, int position) {

        jobposteditem currentItem=mJobList.get(position);
        holder.titleTv.setText(currentItem.getTitle());
        holder.amountTv.setText(currentItem.getAmount());
        holder.statusTv.setText(currentItem.getJobStatus());
        holder.dateTv.setText(currentItem.getPosted_date());

    }

    @Override
    public int getItemCount() {
        return mJobList.size();
    }
}
