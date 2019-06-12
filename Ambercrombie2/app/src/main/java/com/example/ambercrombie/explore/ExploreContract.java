package com.example.ambercrombie.explore;

import com.example.ambercrombie.BasePresenter;
import com.example.ambercrombie.BaseView;
import com.example.ambercrombie.data.Explorative;

import java.util.List;

public interface ExploreContract {
    interface EView extends BaseView{
        void sendResult(List<Explorative> list);
    }
    interface EPresenter extends BasePresenter<EView>{
        void getCards();
    }
}
