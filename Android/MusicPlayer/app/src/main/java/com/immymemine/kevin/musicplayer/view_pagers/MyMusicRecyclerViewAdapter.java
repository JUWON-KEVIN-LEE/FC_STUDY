package com.immymemine.kevin.musicplayer.view_pagers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.immymemine.kevin.musicplayer.R;
import com.immymemine.kevin.musicplayer.view_pagers.MusicFragment.OnListFragmentInteractionListener;
import com.immymemine.kevin.musicplayer.model.SongContent.SongItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link SongItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMusicRecyclerViewAdapter extends RecyclerView.Adapter<MyMusicRecyclerViewAdapter.ViewHolder> {

    private final List<SongItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMusicRecyclerViewAdapter(List<SongItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
//        holder.mAlbumView.setImageResource(R.drawable.album);
        holder.mAlbumView.setImageURI(holder.mItem.albumUri);
        holder.mTitleView.setText(holder.mItem.title);
        holder.mArtistView.setText(holder.mItem.artist);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageView mAlbumView;
        TextView mTitleView;
        TextView mArtistView;
        SongItem mItem;
        int position;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAlbumView = (ImageView) view.findViewById(R.id.iv_album);
            mTitleView = (TextView) view.findViewById(R.id.tv_title);
            mArtistView = (TextView) view.findViewById(R.id.tv_artist);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.playMusicPlayer(position);
                    }
                }
            });
        }

    }
}
