package com.immymemine.kevin.pic_memo_orm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.immymemine.kevin.pic_memo_orm.Util.FileUtil;
import com.immymemine.kevin.pic_memo_orm.model.PicNote;

import java.io.IOException;
import java.util.List;

/**
 * Created by quf93 on 2017-09-22.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    List<PicNote> data = null;
    public void setData(List<PicNote> data) {
        this.data = data;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PicNote note = data.get(position);
        Bitmap bitmap = null;
        try {
            bitmap = FileUtil.bitmapReader(holder.context , note.getBitmap_path());
            Log.d("bitmap", "true");
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.setHolder(note.getTitle(), note.getPn_datetime(), bitmap);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView titleTV, datetimeTV;
        ImageView bitmapView;
        Context context;
        Intent intent;
        public Holder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            titleTV = (TextView)itemView.findViewById(R.id.titleTV);
            datetimeTV = (TextView)itemView.findViewById(R.id.datetimeTV);
            bitmapView = (ImageView) itemView.findViewById(R.id.bitmapView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(context, DetailActivity.class);
                    bundle = new Bundle();
                    bundle.putString("title", title);
                    System.out.println("타이틀 = " + title);
                    bundle.putString("datetime", datetime);
                    bundle.putByteArray("bitmap", FileUtil.bitmapToByteArray(bitmap));
                    intent.putExtra("picnote", bundle);
                    context.startActivity(intent);
                }
            });
        }
        Bundle bundle;
        String title, datetime;
        Bitmap bitmap;
        private void setHolder(String title, String datetime, Bitmap bitmap) {
            this.title = title; this.datetime = datetime; this.bitmap = bitmap;
            titleTV.setText(title); datetimeTV.setText(datetime); bitmapView.setImageBitmap(bitmap);
        }
    }
}
