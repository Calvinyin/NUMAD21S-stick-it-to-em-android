package edu.neu.madcourse.numad21s_stick_it_to_em_android;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.neu.madcourse.numad21s_stick_it_to_em_android.database.model.User;

public class DatabaseController {

    private static final String RECEIVER_USER_NAME = "Receiver";

    private String mUserName;
    private DatabaseReference mDatabase;
    private DatabaseReference mUserReference;
    private DatabaseReference mReceiverReference;

    public DatabaseController(String userName) {
        mUserName = userName;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserReference = mDatabase.child("users").child(mUserName);
        mReceiverReference = mDatabase.child("users").child(RECEIVER_USER_NAME);
    }

    public void subscribeToUpdate(StickerDataListener stickerDataListener) {
        mUserReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        if (user == null) {
                            user = new User(mUserName);
                            mUserReference.setValue(user);
                        }
                        stickerDataListener.onDataChange(user);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

    public void sendSticker(String sticker) {
        mUserReference.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                User user = currentData.getValue(User.class);
                if (user == null) {
                    user = new User(mUserName);
                }
                user.sentStickers.add(sticker);
                currentData.setValue(user);
                return Transaction.success(currentData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {

            }
        });
        mReceiverReference.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                User receiver = currentData.getValue(User.class);
                if (receiver == null) {
                    receiver = new User(mUserName);
                }
                receiver.receivedStickers.add(sticker);
                currentData.setValue(receiver);
                return Transaction.success(currentData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {

            }
        });
    }

    public interface StickerDataListener {
        void onDataChange(User user);
    }
}
