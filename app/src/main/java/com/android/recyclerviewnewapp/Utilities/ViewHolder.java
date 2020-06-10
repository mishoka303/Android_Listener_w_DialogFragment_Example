package com.android.recyclerviewnewapp.Utilities;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.recyclerviewnewapp.Interfaces.RecyclerListener;
import com.android.recyclerviewnewapp.R;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView name, breed, status;
    Button button;
    ConstraintLayout card;
    RecyclerListener RecyclerListener;

    public ViewHolder(@NonNull final View itemView, final RecyclerListener RecyclerListener1) {
        super(itemView);

        name = itemView.findViewById(R.id.catName);
        breed = itemView.findViewById(R.id.catBreed);
        status = itemView.findViewById(R.id.catStatus);
        card = itemView.findViewById(R.id.catCard);
        button = itemView.findViewById(R.id.catButton);

        becomeInactive();

        this.RecyclerListener = RecyclerListener1;
        itemView.setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerListener.onFinishButtonClicked(getAdapterPosition());
            }
        });
    }

    @Override
    public void onClick(View v) {
        RecyclerListener.onRecyclerItemClicked(getAdapterPosition());
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setBreed(String breed) {
        this.breed.setText(breed);
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }

    public void becomeInactive() {
        card.setBackgroundColor(Color.parseColor("#E0E0E0")); //sets initial background
        button.setVisibility(View.INVISIBLE);
    }

    public void becomeActive() {
        this.card.setBackgroundColor(Color.parseColor("#FFFF00"));
        button.setVisibility(View.VISIBLE);
    }
}
