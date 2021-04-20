package com.example.turing_login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Facultyadapter extends RecyclerView.Adapter<Facultyadapter.FacultyView> {

    List<String>titles = new ArrayList<>();
    List<Integer>images = new ArrayList<>();
    List<String>pos = new ArrayList<>();

    public Facultyadapter(List<Integer> images, List<String> titles, List<String> pos) {
        this.titles = titles;
        this.images = images;
        this.pos = pos;
    }

    @NonNull
    @Override
    public FacultyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fac_row_faculty,parent,false);
        return new FacultyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyView holder, int position) {
        holder.facultyimage.setImageResource(images.get(position));
        holder.facultyname.setText(titles.get(position));
        holder.facultyposition.setText(pos.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class FacultyView extends RecyclerView.ViewHolder{

        ImageView facultyimage;
        TextView facultyname,facultyposition;

        public FacultyView(@NonNull View itemView) {
            super(itemView);
            facultyimage=(ImageView)itemView.findViewById(R.id.image_faculty);
            facultyname=(TextView)itemView.findViewById(R.id.facultyName);
            facultyposition=(TextView)itemView.findViewById(R.id.facultyPosition);

        }
    }
}
