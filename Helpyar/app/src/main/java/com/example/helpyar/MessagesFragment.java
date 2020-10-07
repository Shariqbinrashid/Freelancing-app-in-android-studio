/*package com.example.helpyar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessagesFragment extends Fragment {


    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

}*/

package com.example.helpyar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MessagesFragment extends Fragment implements messageAdapter.onLayoutItemClickListener {


    String user_id;

    Button newButton;
    String chatUserId;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_messages, container, false);

        HomeActivity activity = (HomeActivity) getActivity();

        user_id = activity.getUser_id();


        ArrayList<messageItem> messageItemList = new ArrayList<>();

        //messageItemList.add(new messageItem("chat user","text message to send","Date"));

        //FETCHING DATA FROM DATABASE AND ADDING IT TO THE LIST TO BE DISPLAYED IN RECYCLER VIEW

        database db = new database(getContext());
        final SQLiteDatabase sql=db.getWritableDatabase();

        String senderId;
        String recieverId;

        String chatUserFName;
        String chatUserLName;
        String messageText;
        String messageDate;


        //select fname, lname, message from user, message where user_id in (select sender_id from chatmessage where reciever_id = '1') or user_id in (select reciever_id from chatmessage where sender_id = '1')

        //Cursor c= sql.rawQuery("select * from Job where seller_id = ' " + user_id + "'",null);

        //Cursor c= sql.rawQuery("select fname, lname, message from user, message where user_id in (select sender_id from chatmessage where reciever_id = ' " + user_id + "') or user_id in (select reciever_id from chatmessage where sender_id = '" + user_id + "') order by message_time",null);

        //select message, message_time, sender_id, reciever_id from message, chatmessage where message.message_id = chatmessage.message_id and message.message_id in (select chatmessage.message_id from chatmessage where sender_id = '1' or reciever_id = '1')

        //select message, message_time, sender_id, reciever_id from message, chatmessage where message.message_id = chatmessage.message_id and message.message_id in (select chatmessage.message_id from chatmessage where sender_id = '1' or reciever_id = '1')

        //Cursor c = sql.rawQuery("select message, message_time, sender_id, reciever_id from message, chatmessage where message.message_id = chatmessage.message_id and message.message_id in (select chatmessage.message_id from chatmessage where sender_id = '" + user_id + "' or reciever_id = '" + user_id + "') order by message_time desc",null);



        Cursor c = sql.rawQuery("select message, message_time, sender_id, reciever_id from message, chatmessage where message.message_id = chatmessage.message_id and message.message_id in (select chatmessage.message_id from chatmessage where sender_id = '" + user_id + "' or reciever_id = '" + user_id + "') order by message_time desc",null);


        if(c.moveToFirst()) {
            do {

                senderId = c.getString(c.getColumnIndex("sender_id"));
                recieverId = c.getString(c.getColumnIndex("reciever_id"));
                messageText = c.getString(c.getColumnIndex("message"));
                messageDate = c.getString(c.getColumnIndex("message_time"));

                if(senderId.equals(user_id))
                {
                    chatUserId = recieverId;
                }
                else
                {
                    chatUserId = senderId;
                }


                //messageItemList.add(new messageItem(chatUserId , messageText, messageDate));



                Cursor c2 = sql.rawQuery("select fname, lname from user where user_id = '" + chatUserId + "'",null);



                if(c2.moveToFirst()) {
                    do {

                        chatUserFName = c2.getString(c2.getColumnIndex("fname"));

                        chatUserLName = c2.getString(c2.getColumnIndex("lname"));

                        messageItemList.add(new messageItem(chatUserFName + " " + chatUserLName, messageText, messageDate));

                    } while (c2.moveToNext());
                }




            } while (c.moveToNext());
        }


        //DATE TRANSFER FROM DATABASE TO LIST COMPLETED





        mRecyclerView = v.findViewById(R.id.recyclerViewMessages);

        mLayoutManager = new LinearLayoutManager(getContext());

        mAdapter = new messageAdapter(messageItemList,this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



        /*tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });*/

        newButton = v.findViewById(R.id.newMsgButton);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(getActivity(), newMessageActivity.class);
                intent.putExtra("user_id", user_id);
                startActivity(intent);

            }
        });

        return  v;
    }

    @Override
    public void onNoteClick(int position) {
        Toast.makeText(getContext(), chatUserId , Toast.LENGTH_SHORT).show();
      // position= mAdapter.getItemCount()-position;
//        final Intent intent = new Intent(getContext(), chatMessages.class);
//
//        startActivity(intent);
    }
}

