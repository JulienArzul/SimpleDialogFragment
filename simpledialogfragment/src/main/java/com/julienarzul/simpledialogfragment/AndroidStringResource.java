package com.julienarzul.simpledialogfragment;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.google.auto.value.AutoValue;

/**
 * Internal class used to store an Android String, either as an actual String or as a resource identifier.
 * <p>
 * Copyright @ Julien Arzul 2017
 */
@AutoValue
abstract class AndroidStringResource implements Parcelable {

    static AndroidStringResource create(@StringRes int stringResId) {
        return new AutoValue_AndroidStringResource(null, stringResId);
    }

    static AndroidStringResource create(String string) {
        return new AutoValue_AndroidStringResource(string, 0);
    }

    static String valueOf(AndroidStringResource androidStringResource, Context context) {
        if (androidStringResource == null) {
            return null;
        }

        return androidStringResource.value(context);
    }

    @Nullable
    abstract String string();

    @StringRes
    abstract int resId();

    String value(Context context) {
        String result = string();

        if (result == null) {
            int resId = resId();
            if (resId != 0) {
                result = context.getString(resId);
            }
        }

        return result;
    }
}
