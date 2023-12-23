
package com.example.ungdungchplay.FragmentManager.ServiceFragment;

import static android.util.Log.d;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ungdungchplay.Adapter.ServiceAdapter;
import com.example.ungdungchplay.InterfaceManager.SendData.OnDataLoadedListener;
import com.example.ungdungchplay.InterfaceManager.SendData.ServiceListener;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.Presenter.FragmentPresenter.FragmentFixedServicePresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class FixedService extends Fragment implements ServiceListener, OnDataLoadedListener {
    private View view;
    private RecyclerView recyclerView;
    private TextInputEditText edt_search;
    private ServiceAdapter adapter;
    private FragmentFixedServicePresenter presenter;
    private List<Service> listA = new ArrayList<>();
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
        edt_search = view.findViewById(R.id.FixedService_edt_search);
        recyclerView.setAdapter(adapter);
        presenter = new FragmentFixedServicePresenter(getContext(), this);
        presenter.LoadData2(String.valueOf(0));
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
        listA.clear();
        listA.addAll(list);
        adapter.setList(listA);
    }

    @Override
    public void onError(String msg) {

    }
}