package com.ams.cermatiandroidtest.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.cermatiandroidtest.R;
import com.ams.cermatiandroidtest.model.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> mData = new ArrayList<>();
    public void setData(ArrayList<User> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        return new UserViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        final User current = mData.get(position);

        userViewHolder.textViewUsername.setText(current.getName());
        Glide.with(userViewHolder.itemView.getContext())
                .load(current.getAvatar())
                .apply(new RequestOptions().override(350, 550))
                .into(userViewHolder.imgViewAvatar);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUsername;
        ImageView imgViewAvatar;
        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.txt_username);
            imgViewAvatar = itemView.findViewById(R.id.img_photo);
        }

    }
}
