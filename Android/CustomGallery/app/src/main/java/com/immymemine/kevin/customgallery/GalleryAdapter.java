package com.immymemine.kevin.customgallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by quf93 on 2017-09-26.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Holder> {
    List<String> thumbnailData;
    Map<String, String> contentData;
    Activity activity;
    public GalleryAdapter(Activity activity) {
        this.activity = activity;
    }
    public void setData(List<String> thumbnailData, Map<String, String> contentData) {
        this.thumbnailData = thumbnailData; this.contentData = contentData;
        notifyDataSetChanged();
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new Holder(view);
    }
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 썸네일
//        Uri uri = Uri.parse(thumbnailData.get(position));
//        holder.setImage(uri);
        // 실 사진
        String[] paths = contentData.keySet().toArray(new String[contentData.size()]);
//        for(String s : paths)
//            System.out.println("paths = " + s);
        String content = paths[position];
        System.out.println(position);
        holder.setImage(Uri.parse(content));
        holder.setContent(Uri.parse(content));
        // 날짜 정보
        String datetime = contentData.get(content);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        datetime = sdf.format(new Date(Long.parseLong(datetime)));
        holder.setDate(datetime);
    }
    @Override
    public int getItemCount() {
        return contentData.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView dateView;
        Uri thumb; String date; Uri content;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.itemImageView);
            dateView = (TextView)itemView.findViewById(R.id.dateView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("ImagePath", content.getPath());
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();
                }
            });
        }
        public void setImage(Uri uri) {
            this.thumb = uri;
            imageView.setImageURI(thumb);
        }
        public void setDate(String date) {
            this.date = date;
            dateView.setText(date);
        }
        public void setContent(Uri content) {
            this.content = content;
        }
    }
}
