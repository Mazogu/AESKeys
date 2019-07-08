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

/**
 * Handle providing classes that all components will use.
 */
@Module
public class AppModule {

    /**
     * Creates an instance of the database wrapper.
     * @return
     */
    @Singleton
    @Provides
    FirebaseDB providesFireBaseDB(){
        FirebaseDB firebaseDB = new FirebaseDB();
        firebaseDB.init();
        return firebaseDB;
    }

    /**
     * @return Wrapper for the cipher class.
     */
    @Provides
    CipherWrapper providesCipherWrapper(){return new CipherWrapper();}

    /**
     * @return Wrapper for the keystore.
     */
    @Provides
    KeyStoreWrapper providesKeyStoreWrapper(){
        try {
            return new KeyStoreWrapper();
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
