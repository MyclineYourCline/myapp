package com.example.ungdungchplay.FragmentManager.OderFragment;

import static android.util.Log.d;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        d("ca" + "chung", "clickEdit: "+oder.toString());
        ServiceDAO serviceDAO = new ServiceDAO(getContext());
        Dialog dialog = new Dialog(getContext(),
                androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setContentView(R.layout.layout_update_detail_oder);
        TextView txt_name = dialog.findViewById(R.id.item_oder_update_txt_name);
        ImageView img_cancel = dialog.findViewById(R.id.item_oder_update_img_cancel);
        TextInputEditText edt_quantity = dialog.findViewById(R.id.item_oder_update_edt_quantity);
        TextInputEditText edt_price = dialog.findViewById(R.id.item_oder_update_edt_Price);
        TextInputEditText edt_total = dialog.findViewById(R.id.item_oder_update_edt_total);
        Button btn_delete = dialog.findViewById(R.id.item_oder_update_btn_delete);
        Button btn_save = dialog.findViewById(R.id.item_oder_update_btnSave);
        d("ca" + "chung", "clickEdit: "+oder.toString());
        Service service = serviceDAO.queryByID(oder.getServiceID());
        txt_name.setText(service.getName());
        String price = formatPrice(service.getPrice()+"");
        String total = formatPrice(oder.getTotalMoney(service)+"");
        edt_quantity.setText(oder.getQuantity()+"");
        edt_price.setText(price);
        edt_total.setText(total);
        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oder.setQuantity(Integer.parseInt(edt_quantity.getText().toString().trim()));
                presenter.updateOder(1,oder,oder.getStatus(),oder.getTableID());
                dialog.cancel();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oder.setQuantity(Integer.parseInt(edt_quantity.getText().toString().trim()));
                presenter.updateOder(0,oder,oder.getStatus(),oder.getTableID());
                dialog.cancel();
            }
        });
        edt_quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()){
                    edt_total.setText("0");
                    return;
                }
                String total = formatPrice(Integer.parseInt(removeCommas(edt_quantity.getText().toString()))
                        * service.getPrice()+"");
                edt_total.setText(total);
            }
        });
        dialog.show();
    }

    @Override
    public void getDataSuccess(String msg, List<Oder> list) {
        ServiceDAO serviceDAO = new ServiceDAO(getContext());
        adapter.setList(list);
        updateTotalMoney(list);
    }

    @Override
    public void getDataError(String msq) {
        txt_total.setText("0 Vnd");
    }

    @Override
    public void paymentSuccess(String msg) {

    }

    @Override
    public void PaymentError(String msg) {

    }

    @Override
    public void updateSuccess(String msg, List<Oder> list) {
        adapter.setList(list);
        updateTotalMoney(list);
        Toast.makeText(oderActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateError(String msg) {

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
    void updateTotalMoney (List<Oder> list){
        ServiceDAO serviceDAO = new ServiceDAO(getContext());
        int totalMoney = 0;
        String totalMoneyS = "0";
        for (Oder oder: list){
            Service service = serviceDAO.queryByID(oder.getServiceID());
            totalMoney += oder.getTotalMoney(service);
        }
        totalMoneyS = formatPrice(String.valueOf(totalMoney));
        txt_total.setText(totalMoneyS +" Vnd");
    }
    private static String removeCommas(String input) {
        // Loại bỏ dấu phẩy từ chuỗi
        String stringWithoutCommas = input.replaceAll(",", "");
        return stringWithoutCommas;
    }
}