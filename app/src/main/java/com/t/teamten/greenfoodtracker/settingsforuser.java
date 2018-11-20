package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.t.teamten.greenfoodtracker.loginactivities.FactsActivity;
import com.t.teamten.greenfoodtracker.loginactivities.FirebaseLogin;
import com.t.teamten.greenfoodtracker.loginactivities.HomeScreen;
import com.t.teamten.greenfoodtracker.loginactivities.aboutactivity;
//setting:about,manage account, delete user...
public class settingsforuser extends AppCompatActivity {
    FirebaseAuth mAuth;
    float x1,x2,y1,y2;
    //Setting page so that the user can Signout or delete his account or he can manage his account.
    // Settings also has about to the source of information the user is using.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsforuser);
        mAuth = FirebaseAuth.getInstance();

    }
    public void movetoaboutactivity(View view) {
        Intent movetoaboutactivity = new Intent(settingsforuser.this,aboutactivity.class);
        startActivity(movetoaboutactivity);
    }

    public void signoutprofile(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent movetologin = new Intent(settingsforuser.this, FirebaseLogin.class);
        startActivity(movetologin);
        finish();
    }

    public void deletetheuseraccount(View view) {
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        currentuser.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(settingsforuser.this, "Userdeleted", Toast.LENGTH_LONG).show();
                            Intent movetoLoginScreen = new Intent(settingsforuser.this, FirebaseLogin.class);
                            startActivity(movetoLoginScreen);
                        }
                    }
                });
    }

    public void manageaccount(View view) {
        Intent movetomanageactivity = new Intent(this, ManageAccount.class);
        startActivity(movetomanageactivity);
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    Intent i = new Intent(settingsforuser.this,FactsActivity.class);
                    startActivity(i);
                }else if(x1 > x2){

                }
                break;
        }
        return false;
    }

}
