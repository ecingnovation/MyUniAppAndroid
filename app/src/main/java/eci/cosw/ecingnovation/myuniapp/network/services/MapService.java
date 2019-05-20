package eci.cosw.ecingnovation.myuniapp.network.services;

import java.util.List;

import eci.cosw.ecingnovation.myuniapp.network.model.InterestPoint;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MapService {

    @GET("map/points")
    Call<List<InterestPoint>> getAllInterestPoints();

    @GET("map/points/{id}")
    Call<InterestPoint> getInterestPointById(@Path("id") String id );

    @POST("map/points")
    Call<ResponseBody> createPoint(String id, String title, String label, Double lng, Double lat, String description, String image);

    @DELETE ("map/points/{id}")
    Call<ResponseBody> removePlayer(@Path("id") String id );
}
