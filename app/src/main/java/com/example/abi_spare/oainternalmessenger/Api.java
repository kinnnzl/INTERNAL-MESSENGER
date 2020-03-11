package com.example.abi_spare.oainternalmessenger;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.Call;

public interface Api {
    @POST("InsertData")
    Call<ResposeInsert> VerifyUserLogin(
            @Body InsertDataModel M
    );
}
