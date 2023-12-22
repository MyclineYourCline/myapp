package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.DbStruct;
import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.FragmentAddServiceInterface;
import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public class FragmentAddServicePresenter {
    private FragmentAddServiceInterface iAddServiceInterface;
    private Context context;
    private ServiceDAO serviceDAO;

    public FragmentAddServicePresenter(FragmentAddServiceInterface iAddServiceInterface, Context context) {
        this.iAddServiceInterface = iAddServiceInterface;
        serviceDAO = new ServiceDAO(context);
    }
    public void addService (Service service){
        Service serviceC = serviceDAO.queryByName(service.getName());
        if (serviceC == null){
            long checkI = serviceDAO.insertService(service);
            if (checkI == DbStruct.INSERT_ERR) iAddServiceInterface.addServiceError("Insert Err: "+service.getName());
            else iAddServiceInterface.addServiceSuccess("Insert Success: "+service.getName());
        }
        else{
            iAddServiceInterface.addServiceExists("Already exist service: "+service.getName());
        }
    }
}
