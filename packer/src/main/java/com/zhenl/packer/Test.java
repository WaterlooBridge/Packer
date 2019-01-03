package com.zhenl.packer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by lin on 2018/3/15.
 */

public class Test {

    public static void main(String[] args) throws Exception {
        try {
            String srcFilePath = args[0];
            File srcFile = new File(srcFilePath);
            if (args.length > 1) {
                String channelFilePath = args[1];
                File channelFile = new File(channelFilePath);
                FileInputStream channelStream = new FileInputStream(channelFile);
                byte[] channelBytes = new byte[channelStream.available()];
                channelStream.read(channelBytes);
                String[] channels = new String(channelBytes).split("\\|");
                String outDir = channelFile.getParentFile().getAbsolutePath();
                if (args.length >= 3) {
                    outDir = args[2];
                    File dir = new File(outDir);
                    if (!dir.exists())
                        dir.mkdirs();
                }
                String name = srcFile.getName();
                name = name.substring(0, name.length() - 4);
                for (String channel : channels) {
                    File file = new File(outDir, name + "_" + channel + ".apk");
                    FileInputStream in = new FileInputStream(srcFile);
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] b = new byte[1024];
                    int n = 0;
                    while ((n = in.read(b)) != -1) {
                        out.write(b, 0, n);
                    }
                    in.close();
                    out.close();
                    PackerCommon.writeChannel(file, channel);
                    System.out.println(PackerCommon.readChannel(file));
                }
            } else {
                System.out.println(PackerCommon.readChannel(srcFile));
            }
        } catch (Exception e) {
            System.out.println("Usage");
            System.out.println("java -jar packer.jar D:/Fxgo.apk(必填) D:/channel.txt(必填) D:/outputDir(可选)");
            e.printStackTrace();
        }
    }
}
