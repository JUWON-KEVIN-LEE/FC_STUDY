package com.immymemine.kevin.androidmemoorm;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.immymemine.kevin.androidmemoorm.FileUtil.FileUtil;
import com.immymemine.kevin.androidmemoorm.model.PicNote;

import java.io.IOException;
import java.util.List;

import static com.immymemine.kevin.androidmemoorm.R.id.bmView;

/**
 * Created by quf93 on 2017-09-22.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    List<PicNote> data = null;
    Context context;
    public CustomAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<PicNote> data) {
        this.data = data;
    }
    @Override
    public int getItemCount() { // 목록의 전체 길이를 결정
        return data.size();
    }
    // 최초에는 create 와 bind 둘 다 호출
    // 이후부터는 bind 만...
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }
    // 생성된 View Holder 를 Recycler View 에 붙여놓고 재사용해서 그리게 한다.
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PicNote picNote = data.get(position);
        try {
            holder.setBitmapView(FileUtil.read(context,picNote.getBitmap_path()));
        } catch (IOException e) {
            Toast.makeText(context, "Error = "+e.toString(), Toast.LENGTH_SHORT);
        } catch (NullPointerException e) {

        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView bmView;
        public Holder(View itemView) {
            super(itemView);
            bmView = (ImageView) itemView.findViewById(bmView);
        }
        private void setBitmapView(Bitmap bitmap) {
            bmView.set
        }
    }
}
