package com.example.krot.diffutil_workerthread.main_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.krot.diffutil_workerthread.BusStation;
import com.example.krot.diffutil_workerthread.DialogLoading;
import com.example.krot.diffutil_workerthread.R;
import com.example.krot.diffutil_workerthread.StudentContract;
import com.example.krot.diffutil_workerthread.adapter.StudentAdapter;
import com.example.krot.diffutil_workerthread.event.EventAddNewStudent;
import com.example.krot.diffutil_workerthread.event.EventRemoveStudent;
import com.example.krot.diffutil_workerthread.model.Item;
import com.example.krot.diffutil_workerthread.model.Student;
import com.example.krot.diffutil_workerthread.presenter.main_screen.StudentActionActionPresenterImpl;
import com.squareup.otto.Subscribe;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements StudentContract.StudentView {

    public static final String ADD_ACTION_TAG = "ADD_ACTION";
    public static final String REMOVE_ACTION_TAG = "REMOVE_ACTION";
    public static final String LOADING_ACTION_TAG = "LOADING_ACTION";

    @BindView(R.id.student_recycler_view)
    RecyclerView mStudentRecyclerView;
    @BindView(R.id.loading_item_progress_bar)
    ProgressBar mLoadingIndicator;
    @BindView(R.id.temp_background)
    FrameLayout mTempBackground;

    DialogLoading dialogLoading;

    private StudentActionActionPresenterImpl studentActionPresenter;
    private StudentAdapter studentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dialogLoading = new DialogLoading(this);
//        studentActionPresenter = ViewModelProviders.of(this).get(StudentActionActionPresenterImpl.class);
        studentActionPresenter = ViewModelProviders.of(this).get(StudentActionActionPresenterImpl.class);
        studentActionPresenter.setStudentView(this);
        setUpStudentAdapter();
        studentActionPresenter.loadData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        BusStation.getEventBus().register(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        BusStation.getEventBus().unregister(this);
    }


    @Override
    public void onBackPressed() {
        finish();
    }


    public void setUpStudentAdapter() {
        studentAdapter = new StudentAdapter();
        mStudentRecyclerView.setAdapter(studentAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mStudentRecyclerView.setLayoutManager(manager);
        mStudentRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    //event add new student
    @Subscribe
    public void receiveMessageAddNewStudent(EventAddNewStudent eventAddNewStudent) {
        Student newStudent = new Student(UUID.randomUUID().toString(), "Harry Potter");
        studentActionPresenter.doAddStudent(newStudent, eventAddNewStudent.getStudentViewHolder().getAdapterPosition(), mStudentRecyclerView);
    }

    //event remove student
    @Subscribe
    public void receiveMessageRemoveStudent(EventRemoveStudent eventRemoveStudent) {
        studentActionPresenter.doRemoveStudent(eventRemoveStudent.getStudentViewHolder().getAdapterPosition(), mStudentRecyclerView);
    }


    @Override
    public void updateListItem(List<Item> newItemList) {
        studentAdapter.updateListItem(newItemList);
    }

    @Override
    public void showLoadingIndicator(boolean isShowing, String actionType) {
//        Log.i("WTF", "isShowing = " + isShowing);
        if (isShowing) {
            dialogLoading.setMessage(actionType);
            dialogLoading.show();
        } else {
            dialogLoading.dismiss();
        }
    }

    @Override
    public void disableViewGroup(RecyclerView studentRecyclerView) {
        studentRecyclerView.setEnabled(false);
    }

    @Override
    public void enableViewGroup(RecyclerView studentRecyclerView) {
        studentRecyclerView.setEnabled(true);
    }


}
