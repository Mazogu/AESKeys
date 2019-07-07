package com.example.encyrptedstorage.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDB {
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public void init(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    public void sendMessage(String text){
        reference.push().setValue(text);
    }

}
