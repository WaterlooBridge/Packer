package com.zhenl.packer.support;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Created by lin on 2018/3/15.
 */

public class ApkSigningPayload {
    private final int id;
    private final ByteBuffer buffer;

    ApkSigningPayload(final int id, final ByteBuffer buffer) {
        super();
        this.id = id;
        if (buffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
        this.buffer = buffer;
    }

    public int getId() {
        return id;
    }

    public byte[] getByteBuffer() {
        final byte[] array = buffer.array();
        final int arrayOffset = buffer.arrayOffset();
        return Arrays.copyOfRange(array, arrayOffset + buffer.position(),
                arrayOffset + buffer.limit());
    }
}
