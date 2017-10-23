package com.immymemine.kevin.subway.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.immymemine.kevin.subway.MainActivity;
import com.immymemine.kevin.subway.R;
import com.immymemine.kevin.subway.fragment.Subway_Fragment;
import com.immymemine.kevin.subway.model.station_info.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quf93 on 2017-10-19.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
    // static data 가져오기
    String line;
    Subway_Fragment.OnListFragmentInteractionListener mListener;
    List<Row> adpater_data = new ArrayList<>();
    public RecyclerViewAdapter(String line, Subway_Fragment.OnListFragmentInteractionListener mListener) {
        this.line = line; this.mListener = mListener;

        if("search".equals(line)) {
            adpater_data = MainActivity.searchList;
        } else {
            for (int i = 0; i < MainActivity.data.getRow().length; i++) {
                if (MainActivity.data.getRow()[i].getLINE_NUM().equals(line)) {
                    adpater_data.add(MainActivity.data.getRow()[i]);
                }
            }
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subway_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.position = position;
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return adpater_data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivNum1, ivNum2, ivNum3, ivNum4;
        ImageButton departure, passBy, arrival;
        int position;

        public Holder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            ivNum1 = (ImageView) itemView.findViewById(R.id.iv_num1);
            ivNum2 = (ImageView) itemView.findViewById(R.id.iv_num2);
            ivNum3 = (ImageView) itemView.findViewById(R.id.iv_num3);
            ivNum4 = (ImageView) itemView.findViewById(R.id.iv_num4);
            departure = (ImageButton) itemView.findViewById(R.id.departure);
            passBy = (ImageButton) itemView.findViewById(R.id.pass_by);
            arrival = (ImageButton) itemView.findViewById(R.id.arrival);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.seeDetail(position);
                }
            });
        }

        public void bind(int position) {
            tvName.setText( adpater_data.get(position).getSTATION_NM() );
            
        }
    }
}
