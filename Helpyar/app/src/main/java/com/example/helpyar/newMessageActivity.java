package com.example.helpyar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class newMessageActivity extends AppCompatActivity {


    EditText sendingEmail;
    EditText sendingMessage;
    Button sendB;

    String emailText;
    String messageText;

    String user_id;

    String sender_id;
    String reciever_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_message);

        user_id = getIntent().getExtras().getString("user_id") ;


        //   String time = String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", new java.util.Date()));

        //   Toast.makeText(LoginActivity.this, time, Toast.LENGTH_SHORT).show();

        sender_id = user_id;

        database db = new database(this);
        final SQLiteDatabase sql = db.getWritableDatabase();

        sendingEmail = findViewById(R.id.sendingEmail);
        sendingMessage = findViewById(R.id.sendingText);

        sendB = findViewById(R.id.sendMessageButton);

        sendB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean found = false;

                Cursor c = sql.rawQuery("select * from User ",null);

                if(c.moveToFirst()) {
                    do {

                        emailText = c.getString(c.getColumnIndex("email"));

                        reciever_id = c.getString(c.getColumnIndex("user_id"));

                        if(sendingEmail.getText().toString().equals(emailText)){

                            found = true;
                            break;
                        }
                    } while (c.moveToNext());
                }


                if(found == false){

                    Toast.makeText(newMessageActivity.this, "Invalid email entered!!", Toast.LENGTH_SHORT).show();

                }

                else {


                    String time = String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", new java.util.Date()));

                    messageText = sendingMessage.getText().toString();

                    InsertMessage(messageText, time, sender_id, reciever_id);


                    final Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("user_id", user_id);

                    Toast.makeText(newMessageActivity.this, "Message Sent Succesfully !!", Toast.LENGTH_SHORT).show();

                    startActivity(intent);

                }}});
    }

    void InsertMessage(String message, String message_time, String sender_id, String reciever_id){

        database db = new database(this);
        final SQLiteDatabase sql = db.getWritableDatabase();


        ContentValues dv = new ContentValues();

        dv.put("message", message);
        dv.put("message_time", message_time);

        sql.insert("Message",null,dv);


        ContentValues dv2 = new ContentValues();

        dv2.put("sender_id", sender_id);
        dv2.put("reciever_id", reciever_id);

        sql.insert("chatMessage",null,dv2);


    }



}