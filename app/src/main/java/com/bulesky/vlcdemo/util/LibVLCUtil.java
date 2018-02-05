package com.bulesky.vlcdemo.util;


import android.content.Context;

import org.videolan.libvlc.LibVLC;

import java.util.ArrayList;

public class LibVLCUtil {
    private static LibVLC libVLC = null;

    public synchronized static LibVLC getLibVLC(ArrayList<String> options, Context mContext) throws IllegalStateException {
        if (libVLC == null) {
            if (null != mContext) {
                if (options == null) {
                    libVLC = new LibVLC(mContext);
                } else {
                    libVLC = new LibVLC(mContext, options);
                }
            }
        }
        return libVLC;
    }
}