package com.bulesky.vlcdemo.media.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bulesky.vlcdemo.media.Medialibrary;


public class Genre extends MediaLibraryItem {

    public Genre(long id, String title) {
        super(id, title);
    }

    public Album[] getAlbums() {
       Medialibrary ml = Medialibrary.getInstance();
       return ml != null && ml.isInitiated() ? nativeGetAlbumsFromGenre(ml, mId) : new Album[0];
    }

    public Artist[] getArtists() {
        Medialibrary ml = Medialibrary.getInstance();
        return ml != null && ml.isInitiated() ? nativeGetArtistsFromGenre(ml, mId) : new Artist[0];
    }

    @Override
    public MediaWrapper[] getTracks() {
        Medialibrary ml = Medialibrary.getInstance();
        return ml != null && ml.isInitiated() ? nativeGetTracksFromGenre(ml, mId) : Medialibrary.EMPTY_COLLECTION;
    }

    @Override
    public int getItemType() {
        return TYPE_GENRE;
    }

    private native Album[] nativeGetAlbumsFromGenre(Medialibrary ml, long mId);
    private native Artist[] nativeGetArtistsFromGenre(Medialibrary ml, long mId);
    private native MediaWrapper[] nativeGetTracksFromGenre(Medialibrary ml, long mId);

    public static Parcelable.Creator<Genre> CREATOR
            = new Parcelable.Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };
    public Genre(Parcel in) {
        super(in);
    }
}
