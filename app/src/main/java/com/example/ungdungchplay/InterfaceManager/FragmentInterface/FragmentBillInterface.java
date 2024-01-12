package com.example.ungdungchplay.InterfaceManager.FragmentInterface;

import com.example.ungdungchplay.ModelManager.Bill;

import java.util.List;

public interface FragmentBillInterface {
    void getDataSuccess (String msg, List<Bill> list);
    void getDataError (String msg);
}
