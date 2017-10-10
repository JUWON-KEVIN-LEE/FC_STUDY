package com.immymemine.kevin.melon.viewpager_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.immymemine.kevin.melon.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class music extends Fragment {

    public music() {
        // Required empty public constructor
    }
    Button playBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        // Inflate the layout for this fragment
        return view;
    }

}
