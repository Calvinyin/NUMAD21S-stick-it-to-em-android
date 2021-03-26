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

    private Button mLoginButton;
    private EditText mUserNameEditText;
    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserNameEditText = (EditText) findViewById(R.id.editText_username);
    }

    public void login(View view) {
        mLoginButton = (Button)findViewById(R.id.button_login);
        mUserName = mUserNameEditText.getText().toString();
        Log.d(TAG, "username = " + mUserName);

        if (mUserName.equals(null) || mUserName.length() == 0) {
            Toast.makeText(LoginActivity.this, "Please input a username!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("userName", mUserName);
            startActivity(intent);
        }
    }
}