package com.example.andoird_finderproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andoird_finderproject.MainActivity;
import com.example.andoird_finderproject.R;
import com.example.andoird_finderproject.fragments.Fragment_Home;
import com.example.andoird_finderproject.fragments.Inner_Fragment.fragmentItem;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.models.item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterRecycler extends RecyclerView.Adapter<adapterRecycler.RecycleViewHolder> {

    List<item> listItem;
    Context mContext;
    String FragmentType;
    FragmentManager fm;

    public adapterRecycler(List<item> listItem, Context mContext, String FragmentType, FragmentManager fm) {
        this.listItem = listItem;
        this.mContext = mContext;
        this.FragmentType = FragmentType;
        this.fm = fm;
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
            case "popular_fragment":
                view = LayoutInflater.from(mContext).inflate(R.layout.popular_item, parent, false);
                break;
        }
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

        final item item = listItem.get(position);
        holder.tv_segment_itemName.setText(item.getItemname());
        String imagePath = global.imagePath + item.getItempicture();
        Picasso.get().load(imagePath).into(holder.iv_segment_itemPicture);

        holder.iv_segment_itemPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentItem fragmentItem = new fragmentItem();

                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                fm.setFragmentResult("withObject_Item", bundle);
                fm.beginTransaction().replace(R.id.fragment_container, fragmentItem).
                        setCustomAnimations(R.anim.enter_from_up, R.anim.exit_from_up).
                        addToBackStack(null).detach(fragmentItem).attach(fragmentItem).
                        commit();
            }
        });


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
