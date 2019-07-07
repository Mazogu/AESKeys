package com.example.encyrptedstorage.dagger.component;

import com.example.encyrptedstorage.dagger.module.AppModule;
import com.example.encyrptedstorage.dagger.module.DecryptModule;
import com.example.encyrptedstorage.dagger.module.EncryptModule;
import com.example.encyrptedstorage.ui.SecurityApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    DecryptComponent newDecryptComponent(DecryptModule module);

    EncryptComponent newEncryptComponent(EncryptModule module);
}
