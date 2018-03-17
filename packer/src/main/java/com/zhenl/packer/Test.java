package com.zhenl.packer;

import java.io.File;

/**
 * Created by lin on 2018/3/15.
 */

public class Test {

    public static void main(String[] args) throws Exception{
        File file = new File("D:/Fxgo.apk");
        PackerCommon.writeChannel(file, "debug");
        System.out.println(PackerCommon.readChannel(file));
    }
}
