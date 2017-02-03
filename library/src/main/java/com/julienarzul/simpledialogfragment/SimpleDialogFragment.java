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

        String title = null, message = null, positiveButtonText = null, negativeButtonText = null;
        boolean cancelable = true;
        if (this.dialogContent != null) {
            title = this.dialogContent.title();
            message = this.dialogContent.message();
            positiveButtonText = this.dialogContent.positiveButtonText();
            negativeButtonText = this.dialogContent.negativeButtonText();
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

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Pair<SimpleDialogFragmentListener, Integer> listenerPair = this.getListener();

        if (listenerPair != null) {
            SimpleDialogFragmentListener listener = listenerPair.first;
            Integer requestCode = listenerPair.second;
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    listener.onPositiveButtonClicked(dialog, requestCode);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    listener.onNegativeButtonClicked(dialog, requestCode);
                    break;
            }
        } else {
            dialog.dismiss();
        }
    }

    private Pair<SimpleDialogFragmentListener, Integer> getListener() {
        Fragment targetFragment = this.getTargetFragment();
        if (targetFragment instanceof SimpleDialogFragmentListener) {
            return new Pair<>((SimpleDialogFragmentListener) targetFragment, this.getTargetRequestCode());
        }

        FragmentActivity activity = this.getActivity();
        if (activity instanceof SimpleDialogFragmentListener) {
            Integer requestCode = DEFAULT_REQUEST_CODE;
            if (this.dialogContent != null) {
                requestCode = this.dialogContent.requestCode();
            }
            return new Pair<>((SimpleDialogFragmentListener) activity, requestCode);
        }

        return null;
    }
}
