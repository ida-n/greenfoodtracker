package com.t.teamten.greenfoodtracker;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUser extends AppCompatActivity {

    EditText Name;
    EditText Age;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        Name = (EditText) findViewById(R.id.NameID);
        Age =(EditText)findViewById(R.id.AgeID);
        Login = (Button)findViewById(R.id.Login);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Name.getText().toString()!= null ) {
                    NextPage();
                }
                else {
                    if(Name.getText()==null) {
                        Toast.makeText(LoginUser.this, "Empty Name", Toast.LENGTH_SHORT).show();
                    }
                    if(Age.getText()!= null){
                        Toast.makeText(LoginUser.this, "Empty Age", Toast.LENGTH_SHORT).show();
                    }
                    }

            }
        });
    }
    public void NextPage()
    {
        Intent intent = new Intent(this,HomeScreen.class);
        startActivity (intent);
    }

}