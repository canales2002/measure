package com.example.albert.measure.ui.results;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.albert.measure.R;
import com.example.albert.measure.activities.ResultsActivity;
import com.example.albert.measure.elements.Area;
import com.example.albert.measure.elements.ElementsLists;

import java.util.Locale;

public class AreasAdapter extends ElementsAdapter {

    private ViewHolder holder;

    AreasAdapter(ElementsLists elements, Context context) {
        super(elements, context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_areas, parent, false);
        holder = new ViewHolder(v);
        return holder;
    }

    @Override
    void onBindChildrenViewHolder(int position) {
        Area a = elements.getAreaList().get(position);
        double area = a.getArea();
        this.holder.textArea.setText(String.format(Locale.getDefault(), "%.2f %s²", adjustToUnit(area, 2),
                ((ResultsActivity) context).getActualUnit()));
    }

    @Override
    public int getItemCount() {
        return elements.getAreaList().size();
    }

    @Override
    void removeItem(int position) {
        elements.getAreaList().remove(position);
        ((ResultsActivity) context).setElements(elements);
        ((ResultsActivity) context).refreshAdapter(SectionsPagerAdapter.AREA_TAB);
    }

    @Override
    String getItemName(int position) {
        return elements.getAreaList().get(position).getName();
    }

    @Override
    void renameItem(int position, String name) {
        elements.getAreaList().get(position).setName(name);
        ((ResultsActivity) context).setElements(elements);
        ((ResultsActivity) context).refreshAdapter(SectionsPagerAdapter.AREA_TAB);
    }


    public static class ViewHolder extends ElementsAdapter.ViewHolder {
        final TextView textArea;

        ViewHolder(View v) {
            super(v);
            textArea = v.findViewById(R.id.area);
        }
    }
}
