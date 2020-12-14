package com.keennhoward.retrofitmvvmjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {

    public UserModel(List<DataModel> data) {
        this.data = data;
    }

    @SerializedName("id")
    private int id;
    @SerializedName("per_page")
    private int per_page;
    @SerializedName("total")
    private int total;
    @SerializedName("total_pages")
    private int total_pages;

    public List<DataModel> getData() {
        return data;
    }

    @SerializedName("data")
    private List<DataModel> data;

}
