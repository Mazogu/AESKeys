package com.example.encyrptedstorage.ui.decrypt;

import com.example.encyrptedstorage.cipher.CipherWrapper;
import com.example.encyrptedstorage.cipher.KeyStoreWrapper;
import com.example.encyrptedstorage.firebase.FirebaseDB;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public class DecryptPresenter implements DecryptContract.DPresenter, FirebaseDB.CallBack {

    private DecryptContract.DView view;
    private KeyStoreWrapper keyStoreWrapper;
    private CipherWrapper cipher;
    private FirebaseDB database;
    private final static String ALIAS = "Encryption Go";


    public DecryptPresenter(DecryptContract.DView view, FirebaseDB database, KeyStoreWrapper keyStoreWrapper, CipherWrapper cipher){
        this.view = view;
        this.cipher = cipher;
        this.database = database;
        this.keyStoreWrapper = keyStoreWrapper;
        database.attachCallback(this);
    }

    @Override
    public void detachView() {
        view = null;
        database.removeListener();
    }

    @Override
    public void retrieveData(String data) {
        try {
            cipher.init();
            Key key = keyStoreWrapper.getKey(ALIAS);
            String decrypted = cipher.decrypt(data, key);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | UnrecoverableEntryException | KeyStoreException
               | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            view.sendError(String.format("%s has occurred. Can't decrypt data",e.getClass().getName()));
        }
        view.addEntry(data);
    }
}
