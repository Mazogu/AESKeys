package com.example.ambercrombie.retrofit;

import com.example.ambercrombie.data.Explorative;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Class for making Retrofit calls.
 */
public class RetrofitHelper {
    public static class Factory{
        /**
         * Creates retrofit object that converts json requests into a pojo and allows callbacks to be done in RXjava.
         * @param url base url of request
         * @return
         */
        public static Retrofit getRetrofit(String url){
            return new Retrofit.Builder().baseUrl(url).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }


    /**
     * Returns an observable that emits a list of Explorative objects
     * @param url Base url
     * @return
     */
    public static Observable<List<Explorative>> getExploratives(String url){
        RetrofitService service = Factory.getRetrofit(url).create(RetrofitService.class);
        return service.getExploratives();
    }


    /**
     * Creates GET request.
     */
    private interface RetrofitService{
        @Headers({
                "Accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
                "User-Agent: Ambie"
        })
        @GET("anf/nativeapp/qa/codetest/codeTest_exploreData.json")
        Observable<List<Explorative>> getExploratives();
    }
}
