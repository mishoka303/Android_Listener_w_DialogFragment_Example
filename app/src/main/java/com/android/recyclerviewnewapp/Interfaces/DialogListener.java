package com.android.recyclerviewnewapp.Interfaces;

import com.android.recyclerviewnewapp.Models.Cat;

public interface DialogListener {
    void onFinish(Cat cat) ;
    void onStart(int index);
}
