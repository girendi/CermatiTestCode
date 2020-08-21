package com.ams.cermatiandroidtest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.cermatiandroidtest.R;
import com.ams.cermatiandroidtest.databinding.UserItemBinding;
import com.ams.cermatiandroidtest.model.User;

import java.util.ArrayList;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> mData = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public void setData(ArrayList<User> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        UserItemBinding mView = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.user_item, viewGroup, false);
        return new UserViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, int position) {
        final User current = mData.get(position);

        userViewHolder.userItemBinding.setUser(current);

        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mData.get(userViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        UserItemBinding userItemBinding;
        UserViewHolder(@NonNull UserItemBinding itemView) {
            super(itemView.getRoot());
            userItemBinding = itemView;
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(User data);
    }

}
