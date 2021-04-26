package com.example.attendancenquizsystem.Presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.attendancenquizsystem.View.TeacherHomeView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TeacherHomePresenter {
    TeacherHomeView view;
    Context cx;
    ArrayList<String> courses;
    public TeacherHomePresenter(TeacherHomeView myview,Context context){
        cx=context;
        view=myview;
        courses=new ArrayList<String>();
    }
    public void RetriveCoursesnames(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String id= user.getUid();
        DatabaseReference mydatabase= FirebaseDatabase.getInstance().getReference().child(id).child("Courses");
        mydatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                courses.add(dataSnapshot.getKey());
                Log.d("courses size", "RetriveCoursesnames: "+courses.size());
                view.getData(courses);
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
