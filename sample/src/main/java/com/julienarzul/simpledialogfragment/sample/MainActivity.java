package com.julienarzul.simpledialogfragment.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.julienarzul.simpledialogfragment.SimpleDialogContent;
import com.julienarzul.simpledialogfragment.SimpleDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SimpleDialogFragment.OnPositiveButtonClickListener,
        SimpleDialogFragment.OnNegativeButtonClickListener, SimpleDialogFragment.OnNeutralButtonClickListener {

    private static final int SIMPLE_DIALOG_ONE_BUTTON_REQUEST_CODE = 1;
    private static final int SIMPLE_DIALOG_TWO_BUTTONS_REQUEST_CODE = 2;
    private static final int SIMPLE_DIALOG_NOT_CANCELABLE_REQUEST_CODE = 3;
    private static final int SIMPLE_DIALOG_THREE_BUTTONS_REQUEST_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.simple_dialog_fragment_container, NestedFragment.newInstance())
                    .commit();
        }
    }

    private void displaySimpleDialogFragment(SimpleDialogContent dialogContent) {
        SimpleDialogFragment.newInstance(dialogContent)
                .show(this.getSupportFragmentManager(), SimpleDialogFragment.TAG);
    }

    @OnClick(R.id.simple_dialog_no_title_button)
    void onNoTitleButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.builder()
                        .setMessage(this.getString(R.string.dialog_message))
                        .build());
    }

    @OnClick(R.id.simple_dialog_with_title_button)
    void onWithTitleButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.builder()
                        .setTitle(this.getString(R.string.dialog_title))
                        .setMessage(this.getString(R.string.dialog_message))
                        .build());
    }

    @OnClick(R.id.simple_dialog_one_button_button)
    void onOneButtonButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.builder()
                        .setTitle(this.getString(R.string.dialog_title))
                        .setMessage(this.getString(R.string.dialog_message))
                        .setPositiveButtonText(this.getString(R.string.dialog_button_positive))
                        .setRequestCode(SIMPLE_DIALOG_ONE_BUTTON_REQUEST_CODE)
                        .build());
    }

    @OnClick(R.id.simple_dialog_two_buttons_button)
    void onTwoButtonsButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.builder()
                        .setTitle(this.getString(R.string.dialog_title))
                        .setMessage(this.getString(R.string.dialog_message))
                        .setPositiveButtonText(this.getString(R.string.dialog_button_positive))
                        .setNegativeButtonText(this.getString(R.string.dialog_button_negative))
                        .setRequestCode(SIMPLE_DIALOG_TWO_BUTTONS_REQUEST_CODE)
                        .build());
    }

    @OnClick(R.id.simple_dialog_three_buttons_button)
    void onThreeButtonsButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.builder()
                        .setTitle(this.getString(R.string.dialog_title))
                        .setMessage(this.getString(R.string.dialog_message))
                        .setPositiveButtonText(this.getString(R.string.dialog_button_positive))
                        .setNegativeButtonText(this.getString(R.string.dialog_button_negative))
                        .setNeutralButtonText(this.getString(R.string.dialog_button_neutral))
                        .setRequestCode(SIMPLE_DIALOG_THREE_BUTTONS_REQUEST_CODE)
                        .build());
    }

    @OnClick(R.id.simple_dialog_not_cancelable_button)
    void onNotCancelableButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.builder()
                        .setTitle(this.getString(R.string.dialog_title))
                        .setMessage(this.getString(R.string.dialog_message))
                        .setPositiveButtonText(this.getString(R.string.dialog_button_positive))
                        .setRequestCode(SIMPLE_DIALOG_NOT_CANCELABLE_REQUEST_CODE)
                        .setCancelable(false)
                        .build());
    }

    @Override
    public void onDialogPositiveButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (requestCode != null) {
            switch (requestCode) {
                case SIMPLE_DIALOG_ONE_BUTTON_REQUEST_CODE:
                    Toast.makeText(this, "One button - Positive button clicked", Toast.LENGTH_SHORT).show();
                    break;

                case SIMPLE_DIALOG_TWO_BUTTONS_REQUEST_CODE:
                    Toast.makeText(this, "Two buttons - Positive button clicked", Toast.LENGTH_SHORT).show();
                    break;

                case SIMPLE_DIALOG_THREE_BUTTONS_REQUEST_CODE:
                    Toast.makeText(this, "Three buttons - Positive button clicked", Toast.LENGTH_SHORT).show();
                    break;

                case SIMPLE_DIALOG_NOT_CANCELABLE_REQUEST_CODE:
                    Toast.makeText(this, "Not cancelable - Positive button clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onDialogNegativeButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (requestCode != null) {
            switch (requestCode) {
                case SIMPLE_DIALOG_ONE_BUTTON_REQUEST_CODE:
                    // No negative button
                    break;

                case SIMPLE_DIALOG_TWO_BUTTONS_REQUEST_CODE:
                    Toast.makeText(this, "Two buttons - Negative button clicked", Toast.LENGTH_SHORT).show();
                    break;

                case SIMPLE_DIALOG_THREE_BUTTONS_REQUEST_CODE:
                    Toast.makeText(this, "Three buttons - Negative button clicked", Toast.LENGTH_SHORT).show();
                    break;

                case SIMPLE_DIALOG_NOT_CANCELABLE_REQUEST_CODE:
                    // No negative button
                    break;
            }
        }
    }

    @Override
    public void onDialogNeutralButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (requestCode != null) {
            switch (requestCode) {
                case SIMPLE_DIALOG_ONE_BUTTON_REQUEST_CODE:
                    // No neutral button
                    break;

                case SIMPLE_DIALOG_TWO_BUTTONS_REQUEST_CODE:
                    // No neutral button
                    break;

                case SIMPLE_DIALOG_THREE_BUTTONS_REQUEST_CODE:
                    Toast.makeText(this, "Three buttons - Neutral button clicked", Toast.LENGTH_SHORT).show();
                    break;

                case SIMPLE_DIALOG_NOT_CANCELABLE_REQUEST_CODE:
                    // No neutral button
                    break;
            }
        }
    }
}
