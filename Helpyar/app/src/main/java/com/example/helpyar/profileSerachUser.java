package com.example.helpyar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profileSerachUser extends AppCompatActivity {
    String user_id;
    String emailtext;
    String passwordtext;
    String fnametext;
    String lnametext;
    String dob;
    String age;
    String bio;
    String userrank;
    String rating;
    String pno;
    TextView flname;
    TextView emailtv;
    TextView biotv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_serach_user);
        user_id=getIntent().getExtras().getString("user_id") ;

        database db = new database(this);
        final SQLiteDatabase sql = db.getWritableDatabase();
        Cursor c = sql.rawQuery("select * from User where user_id='"+user_id+"'", null);
        if (c.moveToFirst()) {
            do {
                emailtext = c.getString(c.getColumnIndex("email"));
                passwordtext = c.getString(c.getColumnIndex("password"));
                fnametext = c.getString(c.getColumnIndex("fname"));
                lnametext = c.getString(c.getColumnIndex("lname"));
                dob = c.getString(c.getColumnIndex("dob"));
                age = c.getString(c.getColumnIndex("age"));
                bio = c.getString(c.getColumnIndex("bio"));
                userrank = c.getString(c.getColumnIndex("user_rank"));
                rating = c.getString(c.getColumnIndex("rating"));
                pno = c.getString(c.getColumnIndex("pno"));
            } while (c.moveToNext());
        }
        flname = (TextView) findViewById(R.id.flnameSearchProfile);
        biotv = (TextView)findViewById(R.id.bioprofsearch);
        emailtv = (TextView) findViewById(R.id.etprofsearch);
        emailtv.setText("Email "+emailtext);
        flname.setText(fnametext+ " " + lnametext);
        biotv.setText(bio);
        Button job;

        job =findViewById(R.id.jobsProfilesearchButton);
        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(getApplicationContext(), userjobsActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);

            }

        });

    }
}
