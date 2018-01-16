package com.example.krot.diffutil_workerthread;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.krot.diffutil_workerthread.model.Item;
import com.example.krot.diffutil_workerthread.model.Student;

import java.util.List;

/**
 * Created by Krot on 1/15/18.
 */

public interface StudentContract {

    interface StudentView {
        void updateListItem(List<Item> newItemList);
        void showLoadingIndicator(boolean isShowing, String actionType);
        void disableViewGroup(RecyclerView studentRecyclerView);
        void enableViewGroup(RecyclerView studentRecyclerView);

    }

    interface StudentActionPresenter {
        void loadData();
        void doAddStudent(Student student, int position, RecyclerView studentRecyclerView);
        void doRemoveStudent(int position, RecyclerView studentRecyclerView);
    }
}
