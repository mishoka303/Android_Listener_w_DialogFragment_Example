package com.android.recyclerviewnewapp.Utilities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.recyclerviewnewapp.Activies.MainActivity;
import com.android.recyclerviewnewapp.Interfaces.DialogListener;
import com.android.recyclerviewnewapp.Models.Cat;
import com.android.recyclerviewnewapp.R;

import org.w3c.dom.Text;

public class FragmentCatDialog extends DialogFragment {
    TextView catName, catBreed, catStatus;
    Button startCat, backCat;
    DialogListener dlistener;
    Cat cat;
    int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cat_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        catName = view.findViewById(R.id.showName);
        catBreed = view.findViewById(R.id.showBreed);
        catStatus = view.findViewById(R.id.showStatus);
        startCat = view.findViewById(R.id.showStart);
        backCat = view.findViewById(R.id.showBack);

        catName.setText(cat.getName());
        catBreed.setText(cat.getBreed());
        catStatus.setText(cat.getStatus());

        startCat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dlistener.onStart(index);
                dismiss();
            }
        });
        backCat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dlistener = (DialogListener) context; //casted
    }

    public static FragmentCatDialog newInstance() {
        return new FragmentCatDialog();
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
