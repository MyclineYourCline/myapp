package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.NotFixedServiceInterface;
import com.example.ungdungchplay.InterfaceManager.SendData.OnDataLoadedListener;
import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public class NotFixedServicePresenter {
    private Context context;
    private ServiceDAO serviceDAO;
    private OnDataLoadedListener onDataLoadedListener;

    public NotFixedServicePresenter(Context context ,OnDataLoadedListener onDataLoadedListener) {
        this.context = context;
        this.onDataLoadedListener = onDataLoadedListener;
        serviceDAO = new ServiceDAO(context);
    }
    public void getData2(String type){
        String sql = "SELECT * FROM service WHERE type = ?";
        serviceDAO.getAsync2(sql,onDataLoadedListener,type);
    }
}
