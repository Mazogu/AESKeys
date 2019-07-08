package com.example.encyrptedstorage.firebase;

import android.support.annotation.NonNull;

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

    public void init(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }


    public void attachCallback(CallBack callBack){
        this.callBack = callBack;
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot node: dataSnapshot.getChildren()) {
                    String data = node.getValue(String.class);
                    if(FirebaseDB.this.callBack != null) {
                        FirebaseDB.this.callBack.retrieveData(data);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        reference.addValueEventListener(listener);
    }

    public void sendMessage(String text){
        reference.push().setValue(text);
    }

    public void removeListener(){
        reference.removeEventListener(listener);
        listener = null;
        callBack = null;
    }

    public interface CallBack{
        void retrieveData(String data);
    }

}
