package com.zhenl.packer.support;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by lin on 2018/3/15.
 */

public class PayloadReader {
    private PayloadReader() {
        super();
    }

    public static byte[] readBytes(final File apkFile, final int id)
            throws IOException {
        final ByteBuffer buf = readBlock(apkFile, id);
        return buf == null ? null : getBytes(buf);
    }

    public static ByteBuffer readBlock(final File apkFile, final int id)
            throws IOException {
        final Map<Integer, ByteBuffer> blocks = readAllBlocks(apkFile);
        if (blocks == null) {
            return null;
        }
        return blocks.get(id);
    }

    /**
     * get data from byteBuffer
     *
     * @param byteBuffer buffer
     * @return useful data
     */
    private static byte[] getBytes(final ByteBuffer byteBuffer) {
        final byte[] array = byteBuffer.array();
        final int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, arrayOffset + byteBuffer.position(),
                arrayOffset + byteBuffer.limit());
    }

    private static Map<Integer, ByteBuffer> readAllBlocks(final File apkFile)
            throws IOException {
        Map<Integer, ByteBuffer> idValues = null;
        RandomAccessFile randomAccessFile = null;
        FileChannel fileChannel = null;
        try {
            randomAccessFile = new RandomAccessFile(apkFile, "r");
            fileChannel = randomAccessFile.getChannel();
            final ByteBuffer apkSigningBlock2 = ApkUtil.findApkSigningBlock(fileChannel).getFirst();
            idValues = ApkUtil.findIdValues(apkSigningBlock2);
        } finally {
            try {
                if (fileChannel != null) {
                    fileChannel.close();
                }
            } catch (IOException ignore) {
            }
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException ignore) {
            }
        }

        return idValues;
    }
}
