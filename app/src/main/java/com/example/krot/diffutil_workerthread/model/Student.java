package com.example.krot.diffutil_workerthread.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Krot on 1/15/18.
 */

public class Student implements Item {

    @NonNull
    private final String mStudentId;

    @NonNull
    private final String mStudentName;

    public Student(@NonNull String mStudentId, @NonNull String mStudentName) {
        this.mStudentId = mStudentId;
        this.mStudentName = mStudentName;
    }

    @NonNull
    public String getStudentId() {
        return mStudentId;
    }

    @NonNull
    public String getStudentName() {
        return mStudentName;
    }

    public static List<Student> generateStudentList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(UUID.randomUUID().toString(), "Sarah"));
        studentList.add(new Student(UUID.randomUUID().toString(), "John"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Pique"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Mike"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Luis"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Amanda"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Leo"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Squall"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Pudge"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Furion"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Antonio"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Varane"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Marcelo"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Henrique"));
        studentList.add(new Student(UUID.randomUUID().toString(), "Alex"));
        return studentList;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student currentStudent = (Student) obj;
            return (this.getStudentId().equals(currentStudent.getStudentId()));
        } else {
            return false;
        }
    }

    @Override
    public boolean sameContent(Item item) {
        Student currentStudent = (Student) item;
        return (this.getStudentName().equals(currentStudent.getStudentName()));
    }
}
