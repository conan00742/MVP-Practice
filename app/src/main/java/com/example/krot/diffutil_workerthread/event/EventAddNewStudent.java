package com.example.krot.diffutil_workerthread.event;

import android.support.annotation.NonNull;

import com.example.krot.diffutil_workerthread.viewholder.StudentViewHolder;

/**
 * Created by Krot on 1/15/18.
 */

public class EventAddNewStudent {

    @NonNull
    private final StudentViewHolder studentViewHolder;
    @NonNull
    private final String actionType;

    public EventAddNewStudent(@NonNull StudentViewHolder studentViewHolder, @NonNull String actionType) {
        this.studentViewHolder = studentViewHolder;
        this.actionType = actionType;
    }

    public StudentViewHolder getStudentViewHolder() {
        return studentViewHolder;
    }

    @NonNull
    public String getActionType() {
        return actionType;
    }
}
