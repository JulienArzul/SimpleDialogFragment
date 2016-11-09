package com.a2appaz.simpledialogfragment.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.a2appaz.simpledialogfragment.SimpleDialogFragment;
import com.a2appaz.simpledialogfragment.SimpleDialogFragmentListener;

public class MainActivity extends AppCompatActivity implements SimpleDialogFragmentListener {

    private static final int SIMPLE_DIALOG_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SimpleDialogFragment.newInstance("My Dialog", "Test of a simple dialog", this.getString(android.R.string.ok), this.getString(android.R.string.cancel), SIMPLE_DIALOG_REQUEST_CODE)
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
