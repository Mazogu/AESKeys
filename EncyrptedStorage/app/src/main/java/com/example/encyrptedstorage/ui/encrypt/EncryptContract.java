package com.example.encyrptedstorage.ui.encrypt;

import com.example.encyrptedstorage.ui.BasePresenter;
import com.example.encyrptedstorage.ui.BaseView;

public interface EncryptContract {
    interface EView extends BaseView{

    }

    interface EPresenter extends BasePresenter<EView>{
        /**
         * Sends plaintext to be encrypted through the presenter.
         * @param encryptionString Plain text to be encrypted.
         */
        void encrypt(String encryptionString);
    }
}
