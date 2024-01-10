package com.example.ungdungchplay.InterfaceManager.FragmentInterface;

import com.example.ungdungchplay.ModelManager.Oder;
import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public interface OderFragmentInterFace {
    void getDataSuccess(List<Service> list);
    void getDataError(String msg);
    void oderSuccess (String msg, List<Oder> list);
    void  oderError(String msg);

}
