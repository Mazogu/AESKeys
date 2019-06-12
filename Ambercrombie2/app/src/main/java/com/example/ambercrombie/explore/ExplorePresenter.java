package com.example.ambercrombie.explore;

import com.example.ambercrombie.BasePresenter;
import com.example.ambercrombie.BaseView;
import com.example.ambercrombie.data.Explorative;
import com.example.ambercrombie.retrofit.RetrofitHelper;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.ambercrombie.explore.ExploreContract.*;

public class ExplorePresenter implements EPresenter {

    private final static String url = "https://www.abercrombie.com/";
    private EView view;

    /**
     * Gets Abstract reference to the fragment it communicates to;
     * @param view
     */
    @Override
    public void attachView(EView view) {
        this.view = view;
    }

    /**
     * Uses an observer to get emitted list to display and returns it to the fragment.
     */
    @Override
    public void getCards() {
        RetrofitHelper.getExploratives(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Explorative>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Explorative> exploratives) {
                        view.sendResult(exploratives);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.showError("Something went wrong. Check log.");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
