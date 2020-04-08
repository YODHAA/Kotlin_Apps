package com.example.recycler_component;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {

    private String[] data;

    public ProgrammingAdapter(String[] data){
         this.data=data;         // text data we got to show in Recycler
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View view = inflator.inflate(R.layout.list_item_layout,parent,false);

        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

        String title=data[position];
        holder.txtTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{

        ImageView imgIcon ;
        TextView txtTitle;
        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon=itemView.findViewById(R.id.imgIcon);
            txtTitle=itemView.findViewById(R.id.txtTitle);

        }
    }

}
