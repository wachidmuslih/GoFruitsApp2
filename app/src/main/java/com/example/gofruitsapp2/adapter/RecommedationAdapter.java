package com.example.gofruitsapp2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gofruitsapp2.R;
import com.example.gofruitsapp2.models.RecommendationModels;

import java.util.List;

public class RecommedationAdapter extends RecyclerView.Adapter<RecommedationAdapter.ViewHolder> {
    Context context;
    List<RecommendationModels> rAdapterList;

    public RecommedationAdapter(Context context, List<RecommendationModels> rAdapterList) {
        this.context = context;
        this.rAdapterList = rAdapterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(rAdapterList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(rAdapterList.get(position).getName());
        holder.description.setText(rAdapterList.get(position).getDescription());
        holder.rating.setText(rAdapterList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return rAdapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, description, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rec_img);
            name = itemView.findViewById(R.id.rec_name);
            description = itemView.findViewById(R.id.rec_desc);
            rating = itemView.findViewById(R.id.rec_rating);
        }
    }
}
