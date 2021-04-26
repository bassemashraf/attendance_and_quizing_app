package com.example.attendancenquizsystem.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancenquizsystem.Activities.TeacherLectures;
import com.example.attendancenquizsystem.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class coursesadapter extends RecyclerView.Adapter<coursesadapter.courseholder> {
    ArrayList<String>mycourses;
    String myrole;
    public coursesadapter(ArrayList<String> courses,String role){
        mycourses=new ArrayList<>();
        mycourses=courses;
        myrole=role;
    }
    @NonNull
    @Override
    public courseholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        courseholder myholder=new courseholder(v);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull courseholder holder, final int position) {
        holder.mycourse.setText(mycourses.get(position));
        if(myrole.equals("Teacher")){
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(v.getContext(), TeacherLectures.class);
                    intent.putExtra("Subject",mycourses.get(position));
                    v.getContext().startActivity(intent);

                }
            });
        }
        else{
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Student", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mycourses.size();
    }

    public static class courseholder extends RecyclerView.ViewHolder{
        ConstraintLayout constraintLayout;
        TextView mycourse;
        public courseholder(@NonNull View itemView) {
            super(itemView);
            mycourse=itemView.findViewById(R.id.course_name);
            constraintLayout=itemView.findViewById(R.id.myconstraint);
        }
    }
}
