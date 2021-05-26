package com.imf.haryanachi.network;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    Retrofit retrofit = null;

    public static ApiInterface getApiClient() {


        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(MyApplication.getAppContext()))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.WEB_HOST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
              //  .client(client)
                .build();

        Log.v("urllls",""+AppConstants.WEB_HOST_URL);
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        return apiInterface;

    }

}
