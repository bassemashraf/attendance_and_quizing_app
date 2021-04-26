package com.example.attendancenquizsystem.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancenquizsystem.Activities.JoinQuiz;
import com.example.attendancenquizsystem.model.Lecture;
import com.example.attendancenquizsystem.R;

import java.util.ArrayList;

public class studentlecadapter extends RecyclerView.Adapter<studentlecadapter.studentholder> {
    ArrayList<Lecture>lectures;
    String subjcet;
    public studentlecadapter(ArrayList<Lecture>k,String subject){
        subjcet=subject;
        lectures=k;
    }

    @NonNull
    @Override
    public studentlecadapter.studentholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.studentrow,parent,false);
        studentholder myholder=new studentholder(v);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull studentlecadapter.studentholder holder, final int position) {
        holder.title.setText(lectures.get(position).getTitle());
        holder.downloadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(lectures.get(position).getPdf()));
                v.getContext().startActivity(intent);
            }
        });
        holder.joinquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), JoinQuiz.class);
                intent.putExtra("Subject",subjcet);
                intent.putExtra("title",lectures.get(position).getTitle());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lectures.size();
    }
    public static class studentholder extends RecyclerView.ViewHolder{
        TextView lecturenumber;
        TextView title;
        Button downloadpdf,joinquiz;
        public studentholder(@NonNull View itemView) {
            super(itemView);
            lecturenumber=itemView.findViewById(R.id.lecturenumber);
            title=itemView.findViewById(R.id.title);
            downloadpdf=itemView.findViewById(R.id.download);
            joinquiz=itemView.findViewById(R.id.joinquiz);
        }
    }
}
