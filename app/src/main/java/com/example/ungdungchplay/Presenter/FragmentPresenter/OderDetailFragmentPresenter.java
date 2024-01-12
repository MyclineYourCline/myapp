package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.BillDAO;
import com.example.ungdungchplay.Database.OderDAO;
import com.example.ungdungchplay.Database.TableDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.OderDetailFragmentInterface;
import com.example.ungdungchplay.ModelManager.Bill;
import com.example.ungdungchplay.ModelManager.Oder;
import com.example.ungdungchplay.ModelManager.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OderDetailFragmentPresenter {
    private Context context;
    private OderDAO oderDAO;
    private BillDAO billDAO;
    private TableDAO tableDAO;
    private OderDetailFragmentInterface oderDetailFragmentInterface;

    public OderDetailFragmentPresenter(Context context, OderDetailFragmentInterface oderDetailFragmentInterface) {
        this.context = context;
        this.oderDetailFragmentInterface = oderDetailFragmentInterface;
        oderDAO = new OderDAO(context);
        billDAO = new BillDAO(context);
        tableDAO = new TableDAO(context);
    }
    public void getData(int Status, String tableID){
        List<Oder> list = oderDAO.queryByStatusAndTableID(Status,tableID);
        if (list.size() == 0){
            oderDetailFragmentInterface.getDataError("No item Oder");
            return;
        }
        oderDetailFragmentInterface.getDataSuccess("get Data success", list);
    }
    public void updateOder (int option,Oder oder,int Status, String tableID){
        if (option ==1){
            try {
                oderDAO.updateOder(oder);
                List<Oder> list = oderDAO.queryByStatusAndTableID(Status,tableID);
                oderDetailFragmentInterface.updateSuccess("Update Success !",list);
            }
            catch (Exception x){
                oderDetailFragmentInterface.updateError(x.toString());
            }
        }
        else{
            try{
                oderDAO.deleteOder(oder);
                List<Oder> list = oderDAO.queryByStatusAndTableID(Status,tableID);
                oderDetailFragmentInterface.updateSuccess("Delete Success !",list);
            }
            catch (Exception x){
                oderDetailFragmentInterface.updateError(x.toString());
            }
        }
    }
    public void checkOut (List<Bill> list,String tableID){
        if (list.size() == 0){
            oderDetailFragmentInterface.PaymentError("No data !");
            return;
        }
            for (Bill x: list){
                try {
                    Oder oder = oderDAO.getByID(String.valueOf(x.getOderID()));
                    oder.setStatus(1);
                    oderDAO.updateOder(oder);
                    billDAO.insertBill(x);

                }
                catch (Exception ex){
                    oderDetailFragmentInterface.PaymentError("Check Out Error");
                }
            }
            Table table = tableDAO.getByID(tableID);
            table.setStatus(0);
            tableDAO.updateTable(table);
            oderDetailFragmentInterface.paymentSuccess("Check Out Success");
    }
}
