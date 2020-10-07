package com.example.helpyar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    String user_id;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View v=inflater.inflate(R.layout.fragment_home, container, false);
        HomeActivity activity = (HomeActivity) getActivity();

        user_id = activity.getUser_id();

        /*****************************          NEWSFEED CODE BEGIN             *****************************/
        database db = new database(getContext());
        final SQLiteDatabase sql=db.getWritableDatabase();
        ArrayList<homeItem> homeItemList = new ArrayList<>();


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

                homeItemList.add(new homeItem("Title: "+titleS,"Rs: "+ AmountS, "Posted on: " + dateS, "Status :"+statusS));

            } while (c2.moveToNext());
        }


        mRecyclerView = v.findViewById(R.id.recyclerViewHomeFragment);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new homeAdapter(homeItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        /**************************         NEWSFEED CODE END             **************************/





        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity activity=(HomeActivity) getActivity();
        user_id=activity.getUser_id();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_navigation,menu);


    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.nav_search:
                Intent intentig = new Intent(getActivity(), searchuser.class);
                startActivity(intentig);
                break;
            case R.id.nav_settings:
                Intent intenti = new Intent(getActivity(), settings.class);
               intenti.putExtra("user_id",user_id);
                startActivity(intenti);
                Toast.makeText(getActivity(),"Settings",Toast.LENGTH_SHORT).show();
                break;
                case R.id.nav_logout:
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
