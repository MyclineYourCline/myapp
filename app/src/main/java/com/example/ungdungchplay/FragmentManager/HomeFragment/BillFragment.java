package com.example.ungdungchplay.FragmentManager.HomeFragment;

import static android.util.Log.d;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ungdungchplay.Adapter.BillAdapter;
import com.example.ungdungchplay.Adapter.OderAdapter;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.FragmentBillInterface;
import com.example.ungdungchplay.ModelManager.Bill;
import com.example.ungdungchplay.Presenter.FragmentPresenter.FragmentBillPresenter;
import com.example.ungdungchplay.R;

import java.util.ArrayList;
import java.util.List;

public class BillFragment extends Fragment implements FragmentBillInterface {
    private RecyclerView bill_fragment_rcv;
    private BillAdapter adapter;
    private View view;
    private FragmentBillPresenter presenter;
    private List<Bill> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_active, container, false);
        unitUI();
        return view;
    }
    void unitUI (){
        presenter = new FragmentBillPresenter(getContext(),this);
        bill_fragment_rcv = view.findViewById(R.id.bill_fragment_rcv);
        adapter = new BillAdapter(getContext());
        adapter.setList(list);
        bill_fragment_rcv.setAdapter(adapter);
        presenter.getData();
    }

    @Override
    public void getDataSuccess(String msg, List<Bill> list) {
        adapter.setList(list);
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}