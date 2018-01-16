package com.example.krot.diffutil_workerthread.presenter.main_screen;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.example.krot.diffutil_workerthread.StudentContract;
import com.example.krot.diffutil_workerthread.main_screen.MainActivity;
import com.example.krot.diffutil_workerthread.model.Item;
import com.example.krot.diffutil_workerthread.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krot on 1/15/18.
 */

public class StudentActionActionPresenterImpl extends ViewModel implements StudentContract.StudentActionPresenter {

    @Nullable
    private StudentContract.StudentView studentView;

    @Nullable
    private List<Item> dataList;

    @Nullable
    private AsyncTask<Void, Void, List<Item>> addStudentAsync;
    @Nullable
    private AsyncTask<Void, Void, List<Item>> removeStudentAsync;
    @Nullable
    private AsyncTask<Void, Void, List<Item>> loadDataAsync;



    @Override
    protected void onCleared() {
        super.onCleared();
        if (loadDataAsync != null && loadDataAsync.getStatus() == AsyncTask.Status.RUNNING) {
            loadDataAsync.cancel(true);
        } else if (addStudentAsync != null && addStudentAsync.getStatus() == AsyncTask.Status.RUNNING) {
            addStudentAsync.cancel(true);
        } else if (removeStudentAsync != null && removeStudentAsync.getStatus() == AsyncTask.Status.RUNNING) {
            removeStudentAsync.cancel(true);
        }

    }

    public void setStudentView(@NonNull StudentContract.StudentView studentView) {
        this.studentView = studentView;
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public void loadData() {
        if (loadDataAsync != null && (loadDataAsync.getStatus() == AsyncTask.Status.RUNNING)) {
            if (studentView != null) {
                studentView.showLoadingIndicator(true, MainActivity.LOADING_ACTION_TAG);
                studentView.updateListItem(dataList);
            }
        } else if (addStudentAsync != null && (addStudentAsync.getStatus() == AsyncTask.Status.RUNNING)) {
            if (studentView != null) {
                studentView.showLoadingIndicator(true, MainActivity.ADD_ACTION_TAG);
                studentView.updateListItem(dataList);
            }
        } else if (removeStudentAsync != null && (removeStudentAsync.getStatus() == AsyncTask.Status.RUNNING)) {
            if (studentView != null) {
                studentView.showLoadingIndicator(true, MainActivity.REMOVE_ACTION_TAG);
                studentView.updateListItem(dataList);
            }
        } else {
            loadDataAsync = new AsyncTask<Void, Void, List<Item>>() {
                @Override
                protected void onPreExecute() {
                    if (studentView != null) {
                        studentView.showLoadingIndicator(true, MainActivity.LOADING_ACTION_TAG);
                    }
                }

                @Override
                protected List<Item> doInBackground(Void... voids) {
                    if (dataList == null) {
                        try {
                            Thread.sleep(4000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dataList = new ArrayList<>();
                        dataList.addAll(Student.generateStudentList());
                    }
                    return dataList;
                }

                @Override
                protected void onPostExecute(List<Item> itemList) {
                    if (studentView != null) {
                        studentView.showLoadingIndicator(false, MainActivity.LOADING_ACTION_TAG);
                        studentView.updateListItem(dataList);
                    }
                }
            };
            loadDataAsync.execute();
        }
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public void doAddStudent(final Student student, final int position, final RecyclerView studentRecyclerView) {
        //add student asyncTask
        addStudentAsync = new AsyncTask<Void, Void, List<Item>>() {

            @Override
            protected void onPreExecute() {
                if (studentView != null) {
                    studentView.showLoadingIndicator(true, MainActivity.ADD_ACTION_TAG);
                }
            }

            @Override
            protected List<Item> doInBackground(Void... voids) {
                final List<Item> newItemList = new ArrayList<>();
                if (dataList != null) {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < dataList.size(); i++) {
                        Student currentStudent = (Student) dataList.get(i);
                        if (i == position) {
                            newItemList.add(position, student);
                            newItemList.add(currentStudent);
                        } else {
                            newItemList.add(currentStudent);
                        }
                    }
                }
                return newItemList;
            }

            @Override
            protected void onPostExecute(List<Item> itemList) {
                dataList = itemList;
                if (studentView != null) {
                    studentView.showLoadingIndicator(false, MainActivity.ADD_ACTION_TAG);
                    studentView.updateListItem(dataList);
                }

            }
        };
        addStudentAsync.execute();
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public void doRemoveStudent(final int position, final RecyclerView studentRecyclerView) {
        //remove student asyncTask
        removeStudentAsync = new AsyncTask<Void, Void, List<Item>>() {
            @Override
            protected void onPreExecute() {
                if (studentView != null) {
                    studentView.showLoadingIndicator(true, MainActivity.REMOVE_ACTION_TAG);
                    studentView.disableViewGroup(studentRecyclerView);
                }
            }

            @Override
            protected List<Item> doInBackground(Void... voids) {
                final List<Item> newItemList = new ArrayList<>();
                if (dataList != null) {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < dataList.size(); i++) {
                        Student currentStudent = (Student) dataList.get(i);
                        if (i == position) {
                            continue;
                        }
                        newItemList.add(currentStudent);
                    }
                }

                return newItemList;
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                dataList = items;
                if (studentView != null) {
                    studentView.showLoadingIndicator(false, MainActivity.REMOVE_ACTION_TAG);
                    studentView.enableViewGroup(studentRecyclerView);
                    studentView.updateListItem(dataList);
                }
            }
        };
        removeStudentAsync.execute();
    }


}
