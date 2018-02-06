package com.bulesky.vlcdemo.media.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.bulesky.vlcdemo.R;
import com.bulesky.vlcdemo.media.Medialibrary;

import org.videolan.libvlc.util.VLCUtil;

public class Album extends MediaLibraryItem {
    private static String UNKNOWN_ALBUM;

    private int releaseYear;
    private String artworkMrl;
    private String albumArtist;
    private long albumArtistId;
    private int mTracksCount;
    private int duration;

    public Album(long id, String title, int releaseYear, String artworkMrl, String albumArtist, long albumArtistId, int nbTracks, int duration) {
        super(id, title);
        this.releaseYear = releaseYear;
        this.artworkMrl = artworkMrl != null ? VLCUtil.UriFromMrl(artworkMrl).getPath() : null;
        this.albumArtist = albumArtist != null ? albumArtist.trim() : null;
        this.albumArtistId = albumArtistId;
        this.mTracksCount = nbTracks;
        this.duration = duration;
        if (TextUtils.isEmpty(title)) {
            if (UNKNOWN_ALBUM == null) {
                UNKNOWN_ALBUM = Medialibrary.getContext().getString(R.string.unknown_album);
            }
            mTitle = UNKNOWN_ALBUM;
        }
        if (TextUtils.isEmpty(albumArtist)) {
            if (Artist.UNKNOWN_ARTIST == null) {
                Artist.UNKNOWN_ARTIST = Medialibrary.getContext().getString(R.string.unknown_artist);
            }
            this.albumArtist = Artist.UNKNOWN_ARTIST;
        }
    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public String getDescription() {
        return albumArtist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String getArtworkMrl() {
        return artworkMrl;
    }

    public Artist getAlbumArtist() {
        //TODO
        return null;
    }

    public int getTracksCount() {
        return mTracksCount;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public MediaWrapper[] getTracks() {
        Medialibrary ml = Medialibrary.getInstance();
        return ml != null && ml.isInitiated() ? nativeGetTracksFromAlbum(ml, mId) : Medialibrary.EMPTY_COLLECTION;
    }

    @Override
    public int getItemType() {
        return TYPE_ALBUM;
    }

    private native MediaWrapper[] nativeGetTracksFromAlbum(Medialibrary ml, long mId);

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(releaseYear);
        parcel.writeString(artworkMrl);
        parcel.writeString(albumArtist);
        parcel.writeLong(albumArtistId);
        parcel.writeInt(mTracksCount);
        parcel.writeInt(duration);
    }

    public static Parcelable.Creator<Album> CREATOR
            = new Parcelable.Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    private Album(Parcel in) {
        super(in);
        this.releaseYear = in.readInt();
        this.artworkMrl = in.readString();
        this.albumArtist = in.readString();
        this.albumArtistId = in.readLong();
        this.mTracksCount = in.readInt();
        this.duration = in.readInt();
    }
}
