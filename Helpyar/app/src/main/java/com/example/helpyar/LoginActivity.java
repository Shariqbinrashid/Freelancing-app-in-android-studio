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

public class LoginActivity extends AppCompatActivity {
    TextView regtv;
    Button LogIn;
    EditText email;
    EditText password;
    String emailtext ;
    String passwordtext ;
String user_id;



Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String table_user="CREATE TABLE  IF NOT EXISTS User" +
                "(" +
                "user_id integer primary key autoincrement not null unique  ," +
                "email varchar(50) not null unique," +
                "fname varchar(30) not null, " +
                "lname varchar(30)," +
                "dob date," +
                " age integer," +
                "password varchar(30),"+
                " bio varchar(100)," +
                " user_rank varchar(50)," +
                " rating integer," +
                " pno integer" +
                ");";

        String table_jobcategory="CREATE TABLE  IF NOT EXISTS JobCategory" +
                "(" +
                "category_id integer PRIMARY KEY autoincrement   not null unique ," +
                "    category_title varchar(50)," +
                "    category_type varchar(50)," +
                "    category_description varchar(500)," +
                "    category_details varchar(1000)" +
                ");";
        String table_job="CREATE TABLE  IF NOT EXISTS Job\n" +
                "(\t\n" +
                "\tjob_id integer PRIMARY KEY autoincrement not null unique ,\n" +
                "    seller_id integer not null,\n" +
                "\ttitle varchar(50),\n" +
                "\toffered_price varchar(30),\n" +
                "    job_description varchar(500),\n" +
                "\tdetails varchar(1000),\n" +
                "    posted_date date,\n" +
                "    job_assigned varchar(50),\n" +
                "    job_status varchar(50),\n" +
                "    job_type varchar(20),\n" +
                "\tcategory_id integer,\n" +
                "    \n" +
                "\tconstraint Job_seller_id_fk\n" +
                "\t\tFOREIGN KEY(seller_id) \n" +
                "        references User(user_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "        \n" +
                "\tconstraint Job_category_id_fk\n" +
                "\t\tFOREIGN KEY(category_id) \n" +
                "        references JobCategory (category_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                "        \n" +
                "\t\n" +
                ");\n";

        String table_worksOnJob="CREATE TABLE IF NOT EXISTS worksOnJob\n" +
                "(\n" +
                "\tjob_id integer not null ,\n" +
                "    buyer_id integer not null ,\n" +
                "    \n" +
                "    CONSTRAINT WorksOn_pk\n" +
                "\t\tPRIMARY KEY(job_id, buyer_id),\n" +
                "        \n" +
                "\tCONSTRAINT WorksOn_job_id_fk\n" +
                "\t\tFOREIGN KEY(job_id)\n" +
                "        references Job(job_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "        \n" +
                "\tCONSTRAINT WorksOn_buyer_id_fk\n" +
                "\t\tFOREIGN KEY(buyer_id)\n" +
                "        references User(user_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                "    \n" +
                ");";
        String table_Pyament="CREATE TABLE  IF NOT EXISTS Payment\n" +
                "(\n" +
                "    job_id integer not null unique,\n" +
                "    amount integer,\n" +
                "    payment_option varchar(50),\n" +
                "    payment_status varchar(20),\n" +
                "    \n" +
                "\tconstraint Payment_pk\n" +
                "\t\tPRIMARY KEY (job_id),\n" +
                "\n" +
                "\tCONSTRAINT Payment_job_id_fk\n" +
                "\t\tFOREIGN KEY(job_id)\n" +
                "        references Job(job_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                ");";
        String table_Message="CREATE TABLE  IF NOT EXISTS Message\n" +
                "(\n" +
                "\tmessage_id integer PRIMARY KEY autoincrement not null unique ,\n" +
                "    message varchar(10000),\n" +
                "    message_time TIMESTAMP,\n" +
                "    message_file varchar(500)\n" +
                "    \n" +
                ");\n";
        String table_ChatMessages="CREATE TABLE  IF NOT EXISTS chatMessage\n" +
                "(\n" +
                "\tmessage_id  integer PRIMARY KEY autoincrement not null unique ,\n" +
                "    sender_id integer not null,\n" +
                "    reciever_id integer not null,\n" +
                "    \n" +
                "\tconstraint Chat_message_id_fk\n" +
                "\t\tFOREIGN KEY(message_id) \n" +
                "        references Message(message_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "        \n" +
                "\tconstraint Chat_sender_id_fk\n" +
                "\t\tFOREIGN KEY(sender_id) \n" +
                "        references User(user_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "        \n" +
                "\tconstraint Chat_reciever_id_fk\n" +
                "\t\tFOREIGN KEY(reciever_id) \n" +
                "        references User(user_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                ");\n";

        String table_Review="CREATE TABLE  IF NOT EXISTS Review\n" +
                "(\n" +
                "\treview_id integer PRIMARY KEY autoincrement not null unique ,\n" +
                "    reviewer_id integer not null,\n" +
                "    reviewed_to_id integer not null,\n" +
                "    job_id integer not null,\n" +
                "    rating integer,\n" +
                "    review varchar(10000)\n" +
                "    \n" +
                ");";
        String table_reviews="CREATE TABLE  IF NOT EXISTS reviews\n" +
                "(\n" +
                "\treview_id  integer PRIMARY KEY autoincrement not null unique ,\n" +
                "    reviewer_id integer not null,\n" +
                "    reviewed_to_id integer not null,\n" +
                "    job_id integer not null,\n" +
                "    \n" +
                "     CONSTRAINT reviews_review_id_fk\n" +
                "\t\tFOREIGN KEY(review_id)\n" +
                "        references Review(review_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "        \n" +
                "    CONSTRAINT reviews_reviewer_id_fk\n" +
                "\t\tFOREIGN KEY(reviewer_id)\n" +
                "        references User(user_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "        \n" +
                "\tCONSTRAINT reviews_reviewed_to_id_fk\n" +
                "\t\tFOREIGN KEY(reviewed_to_id)\n" +
                "        references User(user_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "        \n" +
                "\tCONSTRAINT reviews_job_id_fk\n" +
                "\t\tFOREIGN KEY(job_id)\n" +
                "        references Job(job_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                "    \n" +
                ");";
        String table_gigCategory="CREATE TABLE  IF NOT EXISTS GigCategory\n" +
                "(\n" +
                "\tcategory_id integer PRIMARY KEY  autoincrement not null unique ,\n" +
                "    categry_title varchar(50),\n" +
                "    category_type varchar(30),\n" +
                "    category_description varchar(500),\n" +
                "    category_details varchar(1000)\n" +
                " \n" +
                ");\n";
        String table_gig="CREATE TABLE  IF NOT EXISTS Gig\n" +
                "(\n" +
                "\tgig_id integer PRIMARY KEY autoincrement not null unique ,\n" +
                "    gig_type varchar(30),\n" +
                "    title varchar(50),\n" +
                "    category_id integer,\n" +
                "    gig_description varchar(500),\n" +
                "    details varchar(1000),\n" +
                "    \n" +
                "\tCONSTRAINT Gig_category_id_fk\n" +
                "\t\tFOREIGN KEY(category_id)\n" +
                "        references GigCategory(category_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                "\n" +
                ");\n";
        String table_hasGig="CREATE TABLE  IF NOT EXISTS hasGig\n" +
                "(\n" +
                "\tuser_id integer not null,\n" +
                "    gig_id integer not null,\n" +
                "    \n" +
                "\tconstraint hasGig_pk\n" +
                "\t\tPRIMARY KEY (user_id, gig_id),\n" +
                "        \n" +
                "\tCONSTRAINT hasGig_user_id_fk\n" +
                "\t\tFOREIGN KEY(user_id)\n" +
                "        references User(user_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "        \n" +
                "\tCONSTRAINT hasGig_gig_id_fk\n" +
                "\t\tFOREIGN KEY(gig_id)\n" +
                "        references Gig(gig_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                "\n" +
                ");\n";

        String table_notifications="CREATE TABLE  IF NOT EXISTS Notification\n" +
                "(\n" +
                "\tnotification_id integer PRIMARY KEY autoincrement not null unique ,\n" +
                "    notification_text varchar(100),\n" +
                "    notification_time TIMESTAMP\n" +
                "    \n" +
                ");";
        String table_hasNotification="CREATE TABLE  IF NOT EXISTS hasNotification\n" +
                "(\n" +
                "\tuser_id integer not null,\n" +
                "    notification_id integer not null,\n" +
                "    \n" +
                "\tconstraint hasNotification_pk\n" +
                "\t\tPRIMARY KEY (user_id, notification_id),\n" +
                "        \n" +
                "\tCONSTRAINT hasNotification_user_id_fk\n" +
                "\t\tFOREIGN KEY(user_id)\n" +
                "        references User(user_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE,\n" +
                "        \n" +
                "\tCONSTRAINT hasNotification_notification_id_fk\n" +
                "\t\tFOREIGN KEY(notification_id)\n" +
                "        references Notification(notification_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                "\n" +
                ");";
        String table_search="CREATE TABLE  IF NOT EXISTS Search\n" +
                "(\t\n" +
                "\tsearch_id integer PRIMARY KEY autoincrement not null unique ,\n" +
                "\tuser_id integer not null,\n" +
                "    search_item varchar(100),\n" +
                "    \n" +
                "\tconstraint Search_user_id_fk\n" +
                "\t\tFOREIGN KEY(user_id) \n" +
                "        references User(user_id)\n" +
                "        ON DELETE CASCADE\n" +
                "        ON UPDATE CASCADE\n" +
                "        \n" +
                "        \n" +
                "\t\n" +
                ");";


     //   String time = String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", new java.util.Date()));

     //   Toast.makeText(LoginActivity.this, time, Toast.LENGTH_SHORT).show();
        database db2=new database(this);
        final SQLiteDatabase sql2=db2.getWritableDatabase();

        sql2.execSQL(table_user);
        sql2.execSQL(table_jobcategory);
        sql2.execSQL(table_job);
        sql2.execSQL(table_worksOnJob);
        sql2.execSQL(table_Pyament);
        sql2.execSQL(table_Message);
        sql2.execSQL(table_ChatMessages);
        sql2.execSQL(table_Review);
        sql2.execSQL(table_reviews);
        sql2.execSQL(table_gigCategory);
        sql2.execSQL(table_gig);
        sql2.execSQL(table_hasGig);
        sql2.execSQL(table_notifications);
        sql2.execSQL(table_hasNotification);
        sql2.execSQL(table_search);
        database db=new database(this);
        final SQLiteDatabase sql=db.getWritableDatabase();

        /*Cursor cr= sql.rawQuery("delete from JobCategory ",null);

           if(cr.moveToFirst()) {
             do {

           } while (cr.moveToNext());
       }*/



        /*InsertCategory("Graphic designer","","");
        InsertCategory("Video Editor","","");
        InsertCategory("Software developer","","");
        InsertCategory("App Developer","","");
        InsertCategory("Web Developer","","");
        InsertCategory("UI/UX DESIGNER","","");
        InsertCategory("Game Developer","","");
        InsertCategory("Content Writer","","");
        InsertCategory("Electrician","","");
        InsertCategory("Painter","","");
        InsertCategory("Cleaning","","");
        InsertCategory("Pickup Dilivery","","");
        InsertCategory("Plumber","","");
        InsertCategory("Car washer","","");
        InsertCategory("Driver","","");
        InsertCategory("Labor","","");
        InsertCategory("Event Planner","","");
        InsertCategory("Consultant","","");
        InsertCategory("Digital Marketing","","");
        InsertCategory("Teacher","","");
        InsertCategory("Other","","");*/

        //Toast.makeText(LoginActivity.this, "Categories entered succesfully !!", Toast.LENGTH_SHORT).show();


        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpass);





      //  sql.execSQL("Insert into User (fname,email) values('s','dfdf')");

  //Cursor c= sql.rawQuery("select email from User ",null);


//
//        if(c.moveToFirst()) {
//            do {
//                final String text = c.getString(c.getColumnIndex("email"));
//                email.setText(text);
//            } while (c.moveToNext());
//        }


       // email.setText(text);
        tb=findViewById(R.id.toolbar);

        tb.setTitle("LOGIN");
        regtv= findViewById(R.id.createAccountTv);
        regtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);

            }});
        LogIn=findViewById(R.id.loginBtn);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    boolean found=false;

                    Cursor c= sql.rawQuery("select * from User ",null);



                if(c.moveToFirst()) {
                    do {
                       emailtext = c.getString(c.getColumnIndex("email"));
                          passwordtext = c.getString(c.getColumnIndex("password"));
                            user_id= c.getString(c.getColumnIndex("user_id"));
                        if(email.getText().toString().equals(emailtext)&&password.getText().toString().equals(passwordtext)){
                        found=true;
                         //   User user=new User(emailtext,fnametext,lnametext,dob,age,passwordtext,bio,userrank,rating,pno);
//                            intent.putExtra("email",emailtext);
//                            intent.putExtra("fname",fnametext);
//                            intent.putExtra("lname",lnametext);
//                            intent.putExtra("dob",dob);
//                            intent.putExtra("age",age);
//                            intent.putExtra("password",passwordtext);
//                            intent.putExtra("bio",bio);
//                            intent.putExtra("userrank",userrank);
//                            intent.putExtra("rating",rating);
//                            intent.putExtra("pno",pno);


                        break;
                        }
                    } while (c.moveToNext());
                }


                if(found==false){
                    Toast.makeText(LoginActivity.this, "Invalid details!!", Toast.LENGTH_SHORT).show();
                }
                else {

                    final Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("user_id",user_id);
                    Toast.makeText(LoginActivity.this, "Logged in succesfully !!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }}});
    }
    void InsertCategory(String title,String description,String category_details){

        database db=new database(this);
        final SQLiteDatabase sql=db.getWritableDatabase();

        ContentValues dv = new ContentValues();
        dv.put("category_title",title);
        dv.put("category_description"," ");
        dv.put("category_details"," ");
        sql.insert("JobCategory",null,dv);


    }
}
