package com.zhenl.packer.support;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by lin on 2018/3/15.
 */

public class V2Utils {

    static byte[] getBytes(final ByteBuffer buf) {
        final byte[] array = buf.array();
        final int arrayOffset = buf.arrayOffset();
        return Arrays.copyOfRange(array, arrayOffset + buf.position(),
                arrayOffset + buf.limit());
    }

    static void close(final Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException ignored) {
        }
    }
}
