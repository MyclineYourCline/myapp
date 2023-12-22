package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.NotFixedServiceInterface;
import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public class NotFixedServicePresenter {
    private Context context;
    private ServiceDAO serviceDAO;
    private NotFixedServiceInterface iNotFixedServiceInterface;

    public NotFixedServicePresenter(Context context ,NotFixedServiceInterface fixedServiceInterface) {
        this.context = context;
        this.iNotFixedServiceInterface = fixedServiceInterface;
        serviceDAO = new ServiceDAO(context);
    }
    public void getData (int type){
        List<Service> list = serviceDAO.queryByType(type);
        if (list == null) iNotFixedServiceInterface.dataError("Data not Exists");
        else iNotFixedServiceInterface.dataExists(list);
    }
}
