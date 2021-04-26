package com.example.attendancenquizsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attendancenquizsystem.Presenter.AddQuizPresenter;
import com.example.attendancenquizsystem.R;
import com.example.attendancenquizsystem.View.addquizView;

public class AddQuiz extends AppCompatActivity implements addquizView {
    EditText questions;
    Button upload;
    AddQuizPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        questions=findViewById(R.id.questions);
        upload=findViewById(R.id.uploadquiz);
        Log.d("course", "onCreate: "+getIntent().getStringExtra("Subject"));
        presenter= new AddQuizPresenter(this);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addquiz(questions.getText().toString(),getIntent().getStringExtra("Subject"),getIntent().getStringExtra("title"));
            }
        });
    }

    @Override
    public void addedsuccesfully() {
        Toast.makeText(this, "added succesfully", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void failed() {
        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }
}
