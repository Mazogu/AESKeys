package com.example.encyrptedstorage.dagger.module;

import com.example.encyrptedstorage.cipher.CipherWrapper;
import com.example.encyrptedstorage.cipher.KeyStoreWrapper;
import com.example.encyrptedstorage.dagger.scope.DecryptScope;
import com.example.encyrptedstorage.firebase.FirebaseDB;
import com.example.encyrptedstorage.ui.decrypt.DecryptContract;
import com.example.encyrptedstorage.ui.decrypt.DecryptPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DecryptModule {

    private DecryptContract.DView view;

    public DecryptModule(DecryptContract.DView view){
        this.view = view;
    }

    @DecryptScope
    @Provides
    DecryptContract.DPresenter providesDPresenter(FirebaseDB database, KeyStoreWrapper keyStore, CipherWrapper cipher){
        return new DecryptPresenter(view, database, keyStore, cipher);
    }
}
