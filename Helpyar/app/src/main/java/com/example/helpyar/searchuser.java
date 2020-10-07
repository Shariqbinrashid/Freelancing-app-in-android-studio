package com.example.helpyar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class searchuser extends AppCompatActivity {
    String user_id;
    String emailtext ;
    String fnametext;
    String lnametext ;
    String dob ;
     String bio ;
     EditText searchet;
    TextView flname;
    TextView dobtv;
    TextView biotv;
    Button searchb;
    Button profileb;

    //String userrank;
    //String rating ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchuser);
        searchet=findViewById(R.id.edittextsearchuser);
         flname=findViewById(R.id.fnamelnamesearch);
         dobtv=findViewById(R.id.dobsearch);
         biotv=findViewById(R.id.biosearch);
         searchb=findViewById(R.id.searchuser);
         profileb=findViewById(R.id.goprofilesearch);



        searchb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database db = new database(getApplicationContext());

                final SQLiteDatabase sql = db.getWritableDatabase();

                Cursor c = sql.rawQuery("select user_id,fname,lname,dob,bio from User where email='" + searchet.getText().toString() + "'", null);
                if (c.moveToFirst()) {
                    do {

                        fnametext = c.getString(c.getColumnIndex("fname"));

                        lnametext = c.getString(c.getColumnIndex("lname"));
                        user_id = c.getString(c.getColumnIndex("user_id"));
                        dob = c.getString(c.getColumnIndex("dob"));
                        bio = c.getString(c.getColumnIndex("bio"));

                    } while (c.moveToNext());
                }
                if (user_id == null) {
                    Toast.makeText(getApplicationContext(), "user don't exist", Toast.LENGTH_SHORT).show();

                } else {
                    flname.setText(fnametext + " " + lnametext);
                    dobtv.setText("DOB: " + dob);
                    biotv.setText("Bio: " + bio);
                }
            }

        });



        profileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(getApplicationContext(), profileSerachUser.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);

            }
        });
    }


}
