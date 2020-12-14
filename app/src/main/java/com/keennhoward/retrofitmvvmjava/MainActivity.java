package com.keennhoward.retrofitmvvmjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.keennhoward.retrofitmvvmjava.adapter.UserListAdapter;
import com.keennhoward.retrofitmvvmjava.model.DataModel;
import com.keennhoward.retrofitmvvmjava.viewmodel.UserListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DataModel> dataModelList;
    private UserListAdapter adapter;
    private UserListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        final TextView noResultTv = findViewById(R.id.noResultTextView);

        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new UserListAdapter(this, dataModelList);

        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        viewModel.getUsersListObserver().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModels) {
                if (dataModels != null){
                    dataModelList = dataModels;
                    adapter.setUserList(dataModels);
                    noResultTv.setVisibility(View.GONE);
                }else{
                    noResultTv.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }
}
