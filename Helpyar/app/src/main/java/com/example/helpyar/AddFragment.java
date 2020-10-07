package com.example.helpyar;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

String user_id;
User user=new User();

    Spinner types;
    Spinner categories;
    Button postJob;

    EditText title;
    EditText description;
    EditText details;
    EditText amount;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add, container, false);
        HomeActivity activity=(HomeActivity) getActivity();
        user_id=activity.getUser_id();

        types=v.findViewById(R.id.spinnertype);

        categories=v.findViewById(R.id.spinnercategory);

        //create a list of items for the spinner.
        String[] items = new String[]{"Online", "Physical"};

        String[] jobCategories = new String[63];

        database db=new database(getActivity());
        final SQLiteDatabase sql=db.getWritableDatabase();

        Cursor c= sql.rawQuery("select * from JobCategory ",null);
        int i = 0;
           if(c.moveToFirst()) {
             do {
                 jobCategories[i] = c.getString(c.getColumnIndex("category_title"));
                         i++;
           } while (c.moveToNext());
       }



//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        types.setAdapter(adapter);

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, jobCategories);
//set the spinners adapter to the previously created one.
        categories.setAdapter(adapter2);


        title = v.findViewById(R.id.jobtitle);
        description = v.findViewById(R.id.jobdescr);
        details = v.findViewById(R.id.jobdetails);
        amount = v.findViewById(R.id.jobamount);



        postJob = v.findViewById(R.id.jobpostbutton);

        postJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                if(title.getText().toString().isEmpty()||amount.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Kindly Enter the job title and amount!", Toast.LENGTH_SHORT).show();
                }else{


                        ContentValues cv = new ContentValues();

                        cv.put("seller_id", Integer.parseInt(user_id));

                        cv.put("title", title.getText().toString());
                        cv.put("offered_price", amount.getText().toString());
                        cv.put("job_description", description.getText().toString());
                        cv.put("details", details.getText().toString());
                        cv.put("job_assigned", "NO");
                        cv.put("job_status", "UnComplete");
                        cv.put("job_type", types.getSelectedItem().toString());

                       String date = String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd", new java.util.Date()));

                        cv.put("posted_date", date);



                       String selectedCategory = categories.getSelectedItem().toString();

                        String categoryId = selectedCategory;


                    Toast.makeText(getActivity(), selectedCategory, Toast.LENGTH_SHORT).show();


                    Cursor c= sql.rawQuery("select category_id from JobCategory where category_title = '" + selectedCategory + "'",null);

                    //Cursor c= sql.rawQuery("select category_id from JobCategory where category_title = 'Driver' ",null);




                    if(c.moveToFirst()) {
                            do {

                                categoryId = c.getString(c.getColumnIndex("category_id"));

                            } while (c.moveToNext());
                        }


                    Toast.makeText(getActivity(), categoryId, Toast.LENGTH_SHORT).show();

                        cv.put("category_id", categoryId);

                        sql.insert("Job", null, cv);

                        Toast.makeText(getActivity(), "Job Posted successfully!!!", Toast.LENGTH_SHORT).show();

                    title.setText("");
                    description.setText("");
                    details.setText("");
                    amount.setText("");



                }


            }});

        return v;
    }

}
