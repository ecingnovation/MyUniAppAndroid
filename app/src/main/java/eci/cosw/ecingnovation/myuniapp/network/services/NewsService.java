package eci.cosw.ecingnovation.myuniapp.network.services;

import java.util.List;

import eci.cosw.ecingnovation.myuniapp.network.model.AppNew;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

    @GET("news/all")
    Call<List<AppNew>> getAllNews();

}
