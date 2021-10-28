package com.frabbi.mvvmdemo.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.frabbi.mvvmdemo.R;
import com.frabbi.mvvmdemo.databinding.RecyclerListViewBinding;
import com.frabbi.mvvmdemo.model.NicePlace;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<NicePlace> list;

    public RecyclerAdapter(@NonNull Context mContext, List<NicePlace> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerListViewBinding rBinding = RecyclerListViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,false);
        return new MyViewHolder(rBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rBinding.textId.setText(list.get(position).getTitle());
        Glide.with(mContext)
                .setDefaultRequestOptions(new RequestOptions().error(R.drawable.ic_launcher_background))
                .load(list.get(position).getImgUrl())
                .into(holder.rBinding.profileImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    protected static class MyViewHolder extends RecyclerView.ViewHolder {
      public RecyclerListViewBinding rBinding;
        public MyViewHolder(@NonNull RecyclerListViewBinding rBinding) {
            super(rBinding.getRoot());
            this.rBinding = rBinding;
        }
    }
}
