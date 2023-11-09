package com.example.rsvpea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MybookingContentAdapter extends RecyclerView.Adapter<MybookingContentAdapter.ViewHolder> {
    Context context;
    ArrayList<MyBookingsTabContent> arrContent;
    MybookingContentAdapter(Context context, ArrayList<MyBookingsTabContent> arrContent){
        this.context=context;
        this.arrContent=arrContent;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1= LayoutInflater.from(context).inflate(R.layout.mybookingscontent, parent, false);
        return new ViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyBookingsTabContent content = arrContent.get(position);
        Picasso.get()
                .load(content.img1)
                .into(holder.img1);
        holder.title1.setText(arrContent.get(position).title1);
        holder.datetime.setText(arrContent.get(position).datetime);
        holder.venue.setText(arrContent.get(position).venue);

    }

    @Override
    public int getItemCount() {
        return arrContent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title1;
        ImageView img1;

        TextView datetime;
        TextView venue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title1=itemView.findViewById(R.id.title1);
            this.img1=itemView.findViewById(R.id.img1);
            this.datetime=itemView.findViewById(R.id.date);
            this.venue=itemView.findViewById(R.id.venue);

        }
    }
}
