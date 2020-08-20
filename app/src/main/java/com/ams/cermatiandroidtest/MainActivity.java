package com.ams.cermatiandroidtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ams.cermatiandroidtest.adapter.UserAdapter;
import com.ams.cermatiandroidtest.model.User;
import com.ams.cermatiandroidtest.viewModel.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private UserAdapter adapter;
    private EditText edtUsername;
    private ProgressBar progressBar;
    private Button btnSearch;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.editUser);
        progressBar = findViewById(R.id.progressBar);
        btnSearch = findViewById(R.id.btnSearch);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                if (TextUtils.isEmpty(username)) return;
                showLoading(true);
                mainViewModel.setUserSearch(username);
            }
        });

        mainViewModel.getUsers().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> userItems) {
                if(mainViewModel.getTotalCount() == 0){
                    Toast.makeText(getApplicationContext(),"no result",
                            Toast.LENGTH_LONG).show();
                    showLoading(false);
                }else{
                    Toast.makeText(getApplicationContext(),"get " + mainViewModel.getTotalCount() + " result",
                            Toast.LENGTH_LONG).show();
                    if (userItems != null) {
                        adapter.setData(userItems);
                        showLoading(false);
                    }
                }
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}