package com.example.helpyar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileFragment extends Fragment {
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
    TextView et;
    User user;

    TextView biotv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        HomeActivity activity = (HomeActivity) getActivity();
        user_id = activity.getUser_id();
        database db = new database(activity);
        final SQLiteDatabase sql = db.getWritableDatabase();
        Cursor c = sql.rawQuery("select * from User ", null);
        if (c.moveToFirst()) {
            do {
                String user_idcheck = c.getString(c.getColumnIndex("user_id"));
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
                if (user_id.equals(user_idcheck)) {
                    break;
                }
            } while (c.moveToNext());
        }
        et = (TextView) v.findViewById(R.id.etprof);
        biotv = (TextView) v.findViewById(R.id.bioprof);

        user = new User(emailtext, fnametext, lnametext, dob, age, passwordtext, bio, userrank, rating, pno);
        et.setText(user.getFname() + " " + user.getLname());
        biotv.setText(bio);

        Button job;

        job = v.findViewById(R.id.jobsProfileButton);
        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(getActivity(), userjobsActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);

            }

        });
        return v;
    }

}
