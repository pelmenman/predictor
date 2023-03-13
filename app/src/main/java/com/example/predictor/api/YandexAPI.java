package com.example.predictor.api;

import com.example.predictor.model.Answer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexAPI {

    /*
    https://predictor.yandex.net - server name
    /api/v1/predict.json/complete - path to server's method
    ?

    Call is necessary for Retrofit library
     */
    @GET("/api/v1/predict.json/complete")
    Call<Answer> getComplete(@Query("key") String key,
                             @Query("q") String q,
                             @Query("lang") String lang,
                             @Query("limit") Integer limit);


}
