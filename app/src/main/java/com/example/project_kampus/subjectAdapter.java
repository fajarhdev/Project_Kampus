package com.example.project_kampus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class subjectAdapter extends RecyclerView.Adapter<subjectAdapter.viewHolder> implements Filterable {
    private ArrayList<Subject> list;
    private ArrayList<Subject> listSearch;
    private OnItemClickListener Listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        Listener = listener;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public ImageView Subject_Image;
        public TextView Subject;
        public TextView Subject_Desc;
        public TextView Subject_Desc2;

        public viewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            Subject = itemView.findViewById(R.id.subject_name);
            Subject_Desc = itemView.findViewById(R.id.subject_desc);
            Subject_Desc2 = itemView.findViewById(R.id.subject_desc2);
            Subject_Image = itemView.findViewById(R.id.subject_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
    public subjectAdapter(ArrayList<Subject>mlist) {
        list = mlist;
        listSearch = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject,parent,false);
        viewHolder vH = new viewHolder(v, Listener);
        return vH;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position){
        Subject currentItem = list.get(position);
        if(URLUtil.isValidUrl(currentItem.getSubject_pic())){
            Picasso.get().load(currentItem.getSubject_pic()).into(holder.Subject_Image);
        }
        else{
            holder.Subject_Image.setImageResource(R.drawable.mike);
        }
        holder.Subject.setText(currentItem.getSubject());
        holder.Subject_Desc.setText(currentItem.getSubject_Desc());
        holder.Subject_Desc2.setText(currentItem.getSubject_Desc2());
    }

    public int getItemCount(){ return list.size();}

    public Filter getFilter(){
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Subject> filtered = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                filtered.addAll(listSearch);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Subject item : listSearch){
                    if(item.getSubject().toLowerCase().contains(filterPattern)
                    || item.getSubject_Desc().toLowerCase().contains(filterPattern)){
                        filtered.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filtered;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list.clear();
            list.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
