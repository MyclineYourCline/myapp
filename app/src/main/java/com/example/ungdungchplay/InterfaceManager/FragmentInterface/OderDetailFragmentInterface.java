package com.example.ungdungchplay.InterfaceManager.FragmentInterface;

import com.example.ungdungchplay.ModelManager.Oder;

import java.util.List;

public interface OderDetailFragmentInterface {
    void getDataSuccess(String msg, List<Oder> list);
    void getDataError (String msq);
    void payMenSuccess(String msg);
    void PayMenError (String msg);
}
