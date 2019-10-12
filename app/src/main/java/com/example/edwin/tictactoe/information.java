package com.example.edwin.tictactoe;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class information extends AppCompatActivity {
    static final String NAME = "name";
    static final String NAME2 = "name2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /*
    @method Storename
    @return void
    @Description what this method does is store the names that were typed into the textEdit box
    and then sends this intent to start our next activity.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void storeName(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        EditText et_firstN = (EditText) findViewById(R.id.edit_firstN);
        String player1 = et_firstN.getText().toString();
        EditText et_secondN = (EditText) findViewById(R.id.edit_secondN);
        String player2 = et_secondN.getText().toString();

        intent.putExtra(NAME, player1);
        intent.putExtra(NAME2, player2);


        if(!player1.isEmpty() && !player2.isEmpty()) {
            startActivity(intent);


        }else{
            Toast.makeText(getApplicationContext(),"Please Enter Two Names to continue", Toast.LENGTH_LONG).show();
        }

    }
}
