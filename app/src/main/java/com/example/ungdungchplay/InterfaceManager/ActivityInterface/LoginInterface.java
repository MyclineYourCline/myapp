package com.example.ungdungchplay.InterfaceManager.ActivityInterface;

import com.example.ungdungchplay.ModelManager.User;

public interface LoginInterface {
    void loginSuccess(String message, User user);
    void loginError(String message);
    void loginActiveTrue();
    void loginActiveFalse();
}
