package com.example.rsvpea;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleContentAdapter extends RecyclerView.Adapter<RecycleContentAdapter.ViewHolder> {
    Context context;
    ArrayList<TimelineCardContent> arrContent;
    RecycleContentAdapter(Context context, ArrayList<TimelineCardContent> arrContent){
        this.context=context;
        this.arrContent=arrContent;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.timeline_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimelineCardContent content = arrContent.get(position);
        //holder.img.setImageResource(arrContent.get(position).img);
        Picasso.get()
                .load(content.img)
                .into(holder.img);
        holder.title.setText(arrContent.get(position).title);
        holder.moreDetailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MoreDetails.class);
            intent.putExtra("title", content.title);
            intent.putExtra("img", content.img);
            intent.putExtra("eventModel",content.eventModel);
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return arrContent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView img;
        Button moreDetailsButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title=itemView.findViewById(R.id.title);
            this.img=itemView.findViewById(R.id.img);
            moreDetailsButton = itemView.findViewById(R.id.moreDetails);

        }
    }
}
