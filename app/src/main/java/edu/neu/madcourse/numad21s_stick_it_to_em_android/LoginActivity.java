package edu.neu.madcourse.numad21s_stick_it_to_em_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private Button loginButton;
    private EditText usernameEditText;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        usernameEditText = (EditText) findViewById(R.id.editText_username);
        loginButton = findViewById(R.id.button_login);
        username = usernameEditText.getText().toString();

        // navigate to the main activity when click login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}