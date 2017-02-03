package com.julienarzul.simpledialogfragment;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.auto.value.AutoValue;

/**
 * Created by Julien Arzul on 13/12/2016.
 * Copyright @ ZenPark 2016
 */
@AutoValue
public abstract class SimpleDialogContent implements Parcelable {

    public static SimpleDialogContent create(String title, String message) {
        return create(title, message, null, null, null);
    }

    public static SimpleDialogContent create(String title, String message, String confirmButtonText, String negativeButtonText, Integer requestCode) {
        return create(title, message, confirmButtonText, negativeButtonText, requestCode, true);
    }

    public static SimpleDialogContent create(String title, String message, String confirmButtonText, String negativeButtonText, Integer requestCode, boolean cancelable) {
        if (TextUtils.isEmpty(message)) {
            return null;
        }

        return new AutoValue_SimpleDialogContent(title, message, confirmButtonText, negativeButtonText, requestCode, cancelable);
    }

    @Nullable
    public abstract String title();

    public abstract String message();

    @Nullable
    public abstract String confirmButtonText();

    @Nullable
    public abstract String negativeButtonText();

    @Nullable
    public abstract Integer requestCode();

    public abstract boolean cancelable();
}
