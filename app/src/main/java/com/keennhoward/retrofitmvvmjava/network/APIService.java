package com.keennhoward.retrofitmvvmjava.network;

import com.keennhoward.retrofitmvvmjava.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("api/users?page=2")
    Call<UserModel> getUsers();
}
