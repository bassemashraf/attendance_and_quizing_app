package com.example.attendancenquizsystem.Presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.attendancenquizsystem.Activities.AddLecture;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

public class AddLecturePresenter {
    Context cx;
    Uri pdf;
    private StorageReference mStorageRef;
    private StorageReference mStorageRef2;
    public AddLecturePresenter(Context cx){
        this.cx=cx;
    }
    public void addLecture(final String title, final String note, final String course, final String Lecturenumber, String map,Uri data){
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Courses").child(course).child(Lecturenumber);
        mStorageRef2=FirebaseStorage.getInstance().getReference().child("Courses").child(course).child(Lecturenumber+"pdf");
        Log.d("path", "addLecture: "+map.toString());
        final Uri uri= Uri.fromFile(new File(map));
        mStorageRef2.putFile(data).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){

                    task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            pdf= task.getResult();
                            mStorageRef.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    if(task.isSuccessful()){
                                        task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Uri> task) {
                                                Uri uri= task.getResult();
                                                DatabaseReference mydatabase= FirebaseDatabase.getInstance().getReference().child("Courses").child(course).child(title);
                                                HashMap<String,String> coursemap= new HashMap<>();
                                                coursemap.put("title",title);
                                                coursemap.put("note",note);
                                                coursemap.put("QR",uri.toString());
                                                coursemap.put("attendance","0");
                                                coursemap.put("pdf",pdf.toString());

                                                mydatabase.setValue(coursemap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            });

                        }
                    });
                }
            }
        });
        Log.d("uri", "addLecture: "+uri.toString());

    }
    public void getpdf(Uri data){

    }
}
