package com.example.edumatch.utils;

import android.view.LayoutInflater;
import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import androidx.recyclerview.widget.RecyclerView;

import com.example.edumatch.R;
import com.example.edumatch.retrofit.model.ProjectResponse;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<ProjectResponse> dataList;

    public ProjectAdapter(List<ProjectResponse> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProjectResponse projectResponse = dataList.get(position);
        holder.textViewTituloProyecto.setText(projectResponse.getName());
        holder.textViewDescripcionProyecto.setText(projectResponse.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTituloProyecto;
        public TextView textViewDescripcionProyecto;

        public ViewHolder(View view) {
            super(view);
            textViewTituloProyecto = view.findViewById(R.id.textViewTituloProyecto);
            textViewDescripcionProyecto = view.findViewById(R.id.textViewDescripcionProyecto);
        }
    }
}

