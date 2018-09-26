package com.allenanker.utils;

import java.util.UUID;

public class FileUploadUtils {

    public static String getUUIDFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        String extension = fileName.substring(index);
        return UUID.randomUUID().toString().replace("-", "") + extension;
    }
}
