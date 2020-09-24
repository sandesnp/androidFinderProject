package com.example.andoird_finderproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.andoird_finderproject.InterfaceAPI.shopAPI;
import com.example.andoird_finderproject.R;
import com.example.andoird_finderproject.adapters.adapterRecycler;
import com.example.andoird_finderproject.global.global;
import com.example.andoird_finderproject.httpRequests.requestItem;
import com.example.andoird_finderproject.models.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class Fragment_Home extends Fragment {

    List<item> listItem;
    RecyclerView recyclerView_first_segment, recyclerView_second_segment, recyclerView_search;
    CircleImageView cv_goLeft, cv_goRight;
    AutoCompleteTextView autoCompleteTextView_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__home, container, false);

        recyclerView_first_segment = view.findViewById(R.id.recyclerView_firstSegment);
        recyclerView_second_segment = view.findViewById(R.id.recyclerView_secondSegment);
        recyclerView_search = view.findViewById(R.id.recycleView_search);
        autoCompleteTextView_search = view.findViewById(R.id.autoComplete_search);
        cv_goLeft = view.findViewById(R.id.cv_goLeft);
        cv_goRight = view.findViewById(R.id.cv_goRight);
        cv_goLeft.setPadding(-80, -50, -80, -50);
        cv_goRight.setPadding(-80, -50, -80, -50);
        listItem = new requestItem(null).fetchItems();
        List<item> listItem_FirstSegment = new ArrayList<>();
        List<item> listItem_SecondSegment = new ArrayList<>();
        for (int i = 0; i < listItem.size(); i++) {
            if (i < listItem.size() / 2) {
                listItem_FirstSegment.add(listItem.get(i));
            } else {
                listItem_SecondSegment.add(listItem.get(i));
            }
        }
        //first segment
        final adapterRecycler adapter_Recycler = new adapterRecycler(listItem_FirstSegment, getContext(), "home_fragment_first_segment", getParentFragmentManager());
        recyclerView_first_segment.setAdapter(adapter_Recycler);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_first_segment.setLayoutManager(linearLayoutManager);
        //Carousal Left and Right clickable
        cv_goLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0) {
                    recyclerView_first_segment.smoothScrollToPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition() - 1);
                }
            }
        });
        cv_goRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView_first_segment.smoothScrollToPosition(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
            }
        });

        //second segment
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 3 == 0) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView_second_segment.setAdapter(new adapterRecycler(listItem_SecondSegment, getContext(), "home_fragment_second_segment", getParentFragmentManager()));
        recyclerView_second_segment.setHorizontalScrollBarEnabled(true);
        recyclerView_second_segment.setLayoutManager(gridLayoutManager);

        autoComplete_Search();
        return view;
    }

    private Map<String, String> MenuItems;

    public void autoComplete_Search() {
        MenuItems = new HashMap<>();
        for (item item : listItem) {
            MenuItems.put(item.getItemname(), item.get_id());
        }
        final String[] item_info = {"", ""}; //setting size
        ArrayAdapter arrayAdapterSearch = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, new ArrayList(MenuItems.keySet()));
        autoCompleteTextView_search.setAdapter(arrayAdapterSearch);
        autoCompleteTextView_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item_info[0] = adapterView.getItemAtPosition(i).toString();
                item_info[1] = MenuItems.get(item_info[0]); //Extracting id with respect to selected shop
                List<item> SearchItemlist = new ArrayList<>();
                for (item item : listItem) {
                    if (item_info[1].equals(item.get_id())) {
                        SearchItemlist.add(item);
                    }
                }
                adapterRecycler adapter_Recycler = new adapterRecycler(SearchItemlist, getContext(), "home_fragment_second_segment", getParentFragmentManager());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView_search.setAdapter(adapter_Recycler);
                recyclerView_search.setLayoutManager(linearLayoutManager);
            }
        });
    }
}