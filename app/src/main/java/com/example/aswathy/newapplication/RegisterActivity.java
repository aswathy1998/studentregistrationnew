package com.example.aswathy.newapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
   TextView ed1,ed2,ed3,ed4,ed5;
    Button b1,b2;
    Dbhelper dbhelper;
    String s1,s2,s3,s4,s5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed1=(TextView) findViewById(R.id.name);
        ed2=(TextView) findViewById(R.id.email);
        ed3=(TextView) findViewById(R.id.mob);
        ed4=(TextView) findViewById(R.id.uname);
        ed5=(TextView) findViewById(R.id.pass);
        b1=(Button)findViewById(R.id.reg);
        b2=(Button)findViewById(R.id.bklg);

        dbhelper=new Dbhelper(this);
        dbhelper.getWritableDatabase();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=ed1.getText().toString();
                s2=ed2.getText().toString();
                s3=ed3.getText().toString();
                s4=ed4.getText().toString();
                s5=ed5.getText().toString();
                Log.d("name",s1);
                Log.d("Email id",s2);
                Log.d("mobileno",s3);
                Log.d("username",s4);
                Log.d("password",s5);


                boolean status=dbhelper.insertData(s1,s2,s3,s4,s5);
                if(status==true){
                    Toast.makeText(getApplicationContext(),"succcessfully inserted",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
