package com.immymemine.kevin.subway.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.immymemine.kevin.subway.R;
import com.immymemine.kevin.subway.adapter.RecyclerViewAdapter;

/**
 * Created by quf93 on 2017-10-19.
 */

public class Subway_Fragment extends Fragment {

    private static final String LINE_NUM = "line_num";
    private String mLineNum = "";
    private OnListFragmentInteractionListener mListener;

    public Subway_Fragment() {
    }

    public static Subway_Fragment newInstance(String line_num) {
        Subway_Fragment fragment = new Subway_Fragment();
        Bundle args = new Bundle();
        args.putString(LINE_NUM, line_num);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mLineNum = getArguments().getString(LINE_NUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subway_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new RecyclerViewAdapter(mLineNum, mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void seeDetail(int position);
    }
}
