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
import com.example.encyrptedstorage.dagger.module.EncryptModule;
import com.example.encyrptedstorage.ui.SecurityApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EncryptFragment extends Fragment implements EncryptContract.EView{

    @BindView(R.id.encrypt_text)
    EditText encryption;

    @Inject
    EncryptContract.EPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((SecurityApp) getActivity().getApplication())
                .getAppComponent()
                .newEncryptComponent(new EncryptModule(this))
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encrypt,container,false);
        ButterKnife.bind(this, view);
        return view;
    }
}
