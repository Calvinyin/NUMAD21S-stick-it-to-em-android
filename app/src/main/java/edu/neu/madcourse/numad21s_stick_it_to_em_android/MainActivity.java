package edu.neu.madcourse.numad21s_stick_it_to_em_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model.PredefinedSticker;
import edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseController mDatabaseController;
    private ViewAdapter mReceivedStickerAdapter;
    private TextView mSentStickerCounterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDatabase();
        setupReceivedStickerRecyclerView();
        setupSentStickerCounter();
        setupStickerPicker();
        subscribeToDatabaseUpdates();
    }

    private void setupDatabase() {
        Intent intent = getIntent();
        String userName = intent.getExtras().getString("userName");
        mDatabaseController = new DatabaseController(userName);
    }

    private void setupReceivedStickerRecyclerView() {
        RecyclerView mReceivedStickerRecyclerView = findViewById(R.id.received_sticker_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mReceivedStickerRecyclerView.setLayoutManager(layoutManager);

        mReceivedStickerAdapter = new ViewAdapter(this, new ArrayList<>());
        mReceivedStickerRecyclerView.setAdapter(mReceivedStickerAdapter);
    }

    private void setupSentStickerCounter() {
        mSentStickerCounterTextView = findViewById(R.id.sent_sticker_counter);
    }

    private void setupStickerPicker() {
        List<String> stickerList = new ArrayList<>(PredefinedSticker.getStickerMap().values());
        RecyclerView mStickerListView = findViewById(R.id.sticker_picker);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mStickerListView.setLayoutManager(layoutManager);
        StickerSelectorViewAdapter stickerAdapter = new StickerSelectorViewAdapter(this, stickerList, mDatabaseController);
        mStickerListView.setAdapter(stickerAdapter);
    }

    private void subscribeToDatabaseUpdates() {
        mDatabaseController.subscribeToUpdate(new DatabaseController.StickerDataListener() {
            @Override
            public void onDataChange(User user) {
                updateSentStickerCounter(user);
                updateReceivedStickerRecycleView(user);
            }
        });
    }

    private void updateSentStickerCounter(User user) {
        mSentStickerCounterTextView.setText("Number of stickers sent: " + String.valueOf(user.sentStickers.size() - 1));
    }

    private void updateReceivedStickerRecycleView(User user) {
        ArrayList<String> receivedStickers = user.receivedStickers;
        for (int i = 0; i <  receivedStickers.size(); i++) {
            if (receivedStickers.get(i).equalsIgnoreCase(User.PLACE_HOLDER_STICKER)) {
                receivedStickers.remove(i);
            }
        }
        Collections.reverse(receivedStickers);
        mReceivedStickerAdapter.setmItemList(receivedStickers);
        mReceivedStickerAdapter.notifyDataSetChanged();
    }
}