package com.example.attendancenquizsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.attendancenquizsystem.Adapters.coursesadapter;
import com.example.attendancenquizsystem.Presenter.StudentHomePresenter;
import com.example.attendancenquizsystem.Presenter.TeacherHomePresenter;
import com.example.attendancenquizsystem.R;
import com.example.attendancenquizsystem.View.TeacherHomeView;

import java.util.ArrayList;

public class TeacherHome extends AppCompatActivity implements TeacherHomeView {
    TeacherHomePresenter presenter;
    RecyclerView view;
    coursesadapter courseadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        presenter=new TeacherHomePresenter(this,this);
        presenter.RetriveCoursesnames();
        view=findViewById(R.id.recyclerView);
        view.setLayoutManager(new GridLayoutManager(this,2));
    }
    @Override
    public void getData(ArrayList<String> courses) {
        courseadapter=new coursesadapter(courses,"Teacher");
        view.setAdapter(courseadapter);
    }
}
