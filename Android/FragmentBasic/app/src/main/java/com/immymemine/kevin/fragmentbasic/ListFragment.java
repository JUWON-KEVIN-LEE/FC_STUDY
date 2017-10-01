package com.immymemine.kevin.fragmentbasic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        init(view);
        return view;
    }

    RecyclerView recyclerView;
    CustomAdapter adapter;
    private void init(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        List<String> data = new ArrayList<>();
        for(int i=1; i<101; i++)
            data.add("tempData " + i);
        adapter = new CustomAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
