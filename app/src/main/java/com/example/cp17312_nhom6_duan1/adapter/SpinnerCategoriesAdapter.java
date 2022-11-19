package com.example.cp17312_nhom6_duan1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cp17312_nhom6_duan1.R;
import com.example.cp17312_nhom6_duan1.dto.CategoriesDTO;

import java.util.ArrayList;

public class SpinnerCategoriesAdapter extends BaseAdapter {
    private ArrayList<CategoriesDTO> list = new ArrayList<>();
    private Context context;

    public SpinnerCategoriesAdapter(ArrayList<CategoriesDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if (view == null) {
            row = View.inflate(context, R.layout.item_sp_categories, null);
        } else {
            row = view;
        }

        CategoriesDTO categoriesDTO = list.get(i);

        TextView tvNameCategories = (TextView) row.findViewById(R.id.tvNameCategories);

        tvNameCategories.setText(categoriesDTO.getName());

        return row;
    }
}
