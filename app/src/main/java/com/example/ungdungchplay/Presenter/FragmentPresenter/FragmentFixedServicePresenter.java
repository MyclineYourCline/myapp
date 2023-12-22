package com.example.ungdungchplay.Presenter.FragmentPresenter;

import android.content.Context;

import com.example.ungdungchplay.Database.ServiceDAO;
import com.example.ungdungchplay.InterfaceManager.FragmentInterface.FragmentFixedServiceInterface;
import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public class FragmentFixedServicePresenter {
    private Context context;
    private ServiceDAO serviceDAO;
    private FragmentFixedServiceInterface fixedServiceInterface;
    public FragmentFixedServicePresenter(Context context,FragmentFixedServiceInterface fixedServiceInterface) {
        this.context = context;
        this.fixedServiceInterface = fixedServiceInterface;
        serviceDAO = new ServiceDAO(context);

    }
    public void getDataForSqlite (int type){
        List<Service> list = serviceDAO.queryByType(type);
        if (list == null) fixedServiceInterface.dataError("Data not Exists");
        else fixedServiceInterface.dataExist("Data Exists", list);
    }
}
