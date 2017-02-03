package com.julienarzul.simpledialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * Created by Julien Arzul on 09/11/2016.
 * Copyright @ Julien Arzul 2016
 */
public class SimpleDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public static final String TAG = "SimpleDialogFragment";

    private static final Integer DEFAULT_REQUEST_CODE = 0;

    private static final String DIALOG_CONTENT_BUNDLE_KEY = "com.julienarzul.simpledialogfragment.SimpleDialogFragment.dialogContent";

    private SimpleDialogContent dialogContent = null;

    public static SimpleDialogFragment newInstance(SimpleDialogContent dialogContent) {
        Bundle args = new Bundle();

        args.putParcelable(DIALOG_CONTENT_BUNDLE_KEY, dialogContent);

        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            this.dialogContent = arguments.getParcelable(DIALOG_CONTENT_BUNDLE_KEY);

            if (this.dialogContent != null) {
                this.setCancelable(this.dialogContent.cancelable());
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        String title = null, message = null, positiveButtonText = null, negativeButtonText = null, neutralButtonText = null;
        if (this.dialogContent != null) {
            title = this.dialogContent.title();
            message = this.dialogContent.message();
            positiveButtonText = this.dialogContent.positiveButtonText();
            negativeButtonText = this.dialogContent.negativeButtonText();
            neutralButtonText = this.dialogContent.neutralButtonText();
        }

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        if (TextUtils.isEmpty(positiveButtonText)) {
            positiveButtonText = getString(android.R.string.ok);
        }
        builder.setPositiveButton(positiveButtonText, this);
        if (!TextUtils.isEmpty(negativeButtonText)) {
            builder.setNegativeButton(negativeButtonText, this);
        }
        if (!TextUtils.isEmpty(neutralButtonText)) {
            builder.setNeutralButton(neutralButtonText, this);
        }

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                Pair<OnPositiveButtonClickListener, Integer> positiveButtonListenerPair = this.getListener(OnPositiveButtonClickListener.class);
                if (positiveButtonListenerPair != null) {
                    positiveButtonListenerPair.first.onDialogPositiveButtonClicked(dialog, positiveButtonListenerPair.second);
                }
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                Pair<OnNegativeButtonClickListener, Integer> negativeListenerPair = this.getListener(OnNegativeButtonClickListener.class);
                if (negativeListenerPair != null) {
                    negativeListenerPair.first.onDialogNegativeButtonClicked(dialog, negativeListenerPair.second);
                }
                break;

            case DialogInterface.BUTTON_NEUTRAL:
                Pair<OnNeutralButtonClickListener, Integer> neutralListenerPair = this.getListener(OnNeutralButtonClickListener.class);
                if (neutralListenerPair != null) {
                    neutralListenerPair.first.onDialogNeutralButtonClicked(dialog, neutralListenerPair.second);
                }
                break;
        }
    }

    private <T> Pair<T, Integer> getListener(Class<T> listenerClazz) {
        Fragment targetFragment = this.getTargetFragment();
        if (listenerClazz.isInstance(targetFragment)) {
            return new Pair<>((T) targetFragment, this.getTargetRequestCode());
        }

        FragmentActivity activity = this.getActivity();
        if (listenerClazz.isInstance(activity)) {
            Integer requestCode = DEFAULT_REQUEST_CODE;
            if (this.dialogContent != null) {
                requestCode = this.dialogContent.requestCode();
            }
            return new Pair<>((T) activity, requestCode);
        }

        return null;
    }

    public interface OnPositiveButtonClickListener {

        void onDialogPositiveButtonClicked(DialogInterface dialog, Integer requestCode);
    }

    public interface OnNegativeButtonClickListener {

        void onDialogNegativeButtonClicked(DialogInterface dialog, Integer requestCode);
    }

    public interface OnNeutralButtonClickListener {

        void onDialogNeutralButtonClicked(DialogInterface dialog, Integer requestCode);
    }
}
