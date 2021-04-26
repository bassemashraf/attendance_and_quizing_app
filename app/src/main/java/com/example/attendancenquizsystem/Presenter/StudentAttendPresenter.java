package com.example.attendancenquizsystem.Presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentAttendPresenter {
    Context cx;
    public StudentAttendPresenter(Context cx) {
    }
    public void checkLecture(String course,String lecture){
        final DatabaseReference mydatabase= FirebaseDatabase.getInstance().getReference().child("Courses").child(course).child("Lecture"+lecture);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String id= user.getUid();
        DatabaseReference getid= FirebaseDatabase.getInstance().getReference().child(id);
                getid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("id", "onDataChange: "+dataSnapshot.getValue());
                String name=dataSnapshot.child("Name").getValue().toString();
                String id=dataSnapshot.child("ID").getValue().toString();
                mydatabase.child("Students").child(name).setValue(id).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(cx, "attended sucessfully", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
