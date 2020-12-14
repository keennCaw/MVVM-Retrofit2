package com.keennhoward.retrofitmvvmjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.keennhoward.retrofitmvvmjava.R;
import com.keennhoward.retrofitmvvmjava.model.DataModel;
import com.keennhoward.retrofitmvvmjava.model.UserModel;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {
    private Context context;
    private List<DataModel> userList;

    public UserListAdapter(Context context, List<DataModel> userList) {
        this.context = context;
        this.userList = userList;
    }

    public void setUserList(List<DataModel> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.emailTextView.setText(this.userList.get(position).getEmail().toString());
        Glide.with(context)
                .load(this.userList.get(position).getAvatar())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {

        if(this.userList != null){
            return this.userList.size();
        }

        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView emailTextView;
        ImageView avatarImageView;

        public MyViewHolder(View itemView){
            super(itemView);
            emailTextView = itemView.findViewById(R.id.emailView);
            avatarImageView = itemView.findViewById(R.id.avatarView);
        }
    }
}
