package com.example.andoird_finderproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andoird_finderproject.R;
import com.example.andoird_finderproject.adapters.adapterRecycler;
import com.example.andoird_finderproject.httpRequests.requestItem;
import com.example.andoird_finderproject.models.item;

import java.util.List;


public class Fragment_Popular extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__popular, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycleView_popularItem);

        List<item> itemList = new requestItem(null).fetchItems();
        adapterRecycler adapterRecycler = new adapterRecycler(itemList, getActivity(), "popular_fragment", getParentFragmentManager());
        recyclerView.setAdapter(adapterRecycler);

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        recyclerView.setLayoutManager(manager);


        return view;
    }
}