package com.example.attendancenquizsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancenquizsystem.Presenter.JoinQuizPresenter;
import com.example.attendancenquizsystem.R;
import com.example.attendancenquizsystem.View.JoinQuizView;

public class JoinQuiz extends AppCompatActivity implements JoinQuizView {
    TextView question;
    EditText answer;
    Button send;
    JoinQuizPresente mypresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_quiz);
        question=findViewById(R.id.question);
        answer=findViewById(R.id.myanswer);
        send=findViewById(R.id.sendanswer);
        mypresenter=new JoinQuizPresenter(this);
        mypresenter.retrivequestion(getIntent().getStringExtra("Subject"),getIntent().getStringExtra("title"));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypresenter.uploadanswer(getIntent().getStringExtra("Subject"),getIntent().getStringExtra("title"),answer.getText().toString());
            }
        });
    }

    @Override
    public void getQuestion(String question) {
        this.question.setText(question);
    }

    @Override
    public void uploaded() {
        Toast.makeText(this, "answer uploaded ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed() {
        Toast.makeText(this, "Something went wrong ", Toast.LENGTH_SHORT).show();
    }
}
