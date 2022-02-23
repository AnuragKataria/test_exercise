package com.obvious.test_exercise.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ResourceHelper {
    @Nullable
    public static String loadString(@NonNull String name) {
        return loadString(ResourceHelper.class.getClassLoader(), name);
    }

    @Nullable
    public static String loadString(@Nullable ClassLoader loader, @NonNull String name) {
        if (loader == null) {
            return null;
        }

        try {
            try (final InputStream inputStream = loader.getResourceAsStream(name)) {
                return loadString(inputStream);
            }
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

    @Nullable
    public static String loadString(@Nullable InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }

        try {
            try (final ByteArrayOutputStream result = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[4096];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    result.write(buffer, 0, length);
                }
                return result.toString("UTF-8");
            }
        } catch (IOException e) {
            return null;
        }
    }
}