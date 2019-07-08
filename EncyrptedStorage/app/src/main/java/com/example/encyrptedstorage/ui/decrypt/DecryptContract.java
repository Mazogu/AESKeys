package com.example.encyrptedstorage.ui.decrypt;

import com.example.encyrptedstorage.ui.BasePresenter;
import com.example.encyrptedstorage.ui.BaseView;

public interface DecryptContract {
    interface DView extends BaseView{
        void addEntry(String data);
    }
    interface DPresenter extends BasePresenter<DView>{

    }
}
