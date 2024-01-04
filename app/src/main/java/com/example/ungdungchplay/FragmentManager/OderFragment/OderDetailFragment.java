package com.example.ungdungchplay.FragmentManager.OderFragment;

import static android.util.Log.d;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungchplay.ActivityManager.OderActivity;
import com.example.ungdungchplay.Adapter.OderDetailAdapter;
import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.OderDetailFragmentInterface;
import com.example.ungdungchplay.InterfaceManager.SendData.OderListener;
import com.example.ungdungchplay.ModelManager.Oder;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.Presenter.FragmentPresenter.OderDetailFragmentPresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class OderDetailFragment extends Fragment implements OderListener, OderDetailFragmentInterface{
    private View view;
    private RecyclerView rcv;
    private OderDetailAdapter adapter;
    private TextInputEditText edt_note;
    private TextView txt_total;
    private OderDetailFragmentPresenter presenter;
    private OderActivity oderActivity;
    private Button btn_pay;private List<Oder> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_oder_detail, container, false);
        unitUI();
        presenter.getData(-1,oderActivity.getTable().getId());
        return view;
    }
    private void unitUI (){
        oderActivity = (OderActivity) getActivity();
        presenter = new OderDetailFragmentPresenter(getContext(),this);
        rcv = view.findViewById(R.id.OderDetailFragment_rcv);
        edt_note = view.findViewById(R.id.OderDetailFragment_edt_note);
        txt_total = view.findViewById(R.id.OderDetailFragment_txt_totalPay);
        btn_pay = view.findViewById(R.id.OderDetailFragment_btn_pay);
        adapter = new OderDetailAdapter(getContext(), this);
        adapter.setList(list);
        rcv.setAdapter(adapter);
    }

    @Override
    public void changeOder(int totalQuantity, int totalMoney) {

    }

    @Override
    public void clickEdit(Oder oder) {

    }

    @Override
    public void getDataSuccess(String msg, List<Oder> list) {
        ServiceDAO serviceDAO = new ServiceDAO(getContext());
        adapter.setList(list);
        int totalMoney = 0;
        String totalMoneyS = "0";
        for (Oder oder: list){
            Service service = serviceDAO.queryByID(oder.getServiceID());
            totalMoney += oder.getTotalMoney(service);
        }
        totalMoneyS = formatPrice(String.valueOf(totalMoney));
        txt_total.setText(totalMoneyS +" Vnd");
    }

    @Override
    public void getDataError(String msq) {
        txt_total.setText("0 Vnd");
    }

    @Override
    public void payMenSuccess(String msg) {

    }

    @Override
    public void PayMenError(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData(-1,oderActivity.getTable().getId());
    }
    private String formatPrice(String price){
        NumberFormat numberFormat = NumberFormat.getInstance();
        double fDouble = Double.parseDouble(price);
        String fPrice = numberFormat.format(fDouble);
        return fPrice;
    }
}