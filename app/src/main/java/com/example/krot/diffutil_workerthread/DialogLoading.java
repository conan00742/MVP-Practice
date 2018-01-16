package com.example.krot.diffutil_workerthread;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.krot.diffutil_workerthread.main_screen.MainActivity;

/**
 * Created by Krot on 1/16/18.
 */

public class DialogLoading extends Dialog {

    private TextView mLoadingMessage;


    public DialogLoading(@NonNull Context context) {
        super(context);
        this.setContentView(R.layout.loading_dialog);
        mLoadingMessage = findViewById(R.id.loading_message);
    }

    public void setMessage(String type) {
        if (type.equals(MainActivity.ADD_ACTION_TAG)) {
            mLoadingMessage.setText("Adding...");
        } else if (type.equals(MainActivity.REMOVE_ACTION_TAG)) {
            mLoadingMessage.setText("Removing...");
        } else {
            mLoadingMessage.setText("Loading...");
        }
    }

}
