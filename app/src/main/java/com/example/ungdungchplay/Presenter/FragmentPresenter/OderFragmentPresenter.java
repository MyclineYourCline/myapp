package com.example.ungdungchplay.Presenter.FragmentPresenter;

import static android.util.Log.d;

import android.content.Context;

import com.example.ungdungchplay.Database.BillDAO;
import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.Database.OderDAO;
import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.OderFragmentInterFace;
import com.example.ungdungchplay.ModelManager.Bill;
import com.example.ungdungchplay.ModelManager.Oder;

import java.util.ArrayList;
import java.util.List;

public class OderFragmentPresenter {
    private OderFragmentInterFace oderFragmentInterFace;
    private Context context;
    private ServiceDAO serviceDAO;
    private OderDAO oderDAO;

    public OderFragmentPresenter(OderFragmentInterFace oderFragmentInterFace, Context context) {
        this.oderFragmentInterFace = oderFragmentInterFace;
        this.context = context;
        serviceDAO = new ServiceDAO(context);
        oderDAO = new OderDAO(context);
    }

    public void getData() {
        String sql = "SELECT * FROM service";
        serviceDAO.getAsync3(sql, oderFragmentInterFace);
    }

    public void insertOder(List<Oder> listOder, String tableID) {
        if (listOder.size() == 0 ){
            oderFragmentInterFace.oderError("Oder Error: list oder null !");
            return;
        }
        for (Oder x: listOder){
            Oder oder = oderDAO.queryByServiceIdAndTableIdAndStatus(x.getTableID(),-1
            ,x.getServiceID());
            if (oder == null){
                oderDAO.insertOder(x);
            }
            else{
                oder.setQuantity(x.getQuantity()+oder.getQuantity());
                oderDAO.updateOder(oder);
            }
        }
        oderFragmentInterFace.oderSuccess("Oder success !", new ArrayList<>());
    }
}
