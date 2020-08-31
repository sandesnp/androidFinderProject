package com.example.andoird_finderproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andoird_finderproject.R;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterRecycler extends RecyclerView.Adapter<adapterRecycler.RecycleViewHolder> {

    List<item> listItem;
    Context mContext;
    String FragmentType;

    public adapterRecycler(List<item> listItem, Context mContext, String FragmentType) {
        this.listItem = listItem;
        this.mContext = mContext;
        this.FragmentType = FragmentType;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (FragmentType) {
            case "home_fragment_first_segment":
                view = LayoutInflater.from(mContext).inflate(R.layout.home_first_segment, parent, false);
                break;
            case "home_fragment_second_segment":
                view = LayoutInflater.from(mContext).inflate(R.layout.home_second_segment, parent, false);
                break;
        }
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

        item item = listItem.get(position);
        holder.tv_segment_itemName.setText(item.getItemname());
        String imagePath = global.imagePath + item.getItempicture();
        Picasso.get().load(imagePath).into(holder.iv_segment_itemPicture);


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_segment_itemPicture;
        TextView tv_segment_itemName;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_segment_itemPicture = itemView.findViewById(R.id.imageView_segment_ItemPicture);

            tv_segment_itemName = itemView.findViewById(R.id.textView_segment_ItemName);
        }
    }
}
