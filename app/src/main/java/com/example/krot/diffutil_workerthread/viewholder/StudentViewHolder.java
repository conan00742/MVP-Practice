package com.example.krot.diffutil_workerthread.viewholder;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krot.diffutil_workerthread.BusStation;
import com.example.krot.diffutil_workerthread.R;
import com.example.krot.diffutil_workerthread.event.EventAddNewStudent;
import com.example.krot.diffutil_workerthread.event.EventRemoveStudent;
import com.example.krot.diffutil_workerthread.main_screen.MainActivity;
import com.example.krot.diffutil_workerthread.model.Student;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Krot on 1/15/18.
 */

public class StudentViewHolder extends ItemBaseViewHolder<Student> {

    @BindView(R.id.tv_student_name)
    TextView tvStudentName;
    @BindView(R.id.tv_action_add)
    public TextView tvAddStudent;
    @BindView(R.id.tv_action_remove)
    public TextView tvRemoveStudent;

    public StudentViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(@NonNull Student item) {
        super.bindData(item);
        tvStudentName.setText(item.getStudentName());
    }

    @OnClick(R.id.tv_action_add)
    public void doAddNewStudent() {
//        Log.i("WTF", "StudentViewHolder - doAddNewStudent");
        BusStation.getEventBus().post(new EventAddNewStudent(StudentViewHolder.this, MainActivity.ADD_ACTION_TAG));
    }

    @OnClick(R.id.tv_action_remove)
    public void deRemoveStudent() {
//        Log.i("WTF", "StudentViewHolder - doRemoveStudent");
        BusStation.getEventBus().post(new EventRemoveStudent(StudentViewHolder.this, MainActivity.REMOVE_ACTION_TAG));
    }

}
