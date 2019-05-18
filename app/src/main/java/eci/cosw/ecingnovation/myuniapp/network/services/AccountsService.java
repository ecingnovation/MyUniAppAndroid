package eci.cosw.ecingnovation.myuniapp.network.services;

import eci.cosw.ecingnovation.myuniapp.network.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AccountsService {
    @GET("/users/private/{user}")
    Call<User> getUserByEmail(@Path("user") String user);
}
