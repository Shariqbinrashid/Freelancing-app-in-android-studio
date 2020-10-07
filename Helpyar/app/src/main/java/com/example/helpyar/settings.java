package com.example.helpyar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class settings extends AppCompatActivity {
Toolbar tb;
User user;

String user_id;
    String emailtext ;
    String passwordtext ;
    String fnametext;
    String lnametext ;
    String dob ;
    String age ;    String bio ;
    String userrank;
    String rating ;
    String pno ;
    TextView emailtv;
    TextView agetv;
    EditText fnametv;
    EditText lanmetv;
    EditText dobtv;
    EditText passwordtv;
    EditText biotv;
    EditText pnotv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
   emailtv=findViewById(R.id.emailsettings);
         agetv=findViewById(R.id.ageset);
         fnametv=findViewById(R.id.fnameset);
         lanmetv=findViewById(R.id.lnameset);
         dobtv=findViewById(R.id.dobset);
         passwordtv=findViewById(R.id.passwordset);
         biotv=findViewById(R.id.bioset);
         pnotv=findViewById(R.id.pnoset);
        tb=(Toolbar) findViewById(R.id.toolbarset);
setSupportActionBar(tb);

    user_id=getIntent().getExtras().getString("user_id") ;
//        HomeActivity activity=new HomeActivity();
//        user_id=activity.getUser_id();
        database db=new database(this);
        final SQLiteDatabase sql=db.getWritableDatabase();
        Cursor c= sql.rawQuery("select * from User ",null);
        if(c.moveToFirst()) {
            do {
                String user_idcheck=c.getString(c.getColumnIndex("user_id"));
                emailtext = c.getString(c.getColumnIndex("email"));
                passwordtext = c.getString(c.getColumnIndex("password"));
                fnametext = c.getString(c.getColumnIndex("fname"));
                lnametext = c.getString(c.getColumnIndex("lname"));
                dob = c.getString(c.getColumnIndex("dob"));
                age =c.getString(c.getColumnIndex("age"));
                bio = c.getString(c.getColumnIndex("bio"));
                userrank = c.getString(c.getColumnIndex("user_rank"));
                rating = c.getString(c.getColumnIndex("rating"));
                pno =c.getString(c.getColumnIndex("pno"));
                if(user_id.equals(user_idcheck)){
                    break;
                }
            } while (c.moveToNext());
        }

        String date = String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd", new java.util.Date()));




        if(dob != null)
        {
            String [] separated = date.split("-");

            int currentYear = Integer.parseInt(separated[0]);
            int currentMonth = Integer.parseInt(separated[1]);
            int currentDay = Integer.parseInt(separated[2]);




            String [] separatedDob = dob.split("-");
            int birthYear = Integer.parseInt(separatedDob[0]);
            int birthMonth = Integer.parseInt(separatedDob[1]);
            int birthDay = Integer.parseInt(separatedDob[2]);



            int userAge;

            if(birthMonth < currentMonth) {
                userAge = currentYear - birthYear;
            }
            else if(birthMonth > currentMonth) {
                userAge = currentYear - birthYear - 1;
            }
            else {
                if(birthDay <= currentDay) {
                    userAge = currentYear - birthYear;
                }
                else {
                    userAge = currentYear - birthYear - 1;
                }
            }

            age = Integer.toString(userAge);
        }
        else
        {
            age = "Enter your DOB";
        }





        user=new User(emailtext,fnametext,lnametext,dob,age,passwordtext,bio,userrank,rating,pno);
        emailtv.setText("Email: "+user.getEmail());
        agetv.setText("Age : "+user.getAge());
        fnametv.setText(user.getFname());
        lanmetv.setText(user.getLname());
        dobtv.setText(user.getDob());
        passwordtv.setText(user.getPassword());
        biotv.setText(user.getBio());
        pnotv.setText(user.getPno());
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settingstoolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.done:
                if(pnotv.getText().toString().isEmpty()||fnametv.getText().toString().isEmpty()||lanmetv.getText().toString().isEmpty()||passwordtv.getText().toString().isEmpty()||dobtv.getText().toString().isEmpty()||biotv.getText().toString().isEmpty()){
                    Toast.makeText(settings.this, "Kindly Enter Require information!", Toast.LENGTH_SHORT).show();
                }
                else{
                    database db=new database(this);
                   SQLiteDatabase sql=db.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("fname", fnametv.getText().toString());
                    cv.put("lname", lanmetv.getText().toString());
                    cv.put("password", passwordtv.getText().toString());
                    cv.put("pno", pnotv.getText().toString());
                    cv.put("bio", biotv.getText().toString());
                    cv.put("dob", dobtv.getText().toString());
                    cv.put("age", agetv.getText().toString());

                    sql.update("User", cv, "user_id="+user_id, null);
                    Toast.makeText(settings.this, "RECORD SAVED!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("user_id",user_id);
                    startActivity(intent);
                }
                break;

        }
        return super.onOptionsItemSelected(item);
}
}


