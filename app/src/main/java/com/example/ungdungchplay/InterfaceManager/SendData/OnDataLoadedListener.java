package com.example.ungdungchplay.InterfaceManager.SendData;

import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public interface OnDataLoadedListener {
    void onDataLoaded(List<Service> list);
    void onError(String msg);
}
