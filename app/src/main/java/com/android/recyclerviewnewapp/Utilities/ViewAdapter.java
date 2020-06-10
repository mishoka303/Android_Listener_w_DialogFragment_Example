package com.android.recyclerviewnewapp.Utilities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.recyclerviewnewapp.Interfaces.RecyclerListener;
import com.android.recyclerviewnewapp.Models.Cat;
import com.android.recyclerviewnewapp.R;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<Cat> cats = new ArrayList<>();
    RecyclerListener mRecycleViewListener;

    public ViewAdapter(ArrayList<Cat> cats, RecyclerListener mOnRecycleViewListener) {
        this.cats = cats;
        this.mRecycleViewListener = mOnRecycleViewListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // Vzemame sustoqnieto na parent
        LayoutInflater inflater = LayoutInflater.from(context); // Da mojem da chetem ot XLM-a(pravi vruzkata)
        View view = inflater.inflate(R.layout.cat_element, parent, false);
        Log.d("ViewAdapter", "onCreateViewHolder: invoked.");

        return new ViewHolder(view, mRecycleViewListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cat cat1 = cats.get(position);
        holder.setName(cat1.getName());
        holder.setBreed(cat1.getBreed());
        holder.setStatus(cat1.getStatus());

        if (cat1.getStatus().equals("Active")) {
            holder.becomeActive();
        }
        else {
            holder.becomeInactive();
        }
        Log.d("ViewAdapter", "onBindViewHolder: invoked.");
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }
}
