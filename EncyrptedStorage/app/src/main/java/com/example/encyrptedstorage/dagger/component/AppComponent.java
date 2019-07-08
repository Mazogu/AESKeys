package com.example.encyrptedstorage.dagger.component;

import com.example.encyrptedstorage.dagger.module.AppModule;
import com.example.encyrptedstorage.dagger.module.DecryptModule;
import com.example.encyrptedstorage.dagger.module.EncryptModule;
import com.example.encyrptedstorage.ui.SecurityApp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Base component.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    /**
     * Adds subcomponent for decryption.
     * @param module
     * @return
     */
    DecryptComponent newDecryptComponent(DecryptModule module);

    /**Adds subcomponent for encryption.
     * @param module
     * @return
     */
    EncryptComponent newEncryptComponent(EncryptModule module);
}
