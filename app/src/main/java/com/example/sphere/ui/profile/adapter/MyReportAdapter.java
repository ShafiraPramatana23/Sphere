package com.example.sphere.ui.profile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sphere.R;
import com.example.sphere.ui.profile.model.MyReportList;

import java.util.ArrayList;

public class MyReportAdapter extends RecyclerView.Adapter<MyReportAdapter.UserViewHolder> {
    private ArrayList<MyReportList> dataList;
    private Context context;

    public MyReportAdapter(ArrayList<MyReportList> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_myreport, parent, false);
        context = parent.getContext();
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
//        holder.tvTitle.setText(dataList.get(position).getTitle());
//        holder.tvAuthor.setText("Penulis : " + dataList.get(position).getAuthor());
//        holder.tvSinopsis.setText(dataList.get(position).getSinopsis());
//        holder.iv.setImageResource(dataList.get(position).getImage());
//        holder.cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(context, DetailActivity.class);
//                myIntent.putExtra("title", dataList.get(position).getTitle());
//                myIntent.putExtra("author", dataList.get(position).getAuthor());
//                myIntent.putExtra("sinopsis", dataList.get(position).getSinopsis());
//                myIntent.putExtra("img", dataList.get(position).getImage());
//                context.startActivity(myIntent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvAuthor, tvSinopsis;
        private ImageView iv;
        private CardView cv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

//            tvTitle = itemView.findViewById(R.id.txt_title);
//            tvAuthor = itemView.findViewById(R.id.txt_author);
//            tvSinopsis = itemView.findViewById(R.id.txt_sinopsis);
//            iv = itemView.findViewById(R.id.iv);
//            cv = itemView.findViewById(R.id.cardView);
        }
    }
}
