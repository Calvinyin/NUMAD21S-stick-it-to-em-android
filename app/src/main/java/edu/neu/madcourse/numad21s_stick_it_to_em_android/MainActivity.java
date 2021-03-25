package edu.neu.madcourse.numad21s_stick_it_to_em_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model.PredefinedSticker;
import edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model.User;
import edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model.ViewAdapter;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String SERVER_KEY = "AAAAmOjnH5o:APA91bFwHsIV0EXHxDisiJfglTmyMFkzzbuHCQEYeoMrKSYC4z_M-G0llCjEwnLJvgtoia3qM0B3g5s4-thuah6AvowAlcSK14rYzxdpVmdhPk3fF7vxGwvouPmSTfsUTUdNZZilN3eq";
    private static final String TAG = "MainActivity";

    // layout component
    private Button historyButton;
    private TextView displayTextView;
    private ListView stickerListView;
    private RecyclerView receivedRecyclerView;

    // user information
    private String username;
    private User user;
    private ArrayList<String> stickerList;
    private ArrayList<String> receivedStickers;

    private ArrayAdapter<String> stickerAdapter;
    private RecyclerView.Adapter receivedAdapter;

    private PredefinedSticker predefinedSticker;

    // Firebase database
    private DatabaseReference mDatabase;


    // resource manager
    private LinearLayoutManager layoutManager;
    private ActivityManager activityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stickerList = new ArrayList<>();

        predefinedSticker = new PredefinedSticker();

        displayTextView = findViewById(R.id.displayTextView);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        // obtain username
        Intent intent = getIntent();
        username = intent.getExtras().getString("username");

        DatabaseReference userNameReference = mDatabase.child("user").child(username);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // if the current user is not in database
                if (!snapshot.exists()) {
                    user = new User(username);
                    // insert it to db
                    mDatabase.child("user").child(username).setValue(user);

                    // subscribe to news
                    FirebaseMessaging.getInstance().subscribeToTopic("news")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg = "msg Subscribed";
                                    if (!task.isSuccessful()) {
                                        msg = "Subscribe Failed";
                                    }
                                    Log.d(TAG, msg);
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                }
                            });
                } else {
                    User newUser = snapshot.getValue(User.class);
                    newUser = user;
                    displayTextView.setText("Number of stickers sent: " + user.sentStickers.size());
                    // add predefined stickers to list view
                    stickerList.addAll(predefinedSticker.getStickerMap().values());
                    stickerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage());
            }
        };

        userNameReference.addListenerForSingleValueEvent(valueEventListener);

        // create sticker list
        stickerListView = findViewById(R.id.StickerListView);
        stickerAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, stickerList);
        stickerListView.setAdapter(stickerAdapter);


        receivedRecyclerView = findViewById(R.id.receivedRecyclerView);

        if (savedInstanceState != null && savedInstanceState.getStringArrayList("data") != null) {
            receivedStickers = savedInstanceState.getStringArrayList("data");
        } else {
            receivedStickers = new ArrayList<>();
        }

        layoutManager = new LinearLayoutManager(this);
        receivedRecyclerView.setLayoutManager(layoutManager);

        receivedAdapter = new ViewAdapter(MainActivity.this, receivedStickers);
        receivedRecyclerView.setAdapter(receivedAdapter);

    }
}