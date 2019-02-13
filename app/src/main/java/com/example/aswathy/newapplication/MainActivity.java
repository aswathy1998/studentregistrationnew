package com.example.aswathy.newapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText t1,t2;
   Button b1,b2;
    String getusername,getpassword;

    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText) findViewById(R.id.uname);
        t2=(EditText) findViewById(R.id.password);
        b1=(Button)findViewById(R.id.loginbutton);
        b2=(Button)findViewById(R.id.regr);

        dbhelper=new Dbhelper(this);
        dbhelper.getWritableDatabase();

        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        String getname=preferences.getString("username",null);
        if(getname!=null)
        {
            Intent i=new Intent(getApplicationContext(),newActivity.class);
            startActivity(i);
        }


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getusername=t1.getText().toString();
                Log.d("username",getusername);
              getpassword=t2.getText().toString();
                Log.d("password",getpassword);

                Cursor cur=dbhelper.compare(getusername);

                if(cur.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"username is invalid",Toast.LENGTH_LONG).show();
                }
                else {
                    while (cur.moveToNext())
                    {


                        String dbname=cur.getString(1);
                        String dbpass=cur.getString(5);

                        if(dbpass.equals(getpassword))
                        {

                            SharedPreferences.Editor editor=getSharedPreferences("login",MODE_PRIVATE).edit();
                            editor.putString("username",dbname);
                            editor.apply();

                            Intent i=new Intent(getApplicationContext(),newActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"wrong password",Toast.LENGTH_LONG).show();
                        }

                        getpassword=cur.getString(2);
                        t2.setText(getpassword);
                    }
                }


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });

    }
}
