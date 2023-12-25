package com.example.ungdungchplay.FragmentManager.ServiceFragment;

import static android.util.Log.d;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.ungdungchplay.Adapter.ServiceAdapter;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.NotFixedServiceInterface;
import com.example.ungdungchplay.InterfaceManager.SendData.OnDataLoadedListener;
import com.example.ungdungchplay.InterfaceManager.SendData.ServiceListener;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.Presenter.FragmentPresenter.NotFixedServicePresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class NotFixedService extends Fragment implements ServiceListener, OnDataLoadedListener {
    private View view;
    private RecyclerView rcv;
    private TextInputEditText edt_search;
    private List<Service> listA = new ArrayList<>();
    private ServiceAdapter serviceAdapter;
    private NotFixedServicePresenter notFixedServicePresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_not_fixed_service, container, false);
        unitUi();
        return view;
    }
    void unitUi(){
        notFixedServicePresenter = new NotFixedServicePresenter(getContext(),this);
        rcv = view.findViewById(R.id.NotFixedService_rcv);
        edt_search = view.findViewById(R.id.NotFixedService_edt_search);
        serviceAdapter =  new ServiceAdapter(getContext(),this);
        serviceAdapter.setList(listA);
//        notFixedServicePresenter.getData(1);
        notFixedServicePresenter.getData2(String.valueOf(1));
        rcv.setAdapter(serviceAdapter);
        startQuery();
    }

    @Override
    public void senData(int option, Service service) {

    }

    @Override
    public void onResume() {
        super.onResume();
        notFixedServicePresenter.getData2(String.valueOf(1));
    }

    @Override
    public void onDataLoaded(List<Service> list) {
        setListAnimation();
        serviceAdapter.setList(list);
    }

    @Override
    public void onError(String msg) {

    }
    private void startQuery() {
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setListAnimation();
                serviceAdapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setListAnimation();
                serviceAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                setListAnimation();
                serviceAdapter.getFilter().filter(s);
            }

        });
    }
    private void setListAnimation() {
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(
                getContext(),R.anim.layout_item_right_to_left);
        rcv.setLayoutAnimation(layoutAnimationController);
    }
}