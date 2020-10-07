package com.example.helpyar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
 Button CreateAccount;
 EditText email;
    EditText password;
    EditText fname;
    EditText lname;
    EditText pno;


 Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        database db=new database(this);
        final SQLiteDatabase sql=db.getWritableDatabase();

        tb=findViewById(R.id.toolbar);
        tb.setTitle("Registeration");
        email=findViewById(R.id.emailreg);
        fname=findViewById(R.id.fnamereg);
        lname=findViewById(R.id.lnamereg);
        password=findViewById(R.id.passwordreg);
        pno=findViewById(R.id.pnoreg);


        CreateAccount =findViewById(R.id.createAccountBtn);

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(email.getText().toString().isEmpty()||fname.getText().toString().isEmpty()||lname.getText().toString().isEmpty()||password.getText().toString().isEmpty()||pno.getText().toString().isEmpty()){
                   Toast.makeText(RegistrationActivity.this, "Kindly Enter Require information!", Toast.LENGTH_SHORT).show();
              }else{

                   String emailS = email.getText().toString();

                   String fnameS =  fname.getText().toString();
                  String lnameS = lname.getText().toString();
                   String passwordS = password.getText().toString();
                   String pnoS = pno.getText().toString() ;
boolean check=false;
                   Cursor c= sql.rawQuery("select email from User ",null);



     if(c.moveToFirst()) {
            do {
                final String text = c.getString(c.getColumnIndex("email"));
                if(emailS.equals(text))
                    check=true;
            } while (c.moveToNext());
        }
if(check==true){
    Toast.makeText(RegistrationActivity.this, "Email already registered", Toast.LENGTH_SHORT).show();
}
else {

    ContentValues cv = new ContentValues();
    cv.put("email", emailS);
    cv.put("fname", fnameS);
    cv.put("lname", lnameS);
    cv.put("password", passwordS);
    cv.put("pno", pnoS);
    sql.insert("User", null, cv);
    Toast.makeText(RegistrationActivity.this, "Account made successfully!!!", Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
    startActivity(intent);
}

                }


            }});


    }
}
