package com.example.attendancenquizsystem.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancenquizsystem.Adapters.coursesadapter;
import com.example.attendancenquizsystem.Presenter.StudentHomePresenter;
import com.example.attendancenquizsystem.R;
import com.example.attendancenquizsystem.View.StudentHomeView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StudentHome extends AppCompatActivity implements StudentHomeView {
    int cameracode=1;
    StudentHomePresenter presenter;
    coursesadapter courseadapter;
    RecyclerView view;
    FloatingActionButton scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        scan=findViewById(R.id.floatingActionButton);
        presenter=new StudentHomePresenter(this,this);
        presenter.RetriveCoursesnames();
        view=findViewById(R.id.recyclerView);
        view.setLayoutManager(new GridLayoutManager(this,2));
    }
    @Override
    public void getData(ArrayList<String> courses) {

        courseadapter=new coursesadapter(courses,"Student");
        view.setAdapter(courseadapter);
    }
    public void scan(View v){
        //Toast.makeText(this, "scan", Toast.LENGTH_SHORT).show();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(getApplicationContext(),ScanActivity.class));}
        else{
            requestpremssion();
        }

    }
    private void requestpremssion(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            new AlertDialog.Builder(this).
                    setTitle(" permission is needed").setMessage("Camera permission is needed to scan QR").setPositiveButton("okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(StudentHome.this,new String[]{Manifest.permission.CAMERA},cameracode);

                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},cameracode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode==cameracode){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Camera Permission granted , Arigato", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
