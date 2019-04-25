package eci.cosw.ecingnovation.myuniapp.network;

import eci.cosw.ecingnovation.myuniapp.network.services.LoginService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit;
    // TODO Change to Heroku API
    private static final String BASE_URL = "https://myuniapp-back.herokuapp.com/";

    /**
     * Returns a Normal Retrofit Instance
     * @return retrofit instance
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Returns a LoginService created by the Retrofit Instance
     * @return LoginService instance
     */
    public static LoginService getLoginService() {
        Retrofit retrofit = getRetrofitInstance();
        return retrofit.create(LoginService.class);
    }
}
