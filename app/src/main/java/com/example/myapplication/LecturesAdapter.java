package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LecturesAdapter extends RecyclerView.Adapter<LecturesAdapter.ViewHolder> {

    public LecturesActivity activity;

    @Override
    public LecturesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lecture, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return activity.lectures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView published;

        public ViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.title);
            published = view.findViewById(R.id.published);

        }
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index) {

        viewHolder.title.setText(activity.lectures.get(index).title);
        viewHolder.published.setText(activity.lectures.get(index).published);

    }


}
