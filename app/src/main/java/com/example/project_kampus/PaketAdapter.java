package com.example.project_kampus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PaketAdapter extends RecyclerView.Adapter<PaketAdapter.viewHolder> {
    private ArrayList<paket_Item> list;
    private OnItemClickListener Listener;

    public PaketAdapter(ArrayList<paket_Item> mlist) {
        list = mlist;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        Listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.paket, parent, false);
        viewHolder vH = new viewHolder(v, Listener);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        paket_Item currentItem = list.get(position);

        if (URLUtil.isValidUrl(currentItem.getMpic())) {
            Picasso.get().load(currentItem.getMpic()).into(holder.pic);
        } else {
            holder.pic.setImageResource(R.drawable.mike);
        }
        holder.namaPaket.setText(currentItem.getMname());
        holder.desc.setText(currentItem.getMdesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        public TextView namaPaket;
        public TextView desc;
        public ImageView pic;

        public viewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            namaPaket = itemView.findViewById(R.id.paket_name);
            desc = itemView.findViewById(R.id.paket_desc);
            pic = itemView.findViewById(R.id.paket_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
