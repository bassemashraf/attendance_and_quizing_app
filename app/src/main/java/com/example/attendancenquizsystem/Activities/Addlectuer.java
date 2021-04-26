package com.example.attendancenquizsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.attendancenquizsystem.Presenter.AddLecturePresenter;
import com.example.attendancenquizsystem.R;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class AddLecture extends AppCompatActivity {
    Button generate,upload;
    ImageView imageView;
    EditText title;
    EditText note;
    EditText lecturenumber;
    Bitmap bitmap;
    String y;
    Button uploadpdf;
    int x=0;
    Uri pdf;
    AddLecturePresenter mypresenter;
    final static int PICK_PDF_CODE = 1;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture);
        generate=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        title=findViewById(R.id.title);
        note=findViewById(R.id.note);
        lecturenumber=findViewById(R.id.lecturenumber);
        upload=findViewById(R.id.upload);
        uploadpdf=findViewById(R.id.uploadpdf);
        mypresenter= new AddLecturePresenter(this);

        uploadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_CODE);
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int width = point.x;
                int height = point.y;
                int smallerDimension = width < height ? width : height;
                smallerDimension = smallerDimension * 3 / 4;
                QRGEncoder qrgEncoder = new QRGEncoder(getIntent().getStringExtra("Subject")+"/"+lecturenumber.getText().toString(), null, QRGContents.Type.TEXT,smallerDimension);
                try {

                    bitmap = qrgEncoder.encodeAsBitmap();
                    imageView.setImageBitmap(bitmap);
                    boolean save;

                    try {
                        save = QRGSaver.save(savePath, title.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    x=1;
                } catch (WriterException e) {

                }
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x==1){
                    Log.d("subject", "onClick: "+getIntent().getStringExtra("Subject"));
                    mypresenter.addLecture(title.getText().toString(),note.getText().toString(),getIntent().getStringExtra("Subject"),lecturenumber.getText().toString(),savePath+title.getText().toString()+".jpg",pdf);
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                pdf=data.getData();
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
