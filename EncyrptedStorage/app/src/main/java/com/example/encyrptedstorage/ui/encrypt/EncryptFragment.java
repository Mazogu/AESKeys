package com.example.encyrptedstorage.ui.encrypt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.encyrptedstorage.R;
import com.example.encyrptedstorage.dagger.module.EncryptModule;
import com.example.encyrptedstorage.ui.SecurityApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    /**
     * Sends text currently in the textview to be encrypted and added to firebase.
     */
    @OnClick(R.id.encrypt_button)
    public void sendData(){
        String encryptionString = encryption.getText().toString();
        presenter.encrypt(encryptionString);
    }

    @Override
    public void sendError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
