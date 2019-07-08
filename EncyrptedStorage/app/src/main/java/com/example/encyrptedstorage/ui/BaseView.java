package com.example.encyrptedstorage.ui;

public interface BaseView {
    /**
     * Sends a string to inform the view of a problem.
     * @param s An error string.
     */
    void sendError(String s);
}
