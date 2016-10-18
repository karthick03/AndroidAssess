package com.example.karthickramjee.androidlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText name,email,password;
    Button save;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        save=(Button)findViewById(R.id.save);
        dbHelper=new DBHelper(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameval=name.getText().toString();
                String emailval=email.getText().toString();
                String passwordval=password.getText().toString();
                if(!nameval.matches("") && !emailval.matches("") && !passwordval.matches(""))
                {
                    if(dbHelper.check(nameval))
                    {
                        Toast.makeText(getApplicationContext(),"Already User Exists",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    dbHelper.addData(new SignData(nameval,emailval,passwordval));
                    Intent intent=new Intent(SignUpActivity.this,FirstActivity.class);
                    startActivity(intent);
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
