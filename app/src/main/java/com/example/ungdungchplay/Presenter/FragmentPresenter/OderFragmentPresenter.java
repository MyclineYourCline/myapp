package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.OderFragmentInterFace;

public class OderFragmentPresenter {
    private OderFragmentInterFace oderFragmentInterFace;
    private Context context;
    private ServiceDAO serviceDAO;

    public OderFragmentPresenter(OderFragmentInterFace oderFragmentInterFace, Context context) {
        this.oderFragmentInterFace = oderFragmentInterFace;
        this.context = context;
        serviceDAO = new ServiceDAO(context);
    }
    public void getData (){
        String sql = "SELECT * FROM service";
        serviceDAO.getAsync3(sql,oderFragmentInterFace);
    }
}
