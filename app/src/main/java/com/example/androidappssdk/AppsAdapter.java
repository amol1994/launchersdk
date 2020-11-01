package com.example.androidappssdk;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.InstalledApps;
import com.example.calculator.AppsProvider;

import java.util.ArrayList;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {
    ArrayList<InstalledApps> apps;
    Activity context;

    public AppsAdapter(ArrayList<InstalledApps> apps,Activity context) {
        this.context=context;
        this.apps = apps;
    }

    @NonNull
    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppsAdapter.ViewHolder holder, int position) {

        holder. appIcon.setImageDrawable(apps.get(position).getIcon());
        holder.appLabel.setText(apps.get(position).getLabel());

    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public void filterList(ArrayList<InstalledApps> filterdNames) {
        this.apps=filterdNames;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appIcon ;
              //  appIcon.setImageDrawable(apps.get(position).getIcon());

        TextView appLabel ;
                //appLabel.setText(apps.get(position).getLabel());
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appIcon = (ImageView)itemView.findViewById(R.id.item_app_icon);
            appLabel = (TextView)itemView.findViewById(R.id.item_app_label);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppsProvider.launchApp(context,apps.get(getAdapterPosition()).getPacakageName());

                }
            });

            //Long Press
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AppsProvider.unInstall(context,apps.get(getAdapterPosition()).getPacakageName());
                    apps.remove(getAdapterPosition());
                    notifyItemChanged(getAdapterPosition());
                    return false;
                }
            });
        }
    }
}
