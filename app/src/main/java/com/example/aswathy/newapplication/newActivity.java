package com.example.aswathy.newapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class newActivity extends AppCompatActivity {
TextView tv;
    Button b1,b2,b3;
    String getid;
    AlertDialog.Builder builder;
    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        tv=(TextView)findViewById(R.id.hlo);
        b1=(Button)findViewById(R.id.pfedit);
        b2=(Button)findViewById(R.id.rm);
        b3=(Button)findViewById(R.id.logout);


        dbhelper=new Dbhelper(this);
        dbhelper.getWritableDatabase();

        builder=new AlertDialog.Builder(this);
        builder.setTitle("confirm");
        builder.setMessage("Are you sure want to delete?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"yes clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();

                boolean status=dbhelper.DeleteData(getid);
                if(status==true){
                    Toast.makeText(getApplicationContext(),"DELETED SUCESSFULLY",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"ERROR IN DELETION",Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"No clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=getSharedPreferences("login",MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
             Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert =builder.create();
                alert.show();
            }
        });



       SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String data=sharedPreferences.getString("username",null);
        tv.setText("Hello "+data);
    }
}
