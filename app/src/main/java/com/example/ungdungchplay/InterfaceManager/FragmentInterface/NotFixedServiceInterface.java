package com.example.ungdungchplay.InterfaceManager.FragmentInterface;

import com.example.ungdungchplay.ModelManager.Service;

import java.util.List;

public interface NotFixedServiceInterface {
    void dataExists (List<Service> list);
    void dataError (String msg);
}
