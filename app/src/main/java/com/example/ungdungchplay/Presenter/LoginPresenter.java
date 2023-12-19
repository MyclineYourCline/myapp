package com.example.ungdungchplay.Presenter;

import static android.util.Log.d;

import android.content.Context;

import com.example.ungdungchplay.Database.UserDAO;
import com.example.ungdungchplay.InterfaceManager.LoginInterface;
import com.example.ungdungchplay.ModelManager.User;

import java.util.List;

public class LoginPresenter {
    private LoginInterface loginInterface;
    private UserDAO userDAO;
    private String message;
    private String success = "Login Success ";
    private String account_err = "Account does not exist";
    private String pass_err = "password Invalid";

    public LoginPresenter(LoginInterface loginInterface, Context context) {
        this.loginInterface = loginInterface;
        userDAO = new UserDAO(context);
    }

    public void login(String account, String password){
        User user1 = userDAO.getByAccount(account);
        if (user1 == null){
            loginInterface.loginError(account_err);
        }
        else{
            if(user1.getPassword().equals(password)){
                loginInterface.loginSuccess(success, user1);
            }
            else{
                loginInterface.loginError(pass_err);
            }
        }
    }
}
