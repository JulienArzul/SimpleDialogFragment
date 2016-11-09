package com.a2appaz.simpledialogfragment;

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
 * Copyright @ 2 App à Z 2016
 */
public class SimpleDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public static final String TAG = "SimpleDialogFragment";

    private static final Integer DEFAULT_REQUEST_CODE = 0;

    private static final String TITLE_BUNDLE_KEY = "com.nomadeducation.balthazar.android.ui.core.dialogs.SimpleDialogFragment.title";

    private static final String MESSAGE_BUNDLE_KEY = "com.nomadeducation.balthazar.android.ui.core.dialogs.SimpleDialogFragment.message";

    private static final String CONFIRM_BUTTON_TEXT_BUNDLE_KEY = "com.nomadeducation.balthazar.android.ui.core.dialogs.SimpleDialogFragment.confirmButtonText";

    private static final String NEGATIVE_BUTTON_TEXT_BUNDLE_KEY = "com.nomadeducation.balthazar.android.ui.core.dialogs.SimpleDialogFragment.negativeButtonText";

    private static final String REQUEST_CODE_BUNDLE_KEY = "com.nomadeducation.balthazar.android.ui.core.dialogs.ui.core.SimpleDialogFragment.requestCode";

    private static final String CANCELABLE_BUNDLE_KEY = "com.nomadeducation.balthazar.android.ui.core.dialogs.ui.core.SimpleDialogFragment.cancelable";

    private String title = null;

    private String message = null;

    private String confirmButtonText = null;

    private String negativeButtonText = null;

    private Integer requestCode = null;

    public static SimpleDialogFragment newInstance(String message) {
        return newInstance(null, message);
    }

    public static SimpleDialogFragment newInstance(String title, String message) {
        return newInstance(title, message, null);
    }

    public static SimpleDialogFragment newInstance(String title, String message, String confirmButtonText) {
        return newInstance(title, message, confirmButtonText, null);
    }

    public static SimpleDialogFragment newInstance(String title, String message, String confirmButtonText, Integer requestCode) {
        return newInstance(title, message, confirmButtonText, null, true, requestCode);
    }

    public static SimpleDialogFragment newInstance(String title, String message, String confirmButtonText, boolean cancelable, Integer requestCode) {
        return newInstance(title, message, confirmButtonText, null, cancelable, requestCode);
    }

    public static SimpleDialogFragment newInstance(String title, String message, String confirmButtonText, String negativeButtonText, Integer requestCode) {
        return newInstance(title, message, confirmButtonText, negativeButtonText, true, requestCode);
    }

    public static SimpleDialogFragment newInstance(String title, String message, String confirmButtonText, String negativeButtonText, boolean cancelable, Integer requestCode) {
        Bundle args = new Bundle();

        if (!TextUtils.isEmpty(title)) {
            args.putString(TITLE_BUNDLE_KEY, title);
        }
        if (!TextUtils.isEmpty(message)) {
            args.putString(MESSAGE_BUNDLE_KEY, message);
        }
        if (!TextUtils.isEmpty(confirmButtonText)) {
            args.putString(CONFIRM_BUTTON_TEXT_BUNDLE_KEY, confirmButtonText);
        }

        if (!TextUtils.isEmpty(negativeButtonText)) {
            args.putString(NEGATIVE_BUTTON_TEXT_BUNDLE_KEY, negativeButtonText);
        }

        if (requestCode == null) {
            requestCode = DEFAULT_REQUEST_CODE;
        }
        args.putInt(REQUEST_CODE_BUNDLE_KEY, requestCode);

        args.putBoolean(CANCELABLE_BUNDLE_KEY, cancelable);

        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            this.title = arguments.getString(TITLE_BUNDLE_KEY);
            this.message = arguments.getString(MESSAGE_BUNDLE_KEY);
            this.confirmButtonText = arguments.getString(CONFIRM_BUTTON_TEXT_BUNDLE_KEY);
            this.negativeButtonText = arguments.getString(NEGATIVE_BUTTON_TEXT_BUNDLE_KEY, null);
            if (arguments.containsKey(REQUEST_CODE_BUNDLE_KEY)) {
                this.requestCode = arguments.getInt(REQUEST_CODE_BUNDLE_KEY);
            }

            this.setCancelable(arguments.getBoolean(CANCELABLE_BUNDLE_KEY, true));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if (!TextUtils.isEmpty(this.title)) {
            builder.setTitle(this.title);
        }

        if (!TextUtils.isEmpty(this.message)) {
            builder.setMessage(this.message);
        }

        if (TextUtils.isEmpty(this.confirmButtonText)) {
            this.confirmButtonText = getString(android.R.string.ok);
        }
        builder.setPositiveButton(this.confirmButtonText, this);

        if (!TextUtils.isEmpty(this.negativeButtonText)) {
            builder.setNegativeButton(this.negativeButtonText, this);
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
            return new Pair<>((SimpleDialogFragmentListener) activity, this.requestCode);
        }

        return null;
    }
}
