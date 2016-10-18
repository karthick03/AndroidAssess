package com.example.karthickramjee.androidlab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText name,password;
    Button login;
    DBHelper dbHelper;
    private final String PREF="value";
    private final String NAME="name";
    private final String PASSWORD="password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        dbHelper=new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameval=name.getText().toString();
                String passwordval=password.getText().toString();
                if(!nameval.matches("") &&  !passwordval.matches(""))
                {
                    if(dbHelper.checkDetails(nameval,passwordval))
                    {
                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(PREF,2);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString(NAME,nameval);
                        editor.putString(PASSWORD,passwordval);
                        editor.commit();
                        Intent intent=new Intent(LoginActivity.this,FirstActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid Details",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid details",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
