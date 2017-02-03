package com.julienarzul.simpledialogfragment;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by Julien Arzul on 13/12/2016.
 * Copyright @ Julien Arzul 2016
 */
@AutoValue
public abstract class SimpleDialogContent implements Parcelable {

    public static Builder builder() {
        return new AutoValue_SimpleDialogContent.Builder()
                .setCancelable(true);
    }

    @Nullable
    public abstract String title();

    public abstract String message();

    @Nullable
    public abstract String positiveButtonText();

    @Nullable
    public abstract String negativeButtonText();

    @Nullable
    public abstract String neutralButtonText();

    @Nullable
    public abstract Integer requestCode();

    public abstract boolean cancelable();

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setTitle(String title);

        public abstract Builder setMessage(String message);

        public abstract Builder setPositiveButtonText(String positiveButtonText);

        public abstract Builder setNegativeButtonText(String negativeButtonText);

        public abstract Builder setNeutralButtonText(String neutralButtonText);

        public abstract Builder setRequestCode(Integer requestCode);

        public abstract Builder setCancelable(boolean cancelable);

        public abstract SimpleDialogContent build();
    }
}
