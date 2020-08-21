package com.ams.cermatiandroidtest.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.cermatiandroidtest.model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    int totalCount;
    private MutableLiveData<ArrayList<User>> listUsers = new MutableLiveData<>();
    private MutableLiveData<ArrayList<User>> listFollowers = new MutableLiveData<>();
    private MutableLiveData<ArrayList<User>> listFollowing = new MutableLiveData<>();

    public MutableLiveData<ArrayList<User>> getListFollowers() {
        return listFollowers;
    }

    public void setListFollowers(MutableLiveData<ArrayList<User>> listFollowers) {
        this.listFollowers = listFollowers;
    }

    public MutableLiveData<ArrayList<User>> getListFollowing() {
        return listFollowing;
    }

    public void setListFollowing(MutableLiveData<ArrayList<User>> listFollowing) {
        this.listFollowing = listFollowing;
    }

    public MutableLiveData<ArrayList<User>> getListUsers() {
        return listUsers;
    }

    public void setListUsers(MutableLiveData<ArrayList<User>> listUsers) {
        this.listUsers = listUsers;
    }

    public void setUserSearch(final String username) {
        final ArrayList<User> listItems = new ArrayList<>();
        String url = "https://api.github.com/search/users?q=" + username;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", "request");
        client.addHeader("Authentication", "token <d385475e3e956289d566497e8233f1b0ee439895>");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("items");

                    totalCount = responseObject.getInt("total_count");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject users = list.getJSONObject(i);
                        User user = new User();
                        user.setId(users.getInt("id"));
                        user.setName(users.getString("login"));
                        user.setLogin(users.getString("login"));
                        user.setAvatar(users.getString("avatar_url"));
                        user.setUrl(users.getString("url"));
                        user.setFollowers_url(users.getString("followers_url"));
                        String userlogin = users.getString("login");
                        String string = "https://api.github.com/users/" + userlogin + "/following";
                        user.setFollowing_url(string);
                        listItems.add(user);
                    }

                    listUsers.postValue(listItems);
                } catch (JSONException e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public int getTotalCount() {
        return totalCount;
    }

    void setUserFollowers(String url) {
        final ArrayList listItems = new ArrayList<User>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", "request");
        client.addHeader("Authentication", "token <242601a424709b54192c26af36d49240b64217ac>");
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    String result = responseString;
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("items");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject users = list.getJSONObject(i);
                        User user = new User();
                        user.setId(users.getInt("id"));
                        user.setLogin(users.getString("login"));
                        user.setAvatar(users.getString("avatar_url"));
                        user.setUrl(users.getString("url"));
                        listItems.add(user);
                    }

                    listFollowers.postValue(listItems);
                } catch (JSONException e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("onFailure", throwable.getMessage());
            }

        });
    }

    void setUserFollowing(String url) {
        final ArrayList listItems = new ArrayList<User>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", "request");
        client.addHeader("Authentication", "token <242601a424709b54192c26af36d49240b64217ac>");
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    String result = responseString;
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("items");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject users = list.getJSONObject(i);
                        User user = new User();
                        user.setId(users.getInt("id"));
                        user.setLogin(users.getString("login"));
                        user.setAvatar(users.getString("avatar_url"));
                        user.setUrl(users.getString("url"));
                        listItems.add(user);
                    }

                    listFollowers.postValue(listItems);
                } catch (JSONException e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("onFailure", throwable.getMessage());
            }

        });
    }

    public LiveData<ArrayList<User>> getUsers() {
        return listUsers;
    }
}
