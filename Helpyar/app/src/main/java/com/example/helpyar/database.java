package com.example.helpyar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 1;
    public database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

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

        db.execSQL(table_user);
        db.execSQL(table_jobcategory);
        db.execSQL(table_job);
        db.execSQL(table_worksOnJob);
        db.execSQL(table_Pyament);
        db.execSQL(table_Message);
        db.execSQL(table_ChatMessages);
        db.execSQL(table_Review);
        db.execSQL(table_reviews);
        db.execSQL(table_gigCategory);
        db.execSQL(table_gig);
        db.execSQL(table_hasGig);
        db.execSQL(table_notifications);
        db.execSQL(table_hasNotification);
        db.execSQL(table_search);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
