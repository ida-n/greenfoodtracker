package com.t.teamten.greenfoodtracker.loginactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.t.teamten.greenfoodtracker.R;
import com.t.teamten.greenfoodtracker.homescreenactivity.DrawerFromSide;
import com.t.teamten.greenfoodtracker.homescreenactivity.HomeScreen;

import firebaseuser.User;
// User Registeration activity so that the user can register himself on the firebase database.
public class UserRegistration extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference ref;

    private String email;
    private String password;
    private String gender;
    private String age;
    private String city;
    private String firstName;
    private String lastName;
    private String pledge;
    private String defaultProfileIcon;


    private EditText emailText;
    private EditText passwordText;
    private EditText ageText;
    private EditText firstNameText;
    private EditText lastNameText;
    private Spinner citySpinner;
    private Spinner genderspinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registeration);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        ageText = findViewById(R.id.ageText);
        citySpinner =  findViewById(R.id.citySpinner);
        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        genderspinner = findViewById(R.id.genderId);

        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("users");


        ArrayAdapter<CharSequence> adapterforcity = ArrayAdapter.createFromResource(this,R.array.city_name,android.R.layout.simple_spinner_item);
        adapterforcity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapterforcity);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserRegistration.this,"City field is Empty", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<CharSequence> adapterforgender = ArrayAdapter.createFromResource(this,R.array.genderarray,android.R.layout.simple_spinner_item);
        adapterforgender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(adapterforgender);

        genderspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserRegistration.this,"Gender field is Empty", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void RegisterActivity(View view) {

        email = emailText.getText().toString();
        password = passwordText.getText().toString();
        age = ageText.getText().toString();
        firstName = firstNameText.getText().toString();
        lastName = lastNameText.getText().toString();
        pledge = "0";
        defaultProfileIcon = "fox";

        if(email.matches("") || password.matches("") || age.matches("") || city.matches("")|| gender.matches("") ||firstName.matches("") ||lastName.matches("")) {
            Toast.makeText(UserRegistration.this, "Fields are mandatory", Toast.LENGTH_LONG).show();
        }
        else {
            final ProgressDialog progressDialog = ProgressDialog.show(UserRegistration.this, "Please wait..", "Processing..", true);
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        Toast.makeText(UserRegistration.this, "Successful", Toast.LENGTH_SHORT).show();
                        storeUserToDatabase();
                        Intent moveToHomeScreen = new Intent(UserRegistration.this, DrawerFromSide.class);
                        startActivity(moveToHomeScreen);
                    } else {
                        Toast.makeText(UserRegistration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void storeUserToDatabase() {
        String userId = auth.getCurrentUser().getUid();
        User user = new User(userId, email, password, gender, age, city, firstName, lastName, pledge, defaultProfileIcon);
        ref.child(userId).setValue(user);
    }


}
