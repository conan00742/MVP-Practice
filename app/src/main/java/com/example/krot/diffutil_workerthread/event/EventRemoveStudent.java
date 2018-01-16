package com.example.krot.diffutil_workerthread.event;

import android.support.annotation.NonNull;

import com.example.krot.diffutil_workerthread.viewholder.StudentViewHolder;

/**
 * Created by Krot on 1/15/18.
 */

public class EventRemoveStudent {

    @NonNull
    private final StudentViewHolder studentViewHolder;

    @NonNull
    private final String actionType;

    public EventRemoveStudent(@NonNull StudentViewHolder studentViewHolder, @NonNull String actionType) {
        this.studentViewHolder = studentViewHolder;
        this.actionType = actionType;
    }

    @NonNull
    public StudentViewHolder getStudentViewHolder() {
        return studentViewHolder;
    }

    @NonNull
    public String getActionType() {
        return actionType;
    }
}
