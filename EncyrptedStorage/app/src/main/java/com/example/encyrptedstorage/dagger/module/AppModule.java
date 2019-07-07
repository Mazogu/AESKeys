package com.example.encyrptedstorage.dagger.module;

import com.example.encyrptedstorage.cipher.CipherWrapper;
import com.example.encyrptedstorage.cipher.KeyStoreWrapper;
import com.example.encyrptedstorage.firebase.FirebaseDB;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    FirebaseDB providesFireBaseDB(){
        FirebaseDB firebaseDB = new FirebaseDB();
        firebaseDB.init();
        return firebaseDB;
    }

    @Provides
    CipherWrapper providesCipherWrapper(){return new CipherWrapper();}

    @Provides
    KeyStoreWrapper providesKeyStoreWrapper(){
        try {
            return new KeyStoreWrapper();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
