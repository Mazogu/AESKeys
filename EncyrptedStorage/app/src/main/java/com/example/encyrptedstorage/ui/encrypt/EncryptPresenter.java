package com.example.encyrptedstorage.ui.encrypt;

import com.example.encyrptedstorage.cipher.CipherWrapper;
import com.example.encyrptedstorage.cipher.KeyStoreWrapper;
import com.example.encyrptedstorage.firebase.FirebaseDB;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncryptPresenter implements EncryptContract.EPresenter {

    private EncryptContract.EView view;
    private FirebaseDB database;
    private KeyStoreWrapper keyStore;
    private CipherWrapper cipher;
    private final static String ALIAS = "Encryption Go";

    public EncryptPresenter(EncryptContract.EView view, FirebaseDB database, KeyStoreWrapper keyStore, CipherWrapper cipher) {
        this.view = view;
        this.database = database;
        this.keyStore = keyStore;
        this.cipher = cipher;
    }

    @Override
    public void encrypt(String encryptionString) {
        try {
            cipher.init();
            keyStore.createSecretKey(ALIAS);
            Key key = keyStore.getKey(ALIAS);
            String coded = cipher.encrypt(encryptionString, key);
            database.sendMessage(coded);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException
                | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | KeyStoreException
                | UnsupportedEncodingException | UnrecoverableEntryException e) {
            e.printStackTrace();
            view.sendError(String.format("%s occurred. Unable to encrypt",e.getClass().getName()));
        }
    }

    @Override
    public void detachView() {
        view = null;
        database.removeListeners();
    }
}
