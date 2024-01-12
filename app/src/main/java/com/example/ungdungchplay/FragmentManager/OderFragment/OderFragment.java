package com.example.ungdungchplay.FragmentManager.OderFragment;

import static android.util.Log.d;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ungdungchplay.ActivityManager.OderActivity;
import com.example.ungdungchplay.Adapter.OderAdapter;
import com.example.ungdungchplay.Adapter.ServiceAdapter;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.OderFragmentInterFace;
import com.example.ungdungchplay.InterfaceManager.SendData.OderListener;
import com.example.ungdungchplay.InterfaceManager.SendData.ServiceListener;
import com.example.ungdungchplay.ModelManager.Oder;
import com.example.ungdungchplay.ModelManager.Service;
import com.example.ungdungchplay.Presenter.FragmentPresenter.OderFragmentPresenter;
import com.example.ungdungchplay.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class OderFragment extends Fragment implements View.OnClickListener, OderFragmentInterFace
, ServiceListener , OderListener {
    private TextView txt_detail;
    private RelativeLayout l_bts;
    private View view;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private BottomSheetBehavior bottomSheetBehavior;
    private OderFragmentPresenter presenter;
    private RecyclerView recyclerView_content, recyclerView_bts;
    private ServiceAdapter serviceAdapter;
    private Context context;
    private List<Service> list = new ArrayList<>();
    private List<Oder> listBts = new ArrayList<>();
    private OderAdapter oderAdapter;
    private TextView quantityOderItem;
    private Button btn_confirmOder;
    private Oder oder = null;
    private boolean checkOder = true;
    private int totalMoney;
    private OderActivity oderActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_oder, container, false);
        unitUI();
        presenter.getData();
        context = getContext().getApplicationContext();
        d("ca" + "chung", "onCreateView: ");
        //
        return view;
    }
    private void unitUI(){
        oderActivity = (OderActivity) getActivity();
        presenter = new OderFragmentPresenter(this,getContext());
        txt_detail = view.findViewById(R.id.Layout_bts_cart_txt_detail);
        l_bts = view.findViewById(R.id.Layout_bts_cart_l);
        bottomSheetBehavior = BottomSheetBehavior.from(l_bts);
        recyclerView_content = view.findViewById(R.id.Layout_bts_rcv);
        recyclerView_bts = view.findViewById(R.id.Layout_bts_cart_txt_rcv);
        quantityOderItem = view.findViewById(R.id.Layout_bts_cart_txt_quantity_oder);
        btn_confirmOder = view.findViewById(R.id.Layout_bts_cart_btn_total);

        txt_detail.setOnClickListener(this);
        serviceAdapter = new ServiceAdapter(getContext(),this);
        serviceAdapter.setList(list);
        recyclerView_content.setAdapter(serviceAdapter);
        oderAdapter = new OderAdapter(getContext(),this );
        btn_confirmOder.setOnClickListener(this);
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
    public void getDataSuccess(List<Service> list) {
        serviceAdapter.setList(list);
    }

    @Override
    public void getDataError(String msg) {

    }

    @Override
    public void oderSuccess(String msg, List<Oder> list) {

        showDiaLogSuccess(msg);

    }

    @Override
    public void oderError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void senData(int option, Service service) {
        if (option == 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Oder "+ service.getName()+" ?");
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Oder now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listBts.size() == 0){
                        oder = new Oder();
                        oder.setServiceID(service.getId());
                        oder.setTableID(oderActivity.getTable().getId());
                        oder.setQuantity(1);
                        listBts.add(oder);
                    }
                    else{
                        for (Oder oderCheck: listBts){
                            if (oderCheck.getServiceID() == service.getId()){
                                checkOder = false;
                                oderCheck.setQuantity(oderCheck.getQuantity()+1);
                                break;
                            }
                            else{
                                checkOder= true;
                            }
                        }
                        if (checkOder){
                            oder = new Oder();
                            oder.setServiceID(service.getId());
                            oder.setTableID(oderActivity.getTable().getId());
                            oder.setQuantity(1);
                            listBts.add(oder);
                        }
                    }
                    oderAdapter.setList(listBts);
                    recyclerView_bts.setAdapter(oderAdapter);
                    recyclerView_bts.setVisibility(View.VISIBLE);
                }
            });
            builder.show();

        }

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
            case R.id.Layout_bts_cart_btn_total:
                btn_confirmOderOnclick();
                break;
        }
    }

    private void btn_confirmOderOnclick() {
        OderActivity oderActivity = (OderActivity) getActivity();
        presenter.insertOder(listBts,oderActivity.getTable().getId());
    }

    private void btnMore(TextView txt_quantity, int price,TextView txt_total){
        int v = Integer.parseInt(txt_quantity.getText().toString().trim());
        v++;
        txt_quantity.setText(String.valueOf(v));
        long total = price * v;
        txt_total.setText("Total: "+formatPrice(String.valueOf(total)));
    }
    private void btnLess(TextView txt_quantity, int price,TextView txt_total){
        int v = Integer.parseInt(txt_quantity.getText().toString().trim());
        if (v == 0){
            return;
        }
        v--;
        txt_quantity.setText(String.valueOf(v));
        long total = price * v;
        txt_total.setText("Total: "+formatPrice(String.valueOf(total)));
    }

    @Override
    public void changeOder(int totalQuantity, int totalMoney) {
        quantityOderItem.setText("Table oder: "+totalQuantity+" Item");
        btn_confirmOder.setText("Confirm and Oder: "+ formatPrice(String.valueOf(totalMoney))+" Vnd");
        this.totalMoney = totalMoney;
    }

    @Override
    public void clickEdit(Oder oder) {

    }

    private String formatPrice(String price){
        NumberFormat numberFormat = NumberFormat.getInstance();
        double fDouble = Double.parseDouble(price);
        String fPrice = numberFormat.format(fDouble);
        return fPrice;
    }
    private void showDiaLogSuccess (String msg){
        Dialog dialog = new Dialog(getContext(),
                androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_success);

        TextView txt_msg = dialog.findViewById(R.id.dialog_success_txt_msg);
        txt_msg.setText(msg);
        Button btn_oce = dialog.findViewById(R.id.dialog_success_btn_oce);
        btn_oce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBts.clear();
                oderAdapter.setList(listBts);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                oderActivity.openDetailTab();
                dialog.cancel();
            }
        });
        dialog.show();

    }
}