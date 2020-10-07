package com.example.helpyar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    Fragment selected=null;
    TextView tv;
    Toolbar tb;
    Menu menu;
    //for user
    String user_id ;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database db = new database(this);
        final SQLiteDatabase sql = db.getWritableDatabase();


        /*****************************          NEWSFEED CODE BEGIN             *****************************/

        /*ArrayList<homeItem> homeItemList = new ArrayList<>();


        String titleS;
        String AmountS;
        String dateS;
        String statusS;

        Cursor c2 = sql.rawQuery("select * from Job order by job_id desc",null);

        if(c2.moveToFirst()) {
            do {

                titleS = c2.getString(c2.getColumnIndex("title"));
                AmountS = c2.getString(c2.getColumnIndex("offered_price"));
                dateS = c2.getString(c2.getColumnIndex("posted_date"));
                statusS = c2.getString(c2.getColumnIndex("job_status"));

                homeItemList.add(new homeItem(titleS, AmountS, "Posted on: " + dateS, statusS));

            } while (c2.moveToNext());
        }


        mRecyclerView = findViewById(R.id.recyclerViewHome);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new homeAdapter(homeItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
*/

        /**************************         NEWSFEED CODE END             **************************/




        String user_idcheck = getIntent().getExtras().getString("user_id") ;
        Cursor c = sql.rawQuery("select user_id from User ",null);



        if(c.moveToFirst()) {
            do {
                user_id = c.getString(c.getColumnIndex("user_id"));

                if(user_id.equals(user_idcheck)){
                    break;
                }
            } while (c.moveToNext());
        }
       // User user=new User(emailtext,fnametext,lnametext,dob,age,passwordtext,bio,userrank,rating,pno);
        tb=findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        tb.setTitle("Home");
        bnv=findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();



    }





    private BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    selected=new HomeFragment();
                    tb.setTitle("Home");
                    break;
                //case R.id.nav_search:
                    //selected=new SearchFragment();
                   // break;
                case R.id.nav_messages:
                    tb.setTitle("Messages");
                    selected=new MessagesFragment();
                    break;
                case R.id.nav_notification:
                    tb.setTitle("Notification");
                    selected=new NotificationFragment();
                    break;
                case R.id.nav_profile:
                    tb.setTitle("Profile");
                    selected=new ProfileFragment();

                    break;
                case R.id.nav_add:
                    tb.setTitle("Post job");
                    selected=new AddFragment();
                    break;

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected).commit();
            return true;
        }
    };

    public String getUser_id() {
        return user_id;
    }
}
