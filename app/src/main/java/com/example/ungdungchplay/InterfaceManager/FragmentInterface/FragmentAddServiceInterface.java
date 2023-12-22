package com.example.ungdungchplay.InterfaceManager.FragmentInterface;

import com.example.ungdungchplay.ModelManager.Service;

public interface FragmentAddServiceInterface {
    void addServiceSuccess (String message);
    void addServiceError(String msg);
    void addServiceExists(String msg);
}
