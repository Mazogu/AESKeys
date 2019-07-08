package com.example.encyrptedstorage.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDB {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private CallBack callBack;
    private ValueEventListener listener;
    private ChildEventListener childEventListener;

    public void init(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }


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

    public void sendMessage(String text){
        reference.push().setValue(text);
    }

    public void removeListeners(){
        reference.removeEventListener(childEventListener);
        listener = null;
        childEventListener = null;
        callBack = null;
    }

    public interface CallBack{
        void retrieveData(String data,String Key);

        void removeData(String key);
    }

}
