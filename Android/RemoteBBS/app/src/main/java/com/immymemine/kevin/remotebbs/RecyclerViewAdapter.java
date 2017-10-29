package com.immymemine.kevin.remotebbs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by quf93 on 2017-10-27.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder>{

    public RecyclerViewAdapter() {

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView titleItem, authorItem, dateItem;

        public Holder(View view) {
            super(view);

            titleItem = view.findViewById(R.id.title_item);
            authorItem = view.findViewById(R.id.author_item);
            dateItem = view.findViewById(R.id.date_item);
        }
    }
}
