package com.example.encyrptedstorage.dagger.component;


import com.example.encyrptedstorage.dagger.module.DecryptModule;
import com.example.encyrptedstorage.dagger.scope.DecryptScope;
import com.example.encyrptedstorage.ui.decrypt.DecryptFragment;

import dagger.Component;
import dagger.Subcomponent;

@DecryptScope
@Subcomponent(modules = {DecryptModule.class})
public interface DecryptComponent {
    void inject(DecryptFragment fragment);
}
