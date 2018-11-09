package com.t.teamten.greenfoodtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.t.teamten.greenfoodtracker.loginactivities.FirebaseLogin;
import com.t.teamten.greenfoodtracker.loginactivities.aboutactivity;
//setting:about,manage account, delete user...
public class settingsforuser extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsforuser);
        mAuth = FirebaseAuth.getInstance();

    }
    public void move(View view) {
        Intent movetoaboutactivity = new Intent(settingsforuser.this,aboutactivity.class);
        startActivity(movetoaboutactivity);
    }

    public void signoutprofile(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent movetologin = new Intent(settingsforuser.this, FirebaseLogin.class);
        startActivity(movetologin);
        finish();
    }

    public void deleteid(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete()
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
        Intent intent = new Intent(this, ManageAccount.class);
        startActivity(intent);
    }
}
