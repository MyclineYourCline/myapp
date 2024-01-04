package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.BillDAO;
import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.OderFragmentInterFace;
import com.example.ungdungchplay.ModelManager.Bill;
import com.example.ungdungchplay.ModelManager.Oder;

import java.util.List;

public class OderFragmentPresenter {
    private OderFragmentInterFace oderFragmentInterFace;
    private Context context;
    private ServiceDAO serviceDAO;
    private BillDAO billDAO;

    public OderFragmentPresenter(OderFragmentInterFace oderFragmentInterFace, Context context) {
        this.oderFragmentInterFace = oderFragmentInterFace;
        this.context = context;
        serviceDAO = new ServiceDAO(context);
        billDAO = new BillDAO(context);
    }

    public void getData() {
        String sql = "SELECT * FROM service";
        serviceDAO.getAsync3(sql, oderFragmentInterFace);
    }

    public void insertBill(List<Oder> listOder,int totalMoney) {
        if (listOder.size() == 0 ){
            oderFragmentInterFace.oderError("Oder Error: list oder null !");
            return;
        }
        boolean checkI = true;
        for (Oder oder : listOder) {
            Bill bill = new Bill();
            bill.setTableID(oder.getTableID());
            bill.setStatus(-1);
            bill.setTotalMoney(totalMoney);
            bill.setOderID(oder.getOderId());
            long checkIn = billDAO.insertBill(bill);
            if (checkIn != -1) {
                checkI = true;
            } else {
                checkI = false;
            }
        }
        if (checkI) oderFragmentInterFace.oderSuccess("Oder Success !");
        else  oderFragmentInterFace.oderError("Oder Error !");
    }
}
