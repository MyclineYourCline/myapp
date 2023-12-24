package com.example.ungdungchplay.FragmentManager.ScreenFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ungdungchplay.ActivityManager.LoginActivity;
import com.example.ungdungchplay.ActivityManager.MainActivity;
import com.example.ungdungchplay.ActivityManager.ScreenActivity;
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
    private void active(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ScreenActivity.SHARE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("active", true);
        editor.apply();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Screen3_btnStart:
                Intent intent = new Intent(getActivity(), MainActivity .class);
                getActivity().startActivity(intent);
                active();
                break;
            default:
                break;
        }
    }
}