package com.example.hoangtangkhai_ktra2_bai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangtangkhai_ktra2_bai2.model.Course;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.CourseViewHolder> {
    private List<Course> listCourse;
    private Context context;

    public RecycleViewAdapter(Context context) {
        this.context = context;
        this.listCourse = new ArrayList<>();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cource_cardview,parent,false);
        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course o = listCourse.get(position);
        holder.tvCardId.setText(o.getId()+"");
        holder.tvCardName.setText(o.getName());
        holder.tvCardDate.setText(o.getDate());
        holder.tvCardNganh.setText(o.getNganh());

        if (o.getKichhoat()==1){
            holder.checkBox.setChecked(true);
        }
        else{
            holder.checkBox.setChecked(false);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Remove_UpdateActivity.class);
                Course o = listCourse.get(position);
                i.putExtra("course",o);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCourse.size();
    }

    public void setListCourse(List<Course> list){
        this.listCourse = list;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCardId, tvCardName, tvCardNganh, tvCardDate;
        private CheckBox checkBox;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardId = itemView.findViewById(R.id.txtid);
            tvCardName = itemView.findViewById(R.id.txtname);
            tvCardNganh = itemView.findViewById(R.id.txtnganh);
            tvCardDate = itemView.findViewById(R.id.txtdate);
            checkBox = itemView.findViewById(R.id.check);
        }
    }
}
