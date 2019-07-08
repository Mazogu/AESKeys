package com.example.encyrptedstorage.ui.decrypt;

import com.example.encyrptedstorage.cipher.CipherWrapper;
import com.example.encyrptedstorage.cipher.KeyStoreWrapper;
import com.example.encyrptedstorage.firebase.FirebaseDB;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
        database.removeListeners();
    }

    @Override
    public void retrieveData(String data, String key) {
        try {
            cipher.init();
            Key secretKey = keyStoreWrapper.getKey(ALIAS);
            String decrypted = cipher.decrypt(data, secretKey);
            view.addEntry(decrypted,key);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | UnrecoverableEntryException | KeyStoreException
               | InvalidKeyException | BadPaddingException | IllegalBlockSizeException
                | InvalidAlgorithmParameterException | UnsupportedEncodingException e) {
            e.printStackTrace();
            view.sendError(String.format("%s has occurred. Can't decrypt data",e.getClass().getName()));
        }
    }

    @Override
    public void removeData(String key) {
        view.removeEntry(key);
    }
}
