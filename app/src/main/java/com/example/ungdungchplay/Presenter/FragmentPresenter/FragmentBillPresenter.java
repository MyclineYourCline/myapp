package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.BillDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.FragmentBillInterface;
import com.example.ungdungchplay.ModelManager.Bill;

import java.util.List;

public class FragmentBillPresenter {
    private  FragmentBillInterface fragmentBillInterface;
    private Context context;
    private BillDAO billDAO;

    public FragmentBillPresenter(Context context ,FragmentBillInterface fragmentBillInterface) {
        this.context = context;
        this.fragmentBillInterface = fragmentBillInterface;
        billDAO = new BillDAO(context);
    }
    public void getData (){
        try{
            List<Bill> list = billDAO.getAll();
            fragmentBillInterface.getDataSuccess("get data Success", list);
        }
        catch (Exception e){
            fragmentBillInterface.getDataError("get data Error");
        }
    }
}
