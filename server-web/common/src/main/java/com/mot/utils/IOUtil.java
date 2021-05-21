package com.mot.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author tianfx
 * @date 2021/5/14 10:10 上午
 */
public class IOUtil {

    public static void closeable(Closeable closeable){
        try {
            if (closeable != null){
                closeable.close();
            }
        } catch (IOException e) {
            // do nothing
        }
    }
}
