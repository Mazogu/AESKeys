package com.example.encyrptedstorage.ui.encrypt;

import com.example.encyrptedstorage.cipher.CipherWrapper;
import com.example.encyrptedstorage.cipher.KeyStoreWrapper;
import com.example.encyrptedstorage.firebase.FirebaseDB;

public class EncryptPresenter implements EncryptContract.EPresenter {

    private EncryptContract.EView view;
    private FirebaseDB database;
    private KeyStoreWrapper keyStore;
    private CipherWrapper cipher;

    public EncryptPresenter(EncryptContract.EView view, FirebaseDB database, KeyStoreWrapper keyStore, CipherWrapper cipher) {
        this.view = view;
        this.database = database;
        this.keyStore = keyStore;
        this.cipher = cipher;
    }


    @Override
    public void detachView() {
        view = null;
    }
}
