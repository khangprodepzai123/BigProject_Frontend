package com.example.a65131433_bigproject.api;

import com.example.a65131433_bigproject.models.BacSiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BacSiApi {
    @GET("api/BacSi")
    Call<BacSiResponse> getAllBacSi();

    @GET("api/BacSi/{maBs}")
    Call<BacSiResponse> getBacSiByMa(@Path("maBs") String maBs);
}

