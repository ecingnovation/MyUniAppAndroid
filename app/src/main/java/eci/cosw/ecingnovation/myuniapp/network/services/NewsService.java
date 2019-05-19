package eci.cosw.ecingnovation.myuniapp.network.services;

import java.util.List;

import eci.cosw.ecingnovation.myuniapp.network.model.AppNew;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NewsService {

    @GET("news/all")
    Call<List<AppNew>> getAllNews();

    @POST("news/post")
    Call<ResponseBody> postNew(@Body AppNew appNew);

}
