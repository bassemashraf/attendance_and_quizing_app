package com.example.attendancenquizsystem.Presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.attendancenquizsystem.model.Lecture;
import com.example.attendancenquizsystem.View.StudentLecturesView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentLecturesPresenter {
    ArrayList<Lecture> lectures;
    StudentLecturesView v;
    public StudentLecturesPresenter(StudentLecturesView v){
        this.v=v;
        lectures=new ArrayList<Lecture>();
    }

    public void retrivelectures(String subject){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String id= user.getUid();
        final DatabaseReference mydatabase= FirebaseDatabase.getInstance().getReference().child("Courses").child(subject);
        mydatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Lecture mylecture=dataSnapshot.getValue(Lecture.class);
                Log.d("mylecture", "onChildAdded: "+mylecture.getNote());
                Log.d("mylecture", "onChildAdded: "+mylecture.getTitle());
                Log.d("test", "onChildAdded: "+dataSnapshot.getValue());
                Log.d("test", "onChildAdded: "+dataSnapshot.getKey()   );

                lectures.add(mylecture);
                v.getData(lectures);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
