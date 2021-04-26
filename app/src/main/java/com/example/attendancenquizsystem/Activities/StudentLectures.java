package com.example.attendancenquizsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.attendancenquizsystem.Adapters.studentlecadapter;
import com.example.attendancenquizsystem.model.Lecture;
import com.example.attendancenquizsystem.Presenter.StudentLecturesPresenter;
import com.example.attendancenquizsystem.Presenter.TeacherLecturesPresenter;
import com.example.attendancenquizsystem.R;
import com.example.attendancenquizsystem.View.StudentLecturesView;

import java.util.ArrayList;

public class StudentLectures extends AppCompatActivity implements StudentLecturesView {
    RecyclerView recycler;
    studentlecadapter myadapter;
    StudentLecturesPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_lectures);
        recycler=findViewById(R.id.recycler);
        presenter=new StudentLecturesPresenter(this);
        presenter.retrivelectures(getIntent().getStringExtra("Subject"));
        recycler.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void getData(ArrayList<Lecture> lectures) {
        myadapter= new studentlecadapter(lectures,getIntent().getStringExtra("Subject"));
        recycler.setAdapter(myadapter);
    }
}
