package com.immymemine.kevin.musicplayer.player;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.immymemine.kevin.musicplayer.R;
import com.immymemine.kevin.musicplayer.model.SongContent;
import com.immymemine.kevin.musicplayer.utilities.CircleImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by quf93 on 2017-10-11.
 */

public class PlayerViewPagerAdapter extends PagerAdapter {
    Context context;
    List<SongContent.SongItem> data;
    public PlayerViewPagerAdapter(Context context, List<SongContent.SongItem> data) {
        this.context = context; this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.player_item, null);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(data.get(position).title);

        TextView tvArtist = (TextView) view.findViewById(R.id.tv_artist);
        tvArtist.setText(data.get(position).artist);

        CircleImageView civAlbum = (CircleImageView) view.findViewById(R.id.civ_album_player);
        civAlbum.setImageURI(data.get(position).albumUri);
        // ivAlbumArt.setImageURI(data.get(position).albumUri);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
