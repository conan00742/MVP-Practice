package com.example.krot.diffutil_workerthread.adapter;

import android.view.ViewGroup;

import com.example.krot.diffutil_workerthread.R;
import com.example.krot.diffutil_workerthread.viewholder.ItemBaseViewHolder;
import com.example.krot.diffutil_workerthread.viewholder.StudentViewHolder;

/**
 * Created by Krot on 1/15/18.
 */

public class StudentAdapter extends ItemBaseAdapter {

    @Override
    public ItemBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StudentViewHolder(parent, R.layout.student_item);
    }
}
