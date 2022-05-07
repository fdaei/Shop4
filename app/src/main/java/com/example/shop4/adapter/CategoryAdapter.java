package com.example.shop4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.shop4.R;
import com.example.shop4.model.Banner;
import com.example.shop4.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{
    List<Category> data;
    Context context;
    public CategoryAdapter(Context context, List<Category> data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_category.setText(data.get(position).getTitle());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.img_category);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img_category;
        TextView name_category;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            img_category = itemView.findViewById(R.id.img_category);
            name_category = itemView.findViewById(R.id.title);
        }

    }
}
