package com.example.encyrptedstorage.ui.decrypt;

import com.example.encyrptedstorage.cipher.CipherWrapper;
import com.example.encyrptedstorage.cipher.KeyStoreWrapper;
import com.example.encyrptedstorage.firebase.FirebaseDB;

public class DecryptPresenter implements DecryptContract.DPresenter {

    private DecryptContract.DView view;
    private KeyStoreWrapper keyStoreWrapper;
    private CipherWrapper cipher;
    private FirebaseDB database;


    public DecryptPresenter(DecryptContract.DView view, FirebaseDB database, KeyStoreWrapper keyStoreWrapper, CipherWrapper cipher){
        this.view = view;
        this.cipher = cipher;
        this.database = database;
        this.keyStoreWrapper = keyStoreWrapper;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
