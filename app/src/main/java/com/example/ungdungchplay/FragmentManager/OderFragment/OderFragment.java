package com.example.ungdungchplay.FragmentManager.OderFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ungdungchplay.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class OderFragment extends Fragment implements View.OnClickListener{
    private TextView txt_detail;
    private LinearLayout l_bts;
    private View view;
    private BottomSheetBehavior bottomSheetBehavior;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_oder, container, false);
        unitUI();
        return view;
    }
    private void unitUI(){
        txt_detail = view.findViewById(R.id.Layout_bts_cart_txt_detail);
        l_bts = view.findViewById(R.id.Layout_bts_cart_l);
        bottomSheetBehavior = BottomSheetBehavior.from(l_bts);
        txt_detail.setOnClickListener(this);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_EXPANDED:
                        txt_detail.setText("Hidden");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        txt_detail.setText("Detail");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Layout_bts_cart_txt_detail:
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
        }
    }
}