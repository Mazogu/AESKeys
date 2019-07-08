package com.example.encyrptedstorage.dagger.module;

import com.example.encyrptedstorage.cipher.CipherWrapper;
import com.example.encyrptedstorage.cipher.KeyStoreWrapper;
import com.example.encyrptedstorage.dagger.scope.EncryptScope;
import com.example.encyrptedstorage.firebase.FirebaseDB;
import com.example.encyrptedstorage.ui.encrypt.EncryptContract;
import com.example.encyrptedstorage.ui.encrypt.EncryptPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Handles creating the provider for the Encryption Fragment.
 */
@Module
public class EncryptModule {

    private EncryptContract.EView view;

    public EncryptModule(EncryptContract.EView view){
        this.view = view;
    }

    /**
     * Uses the provides from the AppModule to instantiate the presenter for the Encryption Fragment.
     * @param firebaseDB
     * @param keyStore
     * @param cipher
     * @return
     */
    @Provides
    @EncryptScope
    EncryptContract.EPresenter providesEPresenter(FirebaseDB firebaseDB, KeyStoreWrapper keyStore, CipherWrapper cipher){
        return new EncryptPresenter(view, firebaseDB, keyStore, cipher);
    }
}
