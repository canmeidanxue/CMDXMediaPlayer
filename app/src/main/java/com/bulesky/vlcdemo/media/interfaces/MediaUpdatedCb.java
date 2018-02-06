package com.bulesky.vlcdemo.media.interfaces;


import com.bulesky.vlcdemo.media.model.MediaWrapper;

public interface MediaUpdatedCb {
    void onMediaUpdated(MediaWrapper[] mediaList);
}
