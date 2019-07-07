package com.example.encyrptedstorage.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.encyrptedstorage.R;
import com.example.encyrptedstorage.ui.decrypt.DecryptFragment;
import com.example.encyrptedstorage.ui.encrypt.EncryptFragment;

public class SecurityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        Fragment textFragment = new EncryptFragment();
        Fragment listFragment = new DecryptFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.send_text,textFragment)
                .replace(R.id.decrypted_list,listFragment)
                .commit();
    }
}
