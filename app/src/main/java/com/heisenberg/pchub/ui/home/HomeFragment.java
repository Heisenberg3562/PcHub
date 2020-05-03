package com.heisenberg.pchub.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.heisenberg.pchub.R;

public class HomeFragment extends Fragment {

    private homeListner listner;
    private TextView textView;

    public interface homeListner{
        void textBox1(String string);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        listner.textBox1(textView.getText().toString());
        return root;
    }

    public void setTextbox1(String string){
        textView.setText(string);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof homeListner){
            listner = (homeListner) context;
        }else {
            throw new RuntimeException(context.toString()
            + " must implement homeListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listner = null;
    }
}