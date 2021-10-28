package com.frabbi.mvvmdemo.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.frabbi.mvvmdemo.R;
import com.frabbi.mvvmdemo.databinding.ActivityMainBinding;
import com.frabbi.mvvmdemo.model.NicePlace;
import com.frabbi.mvvmdemo.view.adapters.RecyclerAdapter;
import com.frabbi.mvvmdemo.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private RecyclerAdapter mAdapter;
    private MainActivityViewModel mActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mActivityViewModel.initialize();
        mActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<NicePlace> list) {
                mAdapter.notifyDataSetChanged();
            }
        });

        mActivityViewModel.getProgressUpdate().observe(this,(isBool)->{
            if(isBool){
                setProgressBar(true);
            }else {
                setProgressBar(isBool);
                mBinding.recyclerView.smoothScrollToPosition(mActivityViewModel.getNicePlaces().getValue().size()-1);
            }
        });
        initRecyclerView();
        mBinding.fab.setOnClickListener((v)->{
            NicePlace data = new NicePlace("#","#");
            mActivityViewModel.addNewValue(data);
        });
    }

    private void initRecyclerView() {
        mAdapter = new RecyclerAdapter(this, mActivityViewModel.getNicePlaces().getValue());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    private void setProgressBar(Boolean value) {
        if (value) {
            mBinding.progressBar.setVisibility(ProgressBar.VISIBLE);
        }else {
            mBinding.progressBar.setVisibility(ProgressBar.GONE);
        }
    }
}