package com.example.encyrptedstorage.ui.decrypt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.encyrptedstorage.R;
import com.example.encyrptedstorage.dagger.module.DecryptModule;
import com.example.encyrptedstorage.ui.SecurityApp;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DecryptFragment extends Fragment implements DecryptContract.DView {
    @BindView(R.id.decrypt_recycle)
    RecyclerView recyclerView;

    DecryptAdapter adapter;

    @Inject
    DecryptContract.DPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((SecurityApp)getActivity().getApplication())
                .getAppComponent()
                .newDecryptComponent(new DecryptModule(this))
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decrypt, container, false);
        ButterKnife.bind(this,view);
        if(adapter == null)
            adapter = new DecryptAdapter(new ArrayList<String>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        return view;
    }

    @Override
    public void sendError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addEntry(String data) {
        if(adapter == null)
            adapter = new DecryptAdapter(new ArrayList<String>());
        adapter.addEntry(data);
    }
}
