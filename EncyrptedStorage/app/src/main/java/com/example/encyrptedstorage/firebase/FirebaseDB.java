package com.example.encyrptedstorage.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Wrapper to handle database interactions.
 */
public class FirebaseDB {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private CallBack callBack;
    private ValueEventListener listener;
    private ChildEventListener childEventListener;

    /**
     * Initializes database and reference point.
     */
    public void init(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }


    /**
     * Add listeners to communicate with the callback.
     * @param callBack
     */
    public void attachCallback(final CallBack callBack){
        this.callBack = callBack;

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(callBack != null)
                    callBack.retrieveData(dataSnapshot.getValue(String.class),dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                callBack.removeData(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        reference.addChildEventListener(childEventListener);
    }

    /**
     * Sends text to database.
     * @param text text to send.
     */
    public void sendMessage(String text){
        reference.push().setValue(text);
    }

    /**
     * Removes listeners and callbacks.
     */
    public void removeListeners(){
        reference.removeEventListener(childEventListener);
        listener = null;
        childEventListener = null;
        callBack = null;
    }

    public interface CallBack{
        /**
         * Sends data to callback.
         * @param data
         * @param Key
         */
        void retrieveData(String data,String Key);

        /**
         * Informs callback that an entry should be removed.
         * @param key
         */
        void removeData(String key);
    }

}
