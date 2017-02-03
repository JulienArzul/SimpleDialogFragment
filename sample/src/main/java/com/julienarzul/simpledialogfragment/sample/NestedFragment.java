package com.julienarzul.simpledialogfragment.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.julienarzul.simpledialogfragment.SimpleDialogContent;
import com.julienarzul.simpledialogfragment.SimpleDialogFragment;
import com.julienarzul.simpledialogfragment.SimpleDialogFragmentListener;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Julien Arzul on 3/2/17.
 * Copyright @ Julien Arzul 2017
 */

public class NestedFragment extends Fragment implements SimpleDialogFragmentListener {

    private static final int SIMPLE_DIALOG_NESTED_IN_FRAGMENT_REQUEST_CODE = 101;

    private Unbinder unbinder;

    public static NestedFragment newInstance() {
        Bundle args = new Bundle();

        NestedFragment fragment = new NestedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nested, container, false);

        this.unbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (this.unbinder != null) {
            this.unbinder.unbind();
            this.unbinder = null;
        }
    }

    @OnClick(R.id.simple_dialog_nested_in_fragment_button)
    void onNestedInFragmentButtonClicked() {
        SimpleDialogContent dialogContent = SimpleDialogContent.builder()
                .setTitle(this.getString(R.string.dialog_title))
                .setMessage(this.getString(R.string.dialog_message))
                .setPositiveButtonText(this.getString(R.string.dialog_button_positive))
                .setNegativeButtonText(this.getString(R.string.dialog_button_negative))
                .setRequestCode(SIMPLE_DIALOG_NESTED_IN_FRAGMENT_REQUEST_CODE)
                .build();

        SimpleDialogFragment dialogFragment = SimpleDialogFragment.newInstance(dialogContent);
        dialogFragment.setTargetFragment(this, SIMPLE_DIALOG_NESTED_IN_FRAGMENT_REQUEST_CODE);
        dialogFragment.show(this.getFragmentManager(), SimpleDialogFragment.TAG);
    }

    @Override
    public void onPositiveButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (requestCode != null) {
            switch (requestCode) {
                case SIMPLE_DIALOG_NESTED_IN_FRAGMENT_REQUEST_CODE:
                    Toast.makeText(this.getContext(), "Nested in Fragment - Positive button clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onNegativeButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (requestCode != null) {
            switch (requestCode) {
                case SIMPLE_DIALOG_NESTED_IN_FRAGMENT_REQUEST_CODE:
                    Toast.makeText(this.getContext(), "Nested in Fragment - Negative button clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onNeutralButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (requestCode != null) {
            switch (requestCode) {
                case SIMPLE_DIALOG_NESTED_IN_FRAGMENT_REQUEST_CODE:
                    // No neutral button
                    break;
            }
        }
    }
}
