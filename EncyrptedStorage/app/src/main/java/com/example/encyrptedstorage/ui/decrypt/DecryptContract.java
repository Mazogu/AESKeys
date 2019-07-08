package com.example.encyrptedstorage.ui.decrypt;

import com.example.encyrptedstorage.ui.BasePresenter;
import com.example.encyrptedstorage.ui.BaseView;

public interface DecryptContract {
    interface DView extends BaseView{
        /**
         * Adds decrypted entry to the recyclerview.
         * @param data Decrypted String.
         * @param key Database key value.
         */
        void addEntry(String data, String key);

        /**
         * Informs view that a value needs to be removed from the recyclerview.
         * @param key Key of the value to be removed.
         */
        void removeEntry(String key);
    }
    interface DPresenter extends BasePresenter<DView>{

    }
}
