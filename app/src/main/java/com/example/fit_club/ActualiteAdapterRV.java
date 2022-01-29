package com.example.fit_club;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fit_club.Actualite;
import com.example.fit_club.R;

import java.util.ArrayList;
import java.util.List;

public class ActualiteAdapterRV extends RecyclerView.Adapter<ActualiteAdapterRV.ViewHolder> {

    private List<Actualite> List;
    private List<Actualite> ListFull;

    public ActualiteAdapterRV(List<Actualite> List) {
        this.List = List;
        ListFull = new ArrayList<>(List);
    }

    public Filter getFilter() {
        return filter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Actualite currentItem = List.get(position);
        holder.titre.setText(currentItem.getTitre());
        holder.date.setText(currentItem.getDate());
        holder.description.setText(currentItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titre;
        TextView date;
        TextView description;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            titre = (TextView)itemView.findViewById(R.id.item_actualite_intitule);
            date = (TextView) itemView.findViewById(R.id.item_actualite_date);
            description = (TextView) itemView.findViewById(R.id.item_actualite_description);
            icon = (ImageView) itemView.findViewById(R.id.item_actualite_image);
        }


    }

    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Actualite> filterlist=new ArrayList<>();
            if(constraint==null|| constraint.length()==0){
                filterlist.addAll(ListFull);
            }
            else{
                String pattrn=constraint.toString().toLowerCase().trim();
                for(Actualite act :ListFull){
                    if(act.getDate().toLowerCase().contains(pattrn)){
                        filterlist.add(act);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filterlist;
            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List.clear();
            List.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };




}
