package com.example.ungdungchplay.InterfaceManager;

import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public interface OderFragmentInterFace {
    void getDataSuccess(List<Service> list);
    void getDataError(String msg);
}
