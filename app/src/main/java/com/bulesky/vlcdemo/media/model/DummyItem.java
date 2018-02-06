package com.bulesky.vlcdemo.media.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.bulesky.vlcdemo.media.Medialibrary;


public class DummyItem extends MediaLibraryItem {

    public DummyItem(String title) {
        super(0, title);
    }

    @Override
    public MediaWrapper[] getTracks() {
        return Medialibrary.EMPTY_COLLECTION;
    }

    @Override
    public int getItemType() {
        return TYPE_DUMMY;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public static Parcelable.Creator<DummyItem> CREATOR
            = new Parcelable.Creator<DummyItem>() {
        @Override
        public DummyItem createFromParcel(Parcel in) {
            return new DummyItem(in);
        }

        @Override
        public DummyItem[] newArray(int size) {
            return new DummyItem[size];
        }
    };

    public DummyItem(Parcel in) {
        super(in);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DummyItem && TextUtils.equals(mTitle, ((DummyItem) obj).getTitle());
    }
}
