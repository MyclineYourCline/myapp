
package com.example.ungdungchplay.FragmentManager.ServiceFragment;

import static android.util.Log.d;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EdgeEffect;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungchplay.Adapter.ServiceAdapter;
import com.example.ungdungchplay.InterfaceManager.SendData.OnDataLoadedListener;
import com.example.ungdungchplay.InterfaceManager.SendData.ServiceListener;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.Presenter.FragmentPresenter.FragmentFixedServicePresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class FixedService extends Fragment implements ServiceListener, OnDataLoadedListener , View.OnClickListener{
    private View view;
    private RecyclerView recyclerView;
    private ServiceAdapter adapter;
    private TextInputEditText edy_query;
    private FragmentFixedServicePresenter presenter;
    private List<Service> listA = new ArrayList<>();
    private final static int TYPE = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fixed_service, container, false);
        unitIU();
        return  view;
    }
    private void unitIU() {
        adapter = new ServiceAdapter(getContext(), this);
        recyclerView = view.findViewById(R.id.FixedService_rcv);
        adapter.setList(listA);
        recyclerView.setAdapter(adapter);
        presenter = new FragmentFixedServicePresenter(getContext(), this);
        presenter.LoadData2(String.valueOf(TYPE));
        edy_query = view.findViewById(R.id.FixedService_edt_search);
        startQuery();
    }

    private void startQuery() {
        edy_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setListAnimation();
                adapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setListAnimation();
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                setListAnimation();
                adapter.getFilter().filter(s);
            }
        });
    }

    @Override
    public void senData(int option, Service service) {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.LoadData2(String.valueOf(0));
    }

    @Override
    public void onDataLoaded(List<Service> list) {
        setListAnimation();
        adapter.setList(list);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }
    void setListAnimation (){
        LayoutAnimationController layoutAnimationController =
                AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_item_lef_to_right);
        recyclerView.setLayoutAnimation(layoutAnimationController);
    }
}