package com.immymemine.kevin.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) { // < 이 Context 가 삽입한 Activity
        if(context instanceof CallBack) {
            callBack = (CallBack)context;
        } else {

        }

        super.onAttach(context);
    }
    // 액티비티에 부착되며 동작한다
    Button goDetailBtn;
    CallBack callBack = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // fragment 화면을 세팅하는 로직...
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        goDetailBtn = (Button)view.findViewById(R.id.goDetailBtn);
        goDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.goDetail();
            }
        });

        return view;
    }

    public interface CallBack {
        public void goDetail();
    }

    @Override
    public void onStart() {
        Log.d("Frag", "__________________start__________________");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("Frag", "__________________resume__________________");
        super.onResume();
    }
}
