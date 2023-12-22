package com.example.ungdungchplay.InterfaceManager.FragmentInterface;

import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public interface FragmentFixedServiceInterface {
    void dataExist(String msg, List<Service> list);
    void dataError(String msg);

}
