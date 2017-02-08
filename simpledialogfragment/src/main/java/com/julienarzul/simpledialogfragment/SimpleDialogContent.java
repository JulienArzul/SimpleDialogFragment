package com.julienarzul.simpledialogfragment;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.google.auto.value.AutoValue;

/**
 * Immutable value class that represents the content of an AlertDialog shown with {@link SimpleDialogFragment}.
 * The class is Parcelable in order to be given to the DialogFragment as a bundle argument.
 * <p>
 * Create an instance of this class with a {@link SimpleDialogContent.Builder}, retrieved through the static method
 * {@link #builder()}.
 * <p>
 * The only required @NotNull field is the message of the dialog.
 * <p>
 * Copyright @ Julien Arzul 2016
 */
@AutoValue
public abstract class SimpleDialogContent implements Parcelable {

    public static Builder builder() {
        return new AutoValue_SimpleDialogContent.Builder()
                .setCancelable(true);
    }

    @Nullable
    abstract AndroidStringResource title();

    abstract AndroidStringResource message();

    @Nullable
    abstract AndroidStringResource positiveButtonText();

    @Nullable
    abstract AndroidStringResource negativeButtonText();

    @Nullable
    abstract AndroidStringResource neutralButtonText();

    @Nullable
    public abstract Integer requestCode();

    public abstract boolean cancelable();

    public String title(Context context) {
        return AndroidStringResource.valueOf(this.title(), context);
    }

    public String message(Context context) {
        return AndroidStringResource.valueOf(this.message(), context);
    }

    public String positiveButtonText(Context context) {
        return AndroidStringResource.valueOf(this.positiveButtonText(), context);
    }

    public String negativeButtonText(Context context) {
        return AndroidStringResource.valueOf(this.negativeButtonText(), context);
    }

    public String neutralButtonText(Context context) {
        return AndroidStringResource.valueOf(this.neutralButtonText(), context);
    }

    @AutoValue.Builder
    public abstract static class Builder {

        abstract Builder setTitle(AndroidStringResource title);

        abstract Builder setMessage(AndroidStringResource message);

        abstract Builder setPositiveButtonText(AndroidStringResource positiveButtonText);

        abstract Builder setNegativeButtonText(AndroidStringResource negativeButtonText);

        abstract Builder setNeutralButtonText(AndroidStringResource neutralButtonText);

        public abstract Builder setRequestCode(Integer requestCode);

        public abstract Builder setCancelable(boolean cancelable);

        public abstract SimpleDialogContent build();

        public Builder setTitle(String title) {
            return this.setTitle(AndroidStringResource.create(title));
        }

        public Builder setTitle(@StringRes int title) {
            return this.setTitle(AndroidStringResource.create(title));
        }

        public Builder setMessage(String message) {
            return this.setMessage(AndroidStringResource.create(message));
        }

        public Builder setMessage(@StringRes int message) {
            return this.setMessage(AndroidStringResource.create(message));
        }

        public Builder setPositiveButtonText(String positiveButtonText) {
            return this.setPositiveButtonText(AndroidStringResource.create(positiveButtonText));
        }

        public Builder setPositiveButtonText(@StringRes int positiveButtonText) {
            return this.setPositiveButtonText(AndroidStringResource.create(positiveButtonText));
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            return this.setNegativeButtonText(AndroidStringResource.create(negativeButtonText));
        }

        public Builder setNegativeButtonText(@StringRes int negativeButtonText) {
            return this.setNegativeButtonText(AndroidStringResource.create(negativeButtonText));
        }

        public Builder setNeutralButtonText(String neutralButtonText) {
            return this.setNeutralButtonText(AndroidStringResource.create(neutralButtonText));
        }

        public Builder setNeutralButtonText(@StringRes int neutralButtonText) {
            return this.setNeutralButtonText(AndroidStringResource.create(neutralButtonText));
        }
    }
}
