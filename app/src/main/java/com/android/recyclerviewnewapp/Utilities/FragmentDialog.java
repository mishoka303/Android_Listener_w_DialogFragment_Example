package com.android.recyclerviewnewapp.Utilities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.recyclerviewnewapp.Interfaces.DialogListener;
import com.android.recyclerviewnewapp.Models.Cat;
import com.android.recyclerviewnewapp.R;

public class FragmentDialog extends DialogFragment implements View.OnClickListener {
    EditText catName, catBreed, catStatus;
    Button addCat;
    DialogListener dlistener;

    @Override
    public void onClick(View v) {
        Cat cat = new Cat(catName.getText().toString(), catBreed.getText().toString(), catStatus.getText().toString());
        dlistener.onFinish(cat);
        dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cat_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        catName = view.findViewById(R.id.addName);
        catBreed = view.findViewById(R.id.addBreed);
        catStatus = view.findViewById(R.id.addStatus);
        addCat = view.findViewById(R.id.addButton);

        addCat.setOnClickListener(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dlistener = (DialogListener) context; //casted
    }

    public static FragmentDialog newInstance() {
        return new FragmentDialog();
    }
}
