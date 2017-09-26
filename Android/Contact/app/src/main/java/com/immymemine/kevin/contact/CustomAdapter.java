package com.immymemine.kevin.contact;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by quf93 on 2017-09-26.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    List<Contact> data;
    public void setData(List<Contact> data) {
        this.data = data;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Contact contact = data.get(position);
        holder.setContView(contact.getName() + "\n" + contact.getNumber());
        holder.setNumber(contact.getNumber());
        holder.setCallBtnOnClickListener();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView contView;
        Button callBtn;
        String number;

        public Holder(View itemView) {
            super(itemView);
            contView = (TextView)itemView.findViewById(R.id.contView);
            callBtn = (Button)itemView.findViewById(R.id.callBtn);
        }

        public void setContView(String cont) {
            contView.setText(cont);
        }
        public void setNumber(String number) {
            this.number = number;
        }

        public void setCallBtnOnClickListener() {
            callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    number = "tel:" + number;
                    // tel: < protocol / smsto: / http:// ...
                    Uri uri = Uri.parse(number);
                    Intent intent = new Intent("android.intent.action.CALL", uri);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }
}
