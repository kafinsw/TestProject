package com.gigs.testproject.API;

import com.gigs.testproject.Model.ResponseLogin;
import com.gigs.testproject.Model.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("register")
    Call<ResponseRegister> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username);
    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> login(
            @Field("password") String password,
            @Field("username") String username);

}
