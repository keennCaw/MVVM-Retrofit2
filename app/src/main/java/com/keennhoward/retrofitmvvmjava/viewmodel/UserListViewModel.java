package com.keennhoward.retrofitmvvmjava.viewmodel;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.keennhoward.retrofitmvvmjava.model.DataModel;
import com.keennhoward.retrofitmvvmjava.model.UserModel;
import com.keennhoward.retrofitmvvmjava.network.APIService;
import com.keennhoward.retrofitmvvmjava.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListViewModel extends ViewModel {

    //create observer livedata
    private MutableLiveData<List<DataModel>> usersList;


    public UserListViewModel(){
        usersList = new MutableLiveData<>();
    }

    public MutableLiveData<List<DataModel>> getUsersListObserver(){
        return usersList;
    }

    public void makeApiCall(){
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<UserModel> call = apiService.getUsers();
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = (UserModel) response.body();
                Log.d("url","response.raw().request().url();"+response.raw().request().url());
                //Log.d("response", userModel.getData().toString());
                usersList.postValue(userModel.getData());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                usersList.postValue(null);
                Log.d("null", t.getMessage());
            }
        });
        /*
        Call<List<UserModel>> call = apiService.getUsers();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                    UserModel userModel = (UserModel) response.body();
                    Log.d("url","response.raw().request().url();"+response.raw().request().url());
                    //Log.d("response", userModel.getData().toString());
                    usersList.postValue(userModel.getData());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                usersList.postValue(null);
                Log.d("null", t.getMessage());
            }
        });*/
    }

}
