package com.a2appaz.simpledialogfragment;

import android.content.DialogInterface;

/**
 * Created by Julien Arzul on 09/11/2016.
 * Copyright @ 2 App à Z 2016
 */
public interface SimpleDialogFragmentListener {

    void onPositiveButtonClicked(DialogInterface dialog, Integer requestCode);

    void onNegativeButtonClicked(DialogInterface dialog, Integer requestCode);
}
