package com.example.helpyar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class userjobsActivity extends AppCompatActivity {
    Toolbar tb;
    String user_id;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userjobs);
        user_id = getIntent().getExtras().getString("user_id") ;

        tb=findViewById(R.id.toolbarpostjobs);
        setSupportActionBar(tb);


        ArrayList<jobposteditem> jobitemlist=new ArrayList<>();

      // jobitemlist.add(new jobposteditem("Title","Amount","Date","Status"));



        database db=new database(this);
        final SQLiteDatabase sql=db.getWritableDatabase();

        String titleS;
        String AmountS;
        String dateS;
        String statusS;

        Cursor c= sql.rawQuery("select * from Job where seller_id = ' " + user_id + "'",null);

        if(c.moveToFirst()) {
            do {

                titleS = c.getString(c.getColumnIndex("title"));
                AmountS = c.getString(c.getColumnIndex("offered_price"));
                dateS = c.getString(c.getColumnIndex("posted_date"));
                statusS = c.getString(c.getColumnIndex("job_status"));

                jobitemlist.add(new jobposteditem(titleS, AmountS, "Posted on: " + dateS, statusS));

            } while (c.moveToNext());
        }


        mRecyclerView = findViewById(R.id.recyclerviewpostjob);
       mLayoutManager = new LinearLayoutManager(this);
       mAdapter = new postjobadapter(jobitemlist);

       mRecyclerView.setLayoutManager(mLayoutManager);
       mRecyclerView.setAdapter(mAdapter);



        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });

    }
}
