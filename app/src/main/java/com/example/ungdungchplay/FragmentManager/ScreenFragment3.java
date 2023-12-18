package com.example.ungdungchplay.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ungdungchplay.ActivityManager.LoginActivity;
import com.example.ungdungchplay.R;


public class ScreenFragment3 extends Fragment implements View.OnClickListener {
    private View view;
    private Button btn_start;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_screen3, container, false);
        //
        unitUI();
        return view;
    }
    void unitUI(){
        btn_start =  view.findViewById(R.id.Screen3_btnStart);
        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Screen3_btnStart:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }
}