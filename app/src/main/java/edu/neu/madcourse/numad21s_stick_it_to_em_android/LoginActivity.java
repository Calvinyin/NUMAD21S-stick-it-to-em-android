package edu.neu.madcourse.numad21s_stick_it_to_em_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
    }

    public void login(View view) {
        loginButton = (Button)findViewById(R.id.button_login);
        username = usernameEditText.getText().toString();
        Log.d(TAG, "username = " + username);

        if (username.equals(null) || username.length() == 0) {
            Toast.makeText(LoginActivity.this, "Please input a username!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }
}