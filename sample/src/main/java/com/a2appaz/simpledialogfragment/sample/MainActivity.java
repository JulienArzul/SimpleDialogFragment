package com.a2appaz.simpledialogfragment.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.a2appaz.simpledialogfragment.SimpleDialogFragment;
import com.a2appaz.simpledialogfragment.SimpleDialogFragmentListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SimpleDialogFragmentListener {

    private static final int SIMPLE_DIALOG_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.simple_dialog_no_title_button)
    void onNoTitleButtonClicked() {
        SimpleDialogFragment.newInstance(this.getString(R.string.dialog_message))
                .show(this.getSupportFragmentManager(), SimpleDialogFragment.TAG);
    }

    @OnClick(R.id.simple_dialog_with_title_button)
    void onWithTitleButtonClicked() {
        SimpleDialogFragment.newInstance(this.getString(R.string.dialog_title), this.getString(R.string.dialog_message))
                .show(this.getSupportFragmentManager(), SimpleDialogFragment.TAG);
    }

    @Override
    public void onPositiveButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (SIMPLE_DIALOG_REQUEST_CODE == requestCode) {
            Toast.makeText(this, "Ok button clicked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNegativeButtonClicked(DialogInterface dialog, Integer requestCode) {
        if (SIMPLE_DIALOG_REQUEST_CODE == requestCode) {
            Toast.makeText(this, "Cancel button clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
