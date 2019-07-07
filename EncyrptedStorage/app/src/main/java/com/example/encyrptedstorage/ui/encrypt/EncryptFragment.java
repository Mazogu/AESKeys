package com.example.encyrptedstorage.ui.encrypt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.encyrptedstorage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EncryptFragment extends Fragment {

    @BindView(R.id.encrypt_text)
    EditText encryption;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encrypt,container,false);
        ButterKnife.bind(this, view);
        return view;
    }
}
