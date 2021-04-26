package com.example.attendancenquizsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.attendancenquizsystem.Presenter.StudentAttendPresenter;
import com.example.attendancenquizsystem.R;

public class StudentAttend extends AppCompatActivity {
    StudentAttendPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attend);
        presenter=new StudentAttendPresenter(this   );
    }
    public void onResume(){
        super.onResume();
        presenter.checkLecture(getIntent().getStringExtra("Subject"),getIntent().getStringExtra("Lecture"));
    }
}
