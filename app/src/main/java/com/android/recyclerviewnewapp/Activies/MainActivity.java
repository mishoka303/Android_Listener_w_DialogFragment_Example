package com.android.recyclerviewnewapp.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.recyclerviewnewapp.Interfaces.DialogListener;
import com.android.recyclerviewnewapp.Interfaces.RecyclerListener;
import com.android.recyclerviewnewapp.Models.Cat;
import com.android.recyclerviewnewapp.R;
import com.android.recyclerviewnewapp.Utilities.FragmentCatDialog;
import com.android.recyclerviewnewapp.Utilities.FragmentDialog;
import com.android.recyclerviewnewapp.Utilities.ViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DialogListener, RecyclerListener {
    private static final String TAG = "MainActivity";
    final String cat_key = "Cat";

    RecyclerView view;
    ViewAdapter adapter;
    FloatingActionButton button;
    ArrayList<Cat> cats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        view = findViewById(R.id.mRecyclerVIew);
        adapter = new ViewAdapter(cats, this);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

        cats.add(new Cat("Pisanka1","Pisana", "Waiting"));
        cats.add(new Cat("Pisanka2","Pisana", "Waiting"));
        cats.add(new Cat("Pisanka3","Pisana", "Waiting"));
        cats.add(new Cat("Pisanka4","Pisana", "Waiting"));
        cats.add(new Cat("Pisanka5","Pisana", "Waiting"));

        //FRAGMENT DIALOG FROM HERE
        button = findViewById(R.id.mAddButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Opening add dialog...", Toast.LENGTH_SHORT).show();
                FragmentManager fmanager = getSupportFragmentManager();
                FragmentDialog fragdialog = FragmentDialog.newInstance();
                fragdialog.show(fmanager, "AddCats");
            }
        });

    }

    @Override
    public void onFinish(Cat cat) {
        cats.add(cat);
        adapter.notifyItemChanged(adapter.getItemCount());
    }

    @Override
    public void onStart(int index) {
        cats.get(index).setStatus("Active");
        Log.d("Debug", "Data: " + cats.get(index).getStatus());
        adapter.notifyItemChanged(index);
    }

    @Override
    public void onRecyclerItemClicked(int position) {
        Log.d(TAG, "onRecyclerItemClicked: clicked on " + position + " position.");
        Toast.makeText(getApplicationContext(), "Opening chosen cat...", Toast.LENGTH_SHORT).show();
        FragmentManager fmanager = getSupportFragmentManager();
        FragmentCatDialog fragdialog = FragmentCatDialog.newInstance();
        fragdialog.setCat(cats.get(position));
        fragdialog.setIndex(position);
        fragdialog.show(fmanager, "ShowCats");
    }

    @Override
    public void onFinishButtonClicked(int position) {
        cats.remove(position);
        view.removeViewAt(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyDataSetChanged();
    }
}
