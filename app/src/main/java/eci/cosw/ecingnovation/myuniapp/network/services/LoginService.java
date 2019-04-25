package eci.cosw.ecingnovation.myuniapp.network.services;

import eci.cosw.ecingnovation.myuniapp.network.model.LoginWrapper;
import eci.cosw.ecingnovation.myuniapp.network.model.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("accounts/login")
    Call<Token> attemptLogin(@Body LoginWrapper user);

}
