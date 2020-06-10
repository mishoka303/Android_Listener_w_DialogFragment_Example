package com.android.recyclerviewnewapp.Activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements DialogListener, RecyclerListener {
    private static final String TAG = "MainActivity";

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
        adapter.onStartCreateRecords(); // suzdava nachalni 5 broiki Cat
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

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

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                int res = new Random().nextInt(3)+1;
                //Log.d("view.postDelayed","Ran once on case " + res);
                //Log.d("Traceable","The res value in Runnable is: " + res);

                switch(res) {
                    case 1:
                        cats.add(new Cat("Pisanka"+ new Random().nextInt(999), "Kotka", "Waiting"));
                        try { view.postDelayed(this, 300); } catch (Exception e) { e.printStackTrace(); }
                        break;
                    case 2:
                        int index = new Random().nextInt(cats.size());
                        cats.get(index).setStatus("Active");
                        adapter.notifyItemChanged(index);
                        try { view.postDelayed(this, 300); } catch (Exception e) { e.printStackTrace(); }
                        break;
                    case 3:
                        int index1 = new Random().nextInt(cats.size());
                        if (cats.get(index1).getStatus().equals("Active")) {
                            try {   //Log.d("case3","Case 3 was ran with random number " + index1 + " from maximum of " + (cats.size()-1));
                                view.removeViewAt(index1);
                                cats.remove(index1);
                                adapter.notifyItemRemoved(index1);
                                adapter.notifyDataSetChanged(); }
                            catch (Exception e) { e.printStackTrace(); }
                        }
                        else {
                            view.postDelayed(this, 300);
                            break;}
                        try { view.postDelayed(this, 300); } catch (Exception e) { e.printStackTrace(); }
                        break;
                    default:
                        view.postDelayed(this, 300);
                        break;
                }
            }
        }, 1000);
    //END OF MAIN ACTIVITY
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
        try {view.removeViewAt(position);
            cats.remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyDataSetChanged();}
        catch (Exception e) { e.printStackTrace(); }
    }
}
