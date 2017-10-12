package com.immymemine.kevin.musicplayer.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SongContent {
    public static final List<SongItem> ITEMS = new ArrayList<>();

    public SongContent() {
    }
    private void addItem(SongItem item) {
        ITEMS.add(item);
    }

    public void setList(Context context) {
        ContentResolver resolver = context.getContentResolver();
        // get uri
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] proj = { MediaStore.Audio.Media._ID, MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DATE_ADDED
        };
        Cursor cursor = resolver.query(uri, proj, null, null, proj[5] + " ASC");
        if(ITEMS != null)
            ITEMS.clear();
        while(cursor.moveToNext()) {
            SongItem songItem = new SongItem(
                getValue(cursor, proj[0]), getValue(cursor, proj[1]), getValue(cursor, proj[2]),
                    getValue(cursor, proj[3]), getValue(cursor, proj[4]), getValue(cursor, proj[5])
            );
            songItem.setMusicUri();
            songItem.setAlbumUri();
            addItem(songItem);
        }
        cursor.close();
    }
    private String getValue(Cursor cursor, String name) {
        int index = cursor.getColumnIndex(name);
        return cursor.getString(index);
    }

    public  static class SongItem {
        public String songId, albumId, title, artist, duration, date_added;
        public Uri musicUri, albumUri;
        public SongItem(String songId, String albumId, String title, String artist, String duration, String date_added) {
            this.songId = songId; this.albumId = albumId; this.title =title; this.artist = artist;
            this.duration = duration; this.date_added = date_added;
        }

        public void setMusicUri() {
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            musicUri = Uri.withAppendedPath(uri, songId);
        }

        public void setAlbumUri() {
            String contentUri = "content://media/external/audio/albumart/";
            albumUri = Uri.parse(contentUri + albumId);
        }
    }
}
