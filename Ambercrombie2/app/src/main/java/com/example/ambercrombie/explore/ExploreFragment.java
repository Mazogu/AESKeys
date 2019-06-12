package com.example.ambercrombie.explore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ambercrombie.R;
import com.example.ambercrombie.data.Explorative;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExploreFragment extends Fragment implements ExploreContract.EView {

    @BindView(R.id.exploreList)
    RecyclerView recyclerView;

    private ExploreContract.EPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ExplorePresenter();
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this,view);
        presenter.getCards();
        return view;
    }


    /**
     * Called from presenter shows sent string.
     * @param s Message to show.
     */
    @Override
    public void showError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets the list of elements from GET response.
     * @param list List of pojo elements from JSON string
     */
    @Override
    public void sendResult(List<Explorative> list) {
        ExploreAdapter adapter = new ExploreAdapter(list,getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }
}
