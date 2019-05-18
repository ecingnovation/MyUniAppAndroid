package eci.cosw.ecingnovation.myuniapp.network;

import java.io.IOException;

import eci.cosw.ecingnovation.myuniapp.network.services.AccountsService;
import eci.cosw.ecingnovation.myuniapp.network.services.LoginService;
import eci.cosw.ecingnovation.myuniapp.network.services.NewsService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit;
    private static Retrofit retrofit2;
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

    public static Retrofit getRetrofitInterceptorInstance(final String token) {
        if (retrofit2 == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor( new Interceptor()
            {
                @Override
                public okhttp3.Response intercept( Chain chain ) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder().header( "Accept", "application/json" ).header( "Authorization", "Bearer " + token ).method(
                            original.method(), original.body() ).build();
                    return chain.proceed( request );
                }
            } );
            retrofit2 = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        }
        return retrofit2;
    }

    /**
     * Returns a LoginService created by the Retrofit Instance
     * @return LoginService instance
     */
    public static LoginService getLoginService() {
        Retrofit retrofit = getRetrofitInstance();
        return retrofit.create(LoginService.class);
    }

    public static NewsService getNewsService(String token) {
        Retrofit retrofit = getRetrofitInterceptorInstance(token);
        return retrofit.create(NewsService.class);
    }

    public static AccountsService getAccountsService(String token) {
        Retrofit retrofit = getRetrofitInterceptorInstance(token);
        return retrofit.create(AccountsService.class);
    }

}
