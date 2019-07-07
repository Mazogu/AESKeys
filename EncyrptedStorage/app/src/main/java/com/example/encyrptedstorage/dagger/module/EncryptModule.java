package com.example.encyrptedstorage.dagger.module;

import com.example.encyrptedstorage.cipher.CipherWrapper;
import com.example.encyrptedstorage.cipher.KeyStoreWrapper;
import com.example.encyrptedstorage.dagger.scope.EncryptScope;
import com.example.encyrptedstorage.firebase.FirebaseDB;
import com.example.encyrptedstorage.ui.encrypt.EncryptContract;
import com.example.encyrptedstorage.ui.encrypt.EncryptPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EncryptModule {

    private EncryptContract.EView view;

    public EncryptModule(EncryptContract.EView view){
        this.view = view;
    }

    @Provides
    @EncryptScope
    EncryptContract.EPresenter providesEPresenter(FirebaseDB firebaseDB, KeyStoreWrapper keyStore, CipherWrapper cipher){
        return new EncryptPresenter(view, firebaseDB, keyStore, cipher);
    }
}
