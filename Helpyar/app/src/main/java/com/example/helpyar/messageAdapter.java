package com.example.helpyar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class messageAdapter extends RecyclerView.Adapter <messageAdapter.messageItemViewHolder>{

    private ArrayList<messageItem> mMessageList;
private onLayoutItemClickListener monLayoutItemClickListener;
    public  class messageItemViewHolder extends  RecyclerView.ViewHolder implements  View.OnClickListener{

        public TextView chatUserTv;
        public TextView textTv;
        public TextView messageDateTv;
        public LinearLayout mesgLinLay;
        onLayoutItemClickListener onLayoutItemClickListener;
        public messageItemViewHolder(@NonNull View itemView,onLayoutItemClickListener onLayoutItemClickListener) {
            super(itemView);

            chatUserTv = itemView.findViewById(R.id.chatUser);
            textTv = itemView.findViewById(R.id.chatMessage);
            messageDateTv = itemView.findViewById(R.id.messageDate);
            mesgLinLay=itemView.findViewById(R.id.messageitemlinearlayput) ;
            this.onLayoutItemClickListener=onLayoutItemClickListener;
itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
onLayoutItemClickListener.onNoteClick(getAdapterPosition());
        }
    }

    public messageAdapter(ArrayList<messageItem> messageList,onLayoutItemClickListener onLayoutItemClickListener){
        mMessageList = messageList;
        monLayoutItemClickListener=onLayoutItemClickListener;
    }
    @NonNull
    @Override
    public messageItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent,false);

        messageItemViewHolder mvh = new messageItemViewHolder(v,monLayoutItemClickListener);

        return  mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull messageItemViewHolder holder, int position) {

        final messageItem currentItem = mMessageList.get(position);

        holder.chatUserTv.setText(currentItem.getChatUser());
        holder.textTv.setText(currentItem.getText());
        holder.messageDateTv.setText(currentItem.getMessage_date());


    }
    public interface onLayoutItemClickListener{
        void onNoteClick(int position);
    }
    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}
