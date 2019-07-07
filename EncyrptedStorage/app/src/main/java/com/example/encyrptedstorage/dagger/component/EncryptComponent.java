package com.example.encyrptedstorage.dagger.component;


import com.example.encyrptedstorage.dagger.module.EncryptModule;
import com.example.encyrptedstorage.dagger.scope.EncryptScope;
import com.example.encyrptedstorage.ui.encrypt.EncryptFragment;

import dagger.Component;
import dagger.Subcomponent;

@EncryptScope
@Subcomponent(modules = {EncryptModule.class})
public interface EncryptComponent {
    void inject(EncryptFragment fragment);
}
