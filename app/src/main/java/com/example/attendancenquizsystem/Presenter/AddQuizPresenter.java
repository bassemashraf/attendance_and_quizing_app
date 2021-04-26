package com.example.attendancenquizsystem.Presenter;

import androidx.annotation.NonNull;

import com.example.attendancenquizsystem.View.addquizView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddQuizPresenter {
    addquizView view;
    public AddQuizPresenter(addquizView v){
view=v;
    }
    public void addquiz(String text, final String subject, final String title){
        final DatabaseReference mydatabase= FirebaseDatabase.getInstance().getReference().child("Courses").child(subject).child(title).child("Quiz");
        HashMap<String,String> map=new HashMap<>();
        map.put("Questions",text);
        mydatabase.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful()){
            view.addedsuccesfully();

        }
        else{
            view.failed();
        }

            }
        });
    }
}
