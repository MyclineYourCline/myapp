package com.example.ungdungchplay.FragmentManager.ServiceFragment;

import static android.util.Log.d;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ungdungchplay.Adapter.ServiceAdapter;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.NotFixedServiceInterface;
import com.example.ungdungchplay.InterfaceManager.SendData.ServiceListener;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.Presenter.FragmentPresenter.NotFixedServicePresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class NotFixedService extends Fragment implements ServiceListener, NotFixedServiceInterface {
    private View view;
    private RecyclerView rcv;
    private TextInputEditText edt_search;
    private List<Service> list = new ArrayList<>();
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
        serviceAdapter.setList(list);
        notFixedServicePresenter.getData(1);
        rcv.setAdapter(serviceAdapter);
    }

    @Override
    public void senData(int option, Service service) {

    }

    @Override
    public void dataExists(List<Service> list) {
       serviceAdapter.setList(list);
       serviceAdapter.notifyDataSetChanged();
    }

    @Override
    public void dataError(String msg) {
        d("ca" + "chungs", "dataError: "+msg);
    }

    @Override
    public void onResume() {
        super.onResume();
        notFixedServicePresenter.getData(1);
    }
}