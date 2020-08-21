package com.ams.cermatiandroidtest.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class User implements Parcelable {

    private int id = 0;
    private String login;
    private String avatar;
    private String url;
    private String followers_url;
    private String following_url;
    private String name;
    private String followers;
    private String following;
    private String repository;
    private String company;
    private String location;

    public User(Parcel in) {
        id = in.readInt();
        login = in.readString();
        avatar = in.readString();
        url = in.readString();
        followers_url = in.readString();
        following_url = in.readString();
        name = in.readString();
        followers = in.readString();
        following = in.readString();
        repository = in.readString();
        company = in.readString();
        location = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeString(avatar);
        dest.writeString(url);
        dest.writeString(followers_url);
        dest.writeString(following_url);
        dest.writeString(name);
        dest.writeString(followers);
        dest.writeString(following);
        dest.writeString(repository);
        dest.writeString(company);
        dest.writeString(location);
    }

    @BindingAdapter("android:imageUrl")
    public static void loadImage(CircleImageView view, String imageUri){
        Glide.with(view.getContext())
                .load(imageUri)
                .apply(new RequestOptions().override(350, 550))
                .into(view);
    }
}
