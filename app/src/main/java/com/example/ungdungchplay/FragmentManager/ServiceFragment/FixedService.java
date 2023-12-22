
package com.example.ungdungchplay.FragmentManager.ServiceFragment;

import static android.util.Log.d;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ungdungchplay.Adapter.ServiceAdapter;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.FragmentFixedServiceInterface;
import com.example.ungdungchplay.InterfaceManager.SendData.ServiceListener;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.Presenter.FragmentPresenter.FragmentFixedServicePresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class FixedService extends Fragment implements FragmentFixedServiceInterface, ServiceListener {
    private View view;
    private RecyclerView recyclerView;
    private TextInputEditText edt_search;
    private ServiceAdapter adapter;
    private FragmentFixedServicePresenter presenter;
    private List<Service> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fixed_service, container, false);

        unitIU();

        return  view;
    }
    void  unitIU (){
        adapter = new ServiceAdapter(getContext(), this);
        recyclerView = view.findViewById(R.id.FixedService_rcv);
        edt_search = view.findViewById(R.id.FixedService_edt_search);
        //
        adapter.setList(list);
        recyclerView.setAdapter(adapter);
        presenter = new FragmentFixedServicePresenter(getContext(),this);
        presenter.getDataForSqlite(0);
    }

    @Override
    public void dataExist(String msg, List<Service> list) {
        adapter.setList(list);
    }

    @Override
    public void dataError(String msg) {
        d("ca" + "chung", "dataError: not data 0");
    }

    @Override
    public void senData(int option, Service service) {
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getDataForSqlite(0);
    }
}