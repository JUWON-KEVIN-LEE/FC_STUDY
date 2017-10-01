package com.immymemine.kevin.fragmentbasic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by quf93 on 2017-09-27.
 */

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.Holder> {
    Context context;
    List<String> data;
    public CustomAdapter(Context context, List<String> data) {
        this.context = context; this.data = data;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setItemTV(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        Call call = (Call)context;
        TextView itemTV;
        public Holder(View itemView) {
            super(itemView);
            itemTV = (TextView)itemView.findViewById(R.id.itemTV);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call.goDetail();
                }
            });
        }

        public void setItemTV(String text) {
            itemTV.setText(text);
        }
    }

    public interface Call {
        public void goDetail();
    }
}

