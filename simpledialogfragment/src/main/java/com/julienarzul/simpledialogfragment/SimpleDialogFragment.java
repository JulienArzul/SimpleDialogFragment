package com.julienarzul.simpledialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * Simple implementation of a {@link DialogFragment} that allows you to specify the content of the {@link AlertDialog}
 * displayed without subclassing it.
 * <p>
 * This DialogFragment must be instantiated with a {@link SimpleDialogContent} via the factory method {@link
 * #newInstance(SimpleDialogContent)}
 * <p>
 * You can easily define listeners for the buttons set in the {@link SimpleDialogContent} with one of the two methods:
 * <ul>
 * <li>the enclosing activity must implement one or more of the listener interfaces</li>
 * <li>the enclosing fragment must be set as the targetFragment of the {@link SimpleDialogFragment} and implement one
 * or more of the listener interfaces</li>
 * </ul>
 * <p>
 * There are three different listener interfaces that you can implement:
 * <ul>
 * <li>{@link OnPositiveButtonClickListener}</li>
 * <li>{@link OnNegativeButtonClickListener}</li>
 * <li>{@link OnNeutralButtonClickListener}</li>
 * </ul>
 * If the user chooses to listen to the click on the dialog's button, it is recommended to add a request code to the
 * {@link SimpleDialogContent}. That request code will be given to the onClick method and will allow the user to
 * determine which {@link SimpleDialogFragment} invoked the method. It is especially useful when a single {@link
 * android.app.Activity} or {@link Fragment} needs to display several {@link SimpleDialogFragment}
 * <p>
 * Copyright @ Julien Arzul 2016
 */
public class SimpleDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public static final String TAG = "SimpleDialogFragment";

    private static final Integer DEFAULT_REQUEST_CODE = 0;

    private static final String DIALOG_CONTENT_BUNDLE_KEY = "com.julienarzul.simpledialogfragment.SimpleDialogFragment.dialogContent";

    private SimpleDialogContent dialogContent = null;

    /**
     * Creates a new instance of a {@link SimpleDialogFragment}. It must be given a {@link SimpleDialogContent} to
     * define the {@link AlertDialog} content.
     *
     * @param dialogContent Instance of {@link SimpleDialogContent} representing the content of the {@link AlertDialog}
     *                      that will be displayed.
     *
     * @return A new instance of {@link SimpleDialogFragment}. Don't forget to call {{@link #show(FragmentManager,
     * String)}} to actually display the {@link DialogFragment}
     */
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

    /**
     * Interface that must be implemented by the enclosing activity (or target fragment) in order to listen to click on
     * the dialog positive's button.
     */
    public interface OnPositiveButtonClickListener {

        /**
         * This method will be invoked when the positive button of the dialog is clicked.
         *
         * @param dialog      The dialog that received the click.
         * @param requestCode The request code used to show the {@link SimpleDialogFragment}
         */
        void onDialogPositiveButtonClicked(DialogInterface dialog, Integer requestCode);
    }

    /**
     * Interface that must be implemented by the enclosing activity (or target fragment) in order to listen to click on
     * the dialog negative's button.
     */
    public interface OnNegativeButtonClickListener {

        /**
         * This method will be invoked when the negative button of the dialog is clicked.
         *
         * @param dialog      The dialog that received the click.
         * @param requestCode The request code used to show the {@link SimpleDialogFragment}
         */
        void onDialogNegativeButtonClicked(DialogInterface dialog, Integer requestCode);
    }

    /**
     * Interface that must be implemented by the enclosing activity (or target fragment) in order to listen to click on
     * the dialog neutral's button.
     */
    public interface OnNeutralButtonClickListener {

        /**
         * This method will be invoked when the neutral button of the dialog is clicked.
         *
         * @param dialog      The dialog that received the click.
         * @param requestCode The request code used to show the {@link SimpleDialogFragment}
         */
        void onDialogNeutralButtonClicked(DialogInterface dialog, Integer requestCode);
    }
}
