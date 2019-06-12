package com.example.ambercrombie;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
}
