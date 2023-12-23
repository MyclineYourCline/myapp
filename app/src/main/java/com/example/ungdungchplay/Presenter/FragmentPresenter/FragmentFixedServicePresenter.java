package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.FragmentFixedServiceInterface;
import com.example.ungdungchplay.InterfaceManager.SendData.OnDataLoadedListener;
import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public class FragmentFixedServicePresenter {
    private Context context;
    private ServiceDAO serviceDAO;
    private OnDataLoadedListener onDataLoadedListener;
    public FragmentFixedServicePresenter(Context context, OnDataLoadedListener onDataLoadedListener ) {
        this.context = context;
        this.onDataLoadedListener = onDataLoadedListener;
        serviceDAO = new ServiceDAO(context);
    }
    public void LoadData2(String type){
        String sql = "SELECT * FROM service WHERE type = ?";
        serviceDAO.getAsync2(sql,onDataLoadedListener,type);
    }
}
