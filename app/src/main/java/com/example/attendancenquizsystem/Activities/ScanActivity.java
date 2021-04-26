package com.example.attendancenquizsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.attendancenquizsystem.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView= new ZXingScannerView(this);
        setContentView(scannerView);
    }

    @Override
    public void handleResult(Result result) {
        String y=result.getText();
        String parts[]=y.split("/");
        String course=parts[0];
        String lecture=parts[1];
        Log.d("scanresult", "handleResult: "+course+"\n"+lecture);
        Intent intent= new Intent(this,StudentAttend.class);
        intent.putExtra("Subject",course);
        intent.putExtra("Lecture",lecture);
        startActivity(intent);
        //onBackPressed();
    }
    protected void onPause(){
        super.onPause();
        scannerView.stopCamera();
    }
    protected void onResume(){
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}
