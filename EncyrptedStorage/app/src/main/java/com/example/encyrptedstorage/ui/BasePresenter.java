package com.example.encyrptedstorage.ui;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
