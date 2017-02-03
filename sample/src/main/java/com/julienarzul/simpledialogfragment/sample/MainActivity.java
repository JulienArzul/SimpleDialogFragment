package com.julienarzul.simpledialogfragment.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.julienarzul.simpledialogfragment.SimpleDialogContent;
import com.julienarzul.simpledialogfragment.SimpleDialogFragment;
import com.julienarzul.simpledialogfragment.SimpleDialogFragmentListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SimpleDialogFragmentListener {

    private static final int SIMPLE_DIALOG_ONE_BUTTON_REQUEST_CODE = 1;
    private static final int SIMPLE_DIALOG_TWO_BUTTONS_REQUEST_CODE = 2;
    private static final int SIMPLE_DIALOG_NOT_CANCELABLE_REQUEST_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

    private void displaySimpleDialogFragment(SimpleDialogContent dialogContent) {
        SimpleDialogFragment.newInstance(dialogContent)
                .show(this.getSupportFragmentManager(), SimpleDialogFragment.TAG);
    }

    @OnClick(R.id.simple_dialog_no_title_button)
    void onNoTitleButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.create(null, this.getString(R.string.dialog_message)));
    }

    @OnClick(R.id.simple_dialog_with_title_button)
    void onWithTitleButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.create(
                        this.getString(R.string.dialog_title),
                        this.getString(R.string.dialog_message)));
    }

    @OnClick(R.id.simple_dialog_one_button_button)
    void onOneButtonButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.create(
                        this.getString(R.string.dialog_title),
                        this.getString(R.string.dialog_message),
                        this.getString(R.string.dialog_button_positive),
                        null,
                        SIMPLE_DIALOG_ONE_BUTTON_REQUEST_CODE));
    }

    @OnClick(R.id.simple_dialog_two_buttons_button)
    void onTwoButtonsButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.create(
                        this.getString(R.string.dialog_title),
                        this.getString(R.string.dialog_message),
                        this.getString(R.string.dialog_button_positive),
                        this.getString(R.string.dialog_button_negative),
                        SIMPLE_DIALOG_TWO_BUTTONS_REQUEST_CODE));
    }

    @OnClick(R.id.simple_dialog_not_cancelable_button)
    void onNotCancelableButtonClicked() {
        this.displaySimpleDialogFragment(
                SimpleDialogContent.create(
                        this.getString(R.string.dialog_title),
                        this.getString(R.string.dialog_message),
                        this.getString(R.string.dialog_button_positive),
                        null,
                        SIMPLE_DIALOG_NOT_CANCELABLE_REQUEST_CODE,
                        false));
    }

    @Override
    public void onPositiveButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (requestCode != null) {
            switch (requestCode) {
                case SIMPLE_DIALOG_ONE_BUTTON_REQUEST_CODE:
                    Toast.makeText(this, "One button - Positive button clicked", Toast.LENGTH_SHORT).show();
                    break;

                case SIMPLE_DIALOG_TWO_BUTTONS_REQUEST_CODE:
                    Toast.makeText(this, "Two buttons - Positive button clicked", Toast.LENGTH_SHORT).show();
                    break;

                case SIMPLE_DIALOG_NOT_CANCELABLE_REQUEST_CODE:
                    Toast.makeText(this, "Not cancelable - Positive button clicked", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onNegativeButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (requestCode != null) {
            switch (requestCode) {
                case SIMPLE_DIALOG_ONE_BUTTON_REQUEST_CODE:
                    // No negative button
                    break;

                case SIMPLE_DIALOG_TWO_BUTTONS_REQUEST_CODE:
                    Toast.makeText(this, "Two buttons - Negative button clicked", Toast.LENGTH_SHORT).show();
                    break;

                case SIMPLE_DIALOG_NOT_CANCELABLE_REQUEST_CODE:
                    // No negative button
                    break;
            }
        }
    }
}
