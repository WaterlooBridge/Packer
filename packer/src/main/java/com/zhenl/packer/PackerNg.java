package com.zhenl.packer;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.io.File;
import java.io.IOException;

/**
 * Created by lin on 2018/3/17.
 */

public final class PackerNg {

    private static final String EMPTY_STRING = "";

    public static String getChannel(final File file) {
        try {
            return PackerCommon.readChannel(file);
        } catch (Exception e) {
            return EMPTY_STRING;
        }
    }

    public static String getChannel(final Context context) {
        try {
            return getChannelOrThrow(context);
        } catch (Exception e) {
            return EMPTY_STRING;
        }
    }

    public static synchronized String getChannelOrThrow(final Context context)
            throws IOException {
        final ApplicationInfo info = context.getApplicationInfo();
        return PackerCommon.readChannel(new File(info.sourceDir));
    }
}
