package com.example.encyrptedstorage.ui;

public interface BasePresenter<V extends BaseView> {
    /**
     * Informs the presenter that the view is being destroyed and should relinquish the instance of the view.
     */
    void detachView();
}
