package com.example.ungdungchplay.InterfaceManager.FragmentInterface;

import com.example.ungdungchplay.ModelManager.Oder;

import java.util.List;

public interface OderDetailFragmentInterface {
    void getDataSuccess(String msg, List<Oder> list);
    void getDataError (String msq);
    void paymentSuccess(String msg);
    void PaymentError (String msg);
    void updateSuccess (String msg, List<Oder> list);
    void  updateError(String msg);
}
