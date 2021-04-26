package com.example.attendancenquizsystem.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancenquizsystem.Activities.AddQuiz;
import com.example.attendancenquizsystem.model.Lecture;
import com.example.attendancenquizsystem.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class lectureadapter extends RecyclerView.Adapter<lectureadapter.lectureholder> {
    ArrayList<Lecture>lectures;
    String course;
    public lectureadapter(ArrayList<Lecture>l,String course){
        lectures=l;
        this.course=course;
    }

    @NonNull
    @Override
    public lectureadapter.lectureholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherrow,parent,false);
        lectureadapter.lectureholder myholder=new lectureadapter.lectureholder(v);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull lectureadapter.lectureholder holder, final int position) {
        holder.title.setText(lectures.get(position).getTitle());
        holder.attendance.setText("Attendance: "+String.valueOf(lectures.get(position).getAttendance()));
        Picasso.get().load(lectures.get(position).getQR()).into(holder.QR);
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addquiz= new Intent(v.getContext(), AddQuiz.class);
                addquiz.putExtra("Subject",course);
                addquiz.putExtra("title",lectures.get(position).getTitle());
                v.getContext().startActivity(addquiz);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lectures.size();
    }
    public static class lectureholder extends RecyclerView.ViewHolder{
        TextView attendance;
        TextView title;
        ImageView QR;
        Button b;
        public lectureholder(@NonNull View itemView) {
            super(itemView);
            attendance=itemView.findViewById(R.id.attendance);
            title=itemView.findViewById(R.id.title);
            QR=itemView.findViewById(R.id.QR);
            b=itemView.findViewById(R.id.addquiz);
        }
    }
}
