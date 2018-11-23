package com.t.teamten.greenfoodtracker.loginactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.t.teamten.greenfoodtracker.ManageAccount;
import com.t.teamten.greenfoodtracker.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import firebaseuser.Realtime_Pledge_Data;

public class FactsActivity extends AppCompatActivity {
    // Facts Activity so that the user can see random facts instead of using the Calculator all the time.
    TextView randomFacts;
    Button refreshButton;
    InputStream streamCountLines;
    BufferedReader readCountLines;

    InputStream inputStream;
    BufferedReader bufferedReader;
    float moveup, moveupvalue, movedown, movedownvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        final Fact factsObject= new Fact();
        randomFacts = findViewById(R.id.factsID);
        streamCountLines = this.getResources().openRawResource(R.raw.factstextfile);
        readCountLines = new BufferedReader(new InputStreamReader(streamCountLines));
        try{

            while(readCountLines.readLine() != null){
                factsObject.setIntCount(factsObject.getIntCount()+1);

            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

        inputStream = this.getResources().openRawResource(R.raw.factstextfile);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        factsObject.initializeArray(factsObject.getIntCount());
        try {
            for(int i = 0 ; i < factsObject.getIntCount();i++){
                factsObject.setTextData(bufferedReader.readLine(),i);
            }
        }catch (Exception bufferReaderException ){
                bufferReaderException.printStackTrace();
        }

        randomFacts.setText(factsObject.getTextData(0));
        refreshButton = findViewById(R.id.RefreshID);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                factsObject.pickRandomFacts();
                randomFacts.setText(factsObject.getTextData(0));
            }
        });
    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                moveup = touchEvent.getX();
                movedown = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                moveupvalue = touchEvent.getX();
                movedownvalue = touchEvent.getY();
                if(moveup < moveupvalue){
                    Intent i = new Intent(FactsActivity.this, Realtime_Pledge_Data.class);
                    startActivity(i);
                }else if(moveup > moveupvalue){
                    Intent i = new Intent(FactsActivity.this,ManageAccount.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }


}
